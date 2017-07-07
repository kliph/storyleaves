(ns storyleaves.cards
  (:require [reagent.core :as r]
            [storyleaves.state :as state]))

(defn card-border [click-fn selected-atom & children]
  [:div.card-border
   {:on-click click-fn
    :class (when @selected-atom
             "selected")}
   [:div.card-border-inset
    {}
    children]])

(defn handle-select [selected-atom card]
  (swap! selected-atom not)
  (swap! state/app-state assoc :selected-card card)
  (js/console.log  @state/app-state))

(defn card [{:keys [title kind idx]}]
  (let [key (str idx kind title)
        card {:title title
              :kind kind
              :idx idx}
        selected-atom (r/atom false)]
    [card-border
     (partial handle-select selected-atom card)
     selected-atom
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
