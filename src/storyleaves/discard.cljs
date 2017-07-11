(ns storyleaves.discard
  (:require [storyleaves.cards :as cards]
            [storyleaves.state :as state]))

(defn top-discard-card [] (first (get @state/app-state :discard)))

(defn click-empty-discard []
  (let [selected-card (:selected-card @state/app-state)
        original-hand (get @state/app-state :hand [])
        hand (remove #{selected-card} original-hand)
        discard (into [selected-card]
                      (get @state/app-state :discard []))]
    (when selected-card
      (swap! state/app-state dissoc :selected-card)
      (state/remove-from-hand! selected-card)
      (swap! state/app-state assoc :discard discard))))

(defn click-top-discard []
  (let [top-card (top-discard-card)]
    (if (and (state/card-selected?)
             (not= (:selected-card @state/app-state)
                   top-card))
      (click-empty-discard)
      (cards/handle-select (first (get @state/app-state :discard))))))

(defn top-card-or-card-back []
  (let [discard-count (count (get @state/app-state :discard))
        top-card (top-discard-card)]
    (if (> discard-count 0)
      [:div {:on-click click-top-discard}
       [cards/card-unclickable top-card]]
      [:div {:on-click click-empty-discard}
       [cards/card-back]])))

(defn discard-pile []
  [:div.discard-pile {}
   [top-card-or-card-back]
   [:div {} (count (get @state/app-state :discard))]])
