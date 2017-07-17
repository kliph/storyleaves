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

(defn base-card [{:keys [title kind idx]} handler-func]
  (let [key (str idx kind title)]
    [card-border
     handler-func
     idx
     [:h2 {:key (str key "idx")}
      idx]
     [:h1 {:key (str key "title")}
      title]
     [:div.chip {:key (str key "kind")}
      kind]]))

(defn card [props]
  (let [card (select-keys props [:title :kind :idx])]
    (base-card props (partial handle-select card))))

(defn card-unclickable [props]
  (base-card props (fn [])))

(defn card-back []
  [:div.card-border {}
   [:div.card-border-inset {}]])

(defn slot [attrs]
  [:div.slot attrs])
