(ns storyleaves.discard
  (:require [storyleaves.cards :as cards]
            [storyleaves.state :as state]))

(defn discard-pile []
  [:div.discard-pile
   [cards/card-back]
   [:div {} (count (get @state/app-state :discard))]])
