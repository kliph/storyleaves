(ns storyleaves.cards)

(defn card-border [& children]
  [:div.card-border
   {}
   [:div.card-border-inset
    {}
    children]])

(defn card [{:keys [title kind idx]} props]
  (let [key (str idx kind title)]
    [card-border
     [:h2 {:key (str key "idx")}
      idx]
     [:h1 {:key (str key "title")}
      title]
     [:div.chip {:key (str key "kind")}
      kind]]))

(defn card-back []
  [card-border])

(defn slot []
  [:div.slot {}])
