(ns storyleaves.card-input
  (:require [reagent.core :as r]))

(defn card-input [{:keys [deck]} props]
  [:div.card-input
   [:input#card-title
    {:placeholder "Title"}]
   [:input#card-kind
    {:placeholder "Kind"}]
   [:button {} "Add Card"]])
