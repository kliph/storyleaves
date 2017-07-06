(ns storyleaves.play-area
  (:require [storyleaves.hand :as hand]
            [storyleaves.discard :as discard]
            [storyleaves.facets :as facets]
            [storyleaves.state :as state]))

(defn play-area []
  [:div
   [:div.row
    [:div
     [:h2.play-area {} "Antagonist Resources / Facets"]
     [facets/antagonist-facets]]
    [:div.discard-pile-area
     [:h2.play-area {} "Discard pile"]
     [discard/discard-pile]]
    [:div
     [:h2.play-area {} "Protagonist Resources / Facets"]
     [facets/protagonist-facets]]]
   [:h2.play-area {} "Hand"]
   [hand/hand (get @state/app-state :hand [])]]
  )
