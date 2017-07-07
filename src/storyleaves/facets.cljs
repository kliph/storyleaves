(ns storyleaves.facets
  (:require
   [storyleaves.cards :as cards]
   [storyleaves.state :as state]))

(defn facet-slot []
  [:div.facet
   [cards/slot]])

(defn facet-row [kind]
  [:div.row.facets
   (map (fn [idx]
          [facet-slot {:key (str kind "facet" idx)}])
        (range 3))])

(defn protagonist-facets []
  [facet-row :protagonist])

(defn antagonist-facets []
  [facet-row :antagonist])

(defn antagonist-effort []
  [facet-slot])
