(ns storyleaves.facets
  (:require
   [storyleaves.cards :as cards]
   [storyleaves.state :as state]))

(defn facet-slot []
  [:div.facet
   [cards/card-back]])

(defn protagonist-facets []
  [:div.row.protagonist-facets
   (map (fn []
          [facet-slot])
        (range 3))])
