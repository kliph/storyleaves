(ns storyleaves.deck-spec
  (:require [clojure.spec.alpha :as s]))


(s/def :card/title string?)
(s/def :card/kind string?)
(s/def ::card (s/keys :req-un [:card/title
                               :card/kind]))
(s/def ::deck (s/coll-of ::card))

(defn just-valid-deck [maybe-deck]
  (let [deck (s/conform ::deck maybe-deck)]
    (when (s/valid? ::deck maybe-deck)
      deck)))
