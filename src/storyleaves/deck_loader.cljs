(ns storyleaves.deck-loader
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require [storyleaves.deck-spec :as deck-spec]
            [storyleaves.state :as state]
            [cljs.core.async :refer [chan <! put!]]))

(defn deck-with-indices [deck]
  (map-indexed (fn [idx card]
                 (assoc card :idx idx))
               deck))

(def first-file
  (map (fn [e]
         (let [target (.-currentTarget e)
               file (-> target
                        .-files
                        (aget 0))]
           (set! (.-value target) "")
           file))))

(def extract-result
  (map #(-> %
            .-target
            .-result
            cljs.reader/read-string
            deck-spec/just-valid-deck
            deck-with-indices)))

(def uploaded-files (chan 1 first-file))
(def file-reads (chan 1 extract-result))

(defn load-deck [e]
  (put! uploaded-files e))

(go-loop []
  (let [reader (js/FileReader.)
        file (<! uploaded-files)]
    (set! (.-onload reader) #(put! file-reads %))
    (.readAsText reader file)
    (recur)))

(go-loop []
  (let [deck (<! file-reads)
        shuffled-deck (shuffle deck)
        hand (take 5 shuffled-deck)
        rest-deck (drop 5 shuffled-deck)]
    (swap! state/app-state assoc :deck deck :shuffled-deck rest-deck :hand hand)

    (recur)))

(defn loader []
  [:div.loader
   [:label {:for "load_deck"} "Load deck: "
    [:input {:type "file"
             :id "load_deck"
             :name "load_deck"
             :accept ".edn"
             :on-change load-deck}]]])
