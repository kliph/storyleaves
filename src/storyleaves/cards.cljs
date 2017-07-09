(ns storyleaves.cards
  (:require [reagent.core :as r]
            [storyleaves.state :as state]))

(defn selected? [idx]
  (= idx (-> @state/app-state
             :selected-card
             :idx)))

(defn card-border [click-fn idx & children]
  [:div.card-border
   {:on-click click-fn
    :class (when (selected? idx)
             "selected")}
   [:div.card-border-inset
    {}
    children]])

(defn handle-select [card]
  (if (selected? (:idx card))
    (swap! state/app-state dissoc :selected-card)
    (swap! state/app-state assoc :selected-card card)))

(defn card [{:keys [title kind idx]}]
  (let [key (str idx kind title)
        card {:title title
              :kind kind
              :idx idx}]
    [card-border
     (partial handle-select card)
     idx
     [:h2 {:key (str key "idx")}
      idx]
     [:h1 {:key (str key "title")}
      title]
     [:div.chip {:key (str key "kind")}
      kind]]))

(defn card-back []
  [:div.card-border {}
   [:div.card-border-inset {}]])

(defn slot []
  [:div.slot {}])
