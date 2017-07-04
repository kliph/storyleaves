(ns storyleaves.core
  (:require [reagent.core :as r]
            [storyleaves.cards :as cards]
            [storyleaves.deck-loader :as deck-loader]
            [storyleaves.state :as state]
            [goog.dom]))

(def by-id goog.dom.getElement)

(defn app-container []
  [:div.app-container {}
   [:div.row
    [deck-loader/loader]]
   [:div.row
    (map-indexed (fn [idx {:keys [title kind]}]
                   [cards/card {:key (str idx kind title "card")
                                :title title
                                :kind kind
                                :idx idx}])
                 (get @state/app-state :deck []))

    [cards/card-back]]])

(r/render-component [app-container] (by-id "app"))
