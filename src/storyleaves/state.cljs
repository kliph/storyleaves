(ns storyleaves.state
  (:require [reagent.core :as r]))

(def app-state (r/atom {:deck [{:title "Hello World"
                                :kind "Character"
                                :idx 0}]
                        :facets {:antagonist [{} {} {}]
                                 :protagonist [{} {} {}]
                                 :antagonist-effort [{}]}
                        :discard []}))

(defn card-selected? []
  ((complement nil?) (:selected-card @app-state)))

(defn remove-from-hand! [card]
  (let [original-hand (get @app-state :hand [])
        hand (remove #{card} original-hand)]
    (swap! app-state assoc :hand hand)))
