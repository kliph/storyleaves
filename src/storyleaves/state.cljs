(ns storyleaves.state
  (:require [reagent.core :as r]))

(def app-state (r/atom {:deck [{:title "Hello World"
                                :kind "Character"
                                :idx 0}]
                        :facets {:antagonist {:slot-0 []
                                              :slot-1 []
                                              :slot-2 []}
                                 :protagonist {:slot-0 []
                                               :slot-1 []
                                               :slot-2 []}
                                 :antagonist-effort {:slot-0 []}}
                        :discard []}))

(defn card-selected? []
  ((complement nil?) (:selected-card @app-state)))

(defn remove-from-hand! [card]
  (let [original-hand (get @app-state :hand [])
        hand (remove #{card} original-hand)]
    (swap! app-state assoc :hand hand)))
