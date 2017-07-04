(ns storyleaves.state
  (:require [reagent.core :as r]))

(def app-state (r/atom {:deck [{:title "Hello World"
                                :kind "Character"}]}))
