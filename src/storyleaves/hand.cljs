(ns storyleaves.hand
  (:require [storyleaves.state :as state]
            [storyleaves.cards :as cards]))

(defn draw-hand! []
  (let [original-shuffled-deck (get @state/app-state :shuffled-deck [])
        hand (take 5 original-shuffled-deck)]
    (swap! state/app-state assoc :shuffled-deck (drop 5 original-shuffled-deck) :hand hand)))

(defn shuffle-deck! []
  (swap! state/app-state assoc :shuffled-deck (shuffle (get @state/app-state :deck []))))

(defn controls []
  [:span
   [:button {:on-click #(js/console.log "hiiiiiii")}
    "Draw up to 5 cards"]
   [:button {:on-click #(shuffle-deck!)}
    "Reshuffle Deck"]
   [:button {:on-click #(draw-hand!)}
    "Redraw Hand"]])

(defn hand [hand]
  [:div
   [:div.row.hand {}
    (map
     (fn [{:keys [idx title kind]}]
       [cards/card {:key (str idx kind title "hand card")
                    :title title
                    :kind kind
                    :idx idx}])
     hand)]
   [:div.row.hand.controls {}
    [controls]]])
