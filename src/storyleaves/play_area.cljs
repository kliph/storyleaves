(ns storyleaves.play-area
  (:require [storyleaves.hand :as hand]
            [storyleaves.discard :as discard]
            [storyleaves.state :as state]))

(defn play-area []
  [:div
   [:h2.play-area {} "Discard pile"]
   [discard/discard-pile]
   [:h2.play-area {} "Hand"]
   [hand/hand (get @state/app-state :hand [])]])
