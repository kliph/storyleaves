(ns storyleaves.core
  (:require [reagent.core :as r]
            [storyleaves.cards :as cards]
            [storyleaves.deck-loader :as deck-loader]
            [storyleaves.play-area :as play-area]
            [storyleaves.state :as state]
            [goog.dom]))

(def by-id goog.dom.getElement)

(defn app-container []
  [:div.app-container {}
   [:section.loader
    [:div.row
     [deck-loader/loader]]]
   [:section.play-area
    [play-area/play-area]]
   [:section.deck
    [:div.row
     (map
      (fn [{:keys [idx title kind]}]
        [cards/card {:key (str idx kind title "card")
                     :title title
                     :kind kind
                     :idx idx}])
      (get @state/app-state :deck []))

     [cards/card-back]]]])

(r/render-component [app-container] (by-id "app"))
