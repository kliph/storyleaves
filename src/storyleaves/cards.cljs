(ns storyleaves.cards)

(defn card-border [& children]
  [:div.card-border
   {}
   [:div.card-border-inset
    {}
    children]])

(defn card [{:keys [title kind idx]} props]
  [card-border
   [:h2 {}
    idx]
   [:h1 {}
    title]
   [:div.chip {}
    kind]])

(defn card-back []
  [card-border])
