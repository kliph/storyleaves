(ns storyleaves.discard
  (:require [storyleaves.cards :as cards]
            [storyleaves.state :as state]))

(defn top-card-or-card-back [discard]
  (let [discard-count (count discard)
        top-card (first discard)]
    (if (> discard-count 0)
      [cards/card top-card]
      [cards/card-back])))

(defn discard-pile []
  [:div.discard-pile
   [top-card-or-card-back (get @state/app-state :discard)]
   [:div {} (count (get @state/app-state :discard))]])
