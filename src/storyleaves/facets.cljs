(ns storyleaves.facets
  (:require
   [storyleaves.cards :as cards]
   [storyleaves.state :as state]))

(defn slot-idx-to-key [idx]
  (keyword (str "slot-" idx)))

(defn click-empty-slot [idx kind _]
  (when-let [selected-card (:selected-card @state/app-state)]
    (swap! state/app-state dissoc :selected-card)
    (state/remove-from-hand! selected-card)
    (swap! state/app-state assoc-in [:facets kind (slot-idx-to-key idx)] [selected-card])))

(defn card-or-slot [facet idx kind]
  (if (empty? facet)
    [cards/slot {:on-click (partial click-empty-slot idx kind)}]
    [cards/card (first facet)]))

(defn facet-slot [attrs idx kind]
  [:div.facet
   [card-or-slot (get-in @state/app-state [:facets
                                           kind
                                           (slot-idx-to-key idx)]) idx kind]])

(defn facet-row [kind]
  [:div.row.facets
   (map (fn [idx]
          [facet-slot {:key (str kind "facet" idx)} idx kind])
        (range 3))])

(defn protagonist-facets []
  [facet-row :protagonist])

(defn antagonist-facets []
  [facet-row :antagonist])

(defn antagonist-effort []
  [facet-slot {} 0 :antagonist-effort])
