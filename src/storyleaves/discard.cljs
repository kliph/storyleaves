(ns storyleaves.discard
  (:require [storyleaves.cards :as cards]
            [storyleaves.state :as state]))

(defn discard-pile []
  [:div.discard-pile.center
   [cards/card-back]
   [:p {} (count (get @state/app-state :discard))]])
