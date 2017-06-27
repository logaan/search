(ns search.ui.table-test
  (:require [search.ui.table :as sut]
            [clojure.test :refer [deftest is are]]))

(defn table [selected expanded scroll]
  {:table {:selected selected
           :expanded expanded
           :scroll scroll}
   :dataset "users"
   :field "_id"
   :query 72
   :data {"users" (delay [{"_id" 72}])}})

(deftest handles-input
  (let [state (table 1 false 1)]
    (are [input result] (= (:table result) (:table (sut/input state input)))
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
