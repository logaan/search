(ns search.ui.table-test
  (:require [search.ui.table :as sut]
            [clojure.test :refer [deftest is are]]))

(deftest handles-input
  (let [state {:selected 1 :expanded false :scroll 1}]
    (are [input result] (= result (sut/input state input))
      :escape      [:exit state]
      :enter       [:set {:selected 1 :expanded true :scroll 1}]
      :tab         [:next state]
      :reverse-tab [:prev state]
      :up          [:set {:selected 1 :expanded false :scroll 0}]
      \k           [:set {:selected 0 :expanded false :scroll 1}]
      :down        [:set {:selected 1 :expanded false :scroll 2}]
      \j           [:set {:selected 2 :expanded false :scroll 1}]
      \a           [:set state]
      \z           [:set state])))
