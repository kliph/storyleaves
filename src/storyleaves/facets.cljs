(ns storyleaves.facets
  (:require
   [storyleaves.cards :as cards]
   [storyleaves.state :as state]))

(defn card-or-slot [facet]
  (if (empty? facet)
    [cards/slot]
    [cards/card (first facet)]))

(defn facet-slot [attrs idx kind]
  (let [facet (get-in @state/app-state [:facets
                                        kind
                                        idx])]
    [:div.facet
     [card-or-slot facet]]))

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
  [facet-slot])
