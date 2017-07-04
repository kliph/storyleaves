(ns storyleaves.cards
  (:require [reagent.core :as r]))

(defn card-border [child]
  [:div.card-border
   {}
   [:div.card-border-inset
    {}
    child]])

(defn card [{:keys [title kind]} props]
  [card-border
   [:h1 {}
    (str title kind)]])

(defn card-back []
  [card-border])
