(ns search.ui.table-test
  (:require [search.ui.table :as sut]
            [clojure.test :refer [deftest is are]]))

(deftest handles-input
  (let [state {:selected 1 :expanded false}]
    (are [input result] (= result (sut/input state input))
      :escape      [:exit state]
      :enter       [:set {:selected 1 :expanded true}]
      :tab         [:next state]
      :reverse-tab [:prev state]
      :up          [:set {:selected 0 :expanded false}]
      \k           [:set {:selected 0 :expanded false}]
      :down        [:set {:selected 2 :expanded false}]
      \j           [:set {:selected 2 :expanded false}]
      \a           [:set state]
      \z           [:set state])))
