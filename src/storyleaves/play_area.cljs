(ns storyleaves.play-area
  (:require [storyleaves.hand :as hand]
            [storyleaves.state :as state]))

(defn play-area []
  [:div
   [hand/hand (get @state/app-state :hand [])]])
