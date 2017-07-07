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
    [:div.space-left
     [:h2.play-area {} "Antagonist's Efforts"]
     [facets/antagonist-effort]]
    [:div
     [:h2.play-area {} "Protagonist Resources / Facets"]
     [facets/protagonist-facets]]
    [:div.discard-pile-area.space-left
     [:h2.play-area {} "Discard pile"]
     [discard/discard-pile]]]
   [:h2.play-area {} "Hand"]
   [hand/hand (get @state/app-state :hand [])]]
  )
