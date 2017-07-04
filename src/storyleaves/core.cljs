(ns storyleaves.core
  (:require [reagent.core :as r]
            [storyleaves.cards :as cards]
            [goog.dom]))

(def by-id goog.dom.getElement)

(defn app-container []
  [:div {}
   [cards/card {:title "Hello World!"
                :kind "Character"}]
   [cards/card-back]])

(r/render-component [app-container] (by-id "app"))
