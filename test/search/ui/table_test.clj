(ns search.ui.table-test
  (:require [search.ui.table :as sut]
            [clojure.test :refer [deftest is are]]))

(defn table [selected expanded scroll]
  {:table {:selected selected :expanded expanded :scroll scroll}})

(deftest handles-input
  (let [state (table 1 false 1)]
    (are [input result] (= result (sut/input state input))
      :escape      [:exit state]
      :enter       [:set (table 1 true 1)]
      :tab         [:next state]
      :reverse-tab [:prev state]
      :up          [:set (table 1 false 0)]
      \k           [:set (table 0 false 1)]
      :down        [:set (table 1 false 2)]
      \j           [:set (table 2 false 1)]
      \a           [:set state]
      \z           [:set state])))
