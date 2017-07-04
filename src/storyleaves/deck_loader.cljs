(ns storyleaves.deck-loader
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require [storyleaves.deck-spec :as deck-spec]
            [storyleaves.state :as state]
            [cljs.core.async :refer [chan <! put!]]))

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
            deck-spec/just-valid-deck)))

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
  (swap! state/app-state assoc :deck (<! file-reads))
  (recur))

(defn loader []
  [:div
   [:label {:for "load_deck"} "Load deck: "
    [:input {:type "file"
             :id "load_deck"
             :name "load_deck"
             :accept ".edn"
             :on-change load-deck}]]])
