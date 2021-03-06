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

(defn remove-from-facets! [card]
  (let [kinds [:antagonist :protagonist]
        slots [:slot-0 :slot-1 :slot-2]
        update-slots (fn [kind]
                       (zipmap
                        slots
                        (map
                         (fn [slot]
                           (remove #{card} (get-in @app-state [kind slot])))
                         slots)))
        facets (-> (reduce
                    (fn [acc kind] (assoc acc kind (update-slots kind)))
                    {}
                    kinds)
                   (assoc :antagonist-effort
                          {:slot-0
                           (remove #{card} (get-in @app-state [:antagonist-effort :slot-0]))}))]
    (js/console.log facets)
    (swap! app-state assoc :facets facets)))
