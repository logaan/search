(ns search.ui.text-test
  (:require [search.ui.text :as sut]
            [clojure.test :refer [deftest is are]]))

(defn dataset [str]
  {:dataset str :index 0})

(deftest backspace-deletes-last
  (is (= [:set (dataset "fo")] (sut/input (dataset "foo") :backspace)))
  (is (= [:set (dataset "")] (sut/input (dataset "") :backspace))))

(deftest character-keys-get-appended
  (is (= [:set (dataset "foo")] (sut/input (dataset "fo") \o)))
  (is (= [:set (dataset "a")] (sut/input (dataset "") \a))))

(deftest handles-input
  (let [state {:dataset "foo" :index 0}]
    (are [input result] (= result (sut/input state input))
      :escape      [:exit state]
      :enter       [:next state]
      :tab         [:next state]
      :down        [:next state]
      :reverse-tab [:prev state]
      :up          [:prev state]
      :backspace   [:set (dataset "fo")]
      \a           [:set (dataset "fooa")]
      \z           [:set (dataset "fooz")]
      :home        [:set state])))

(deftest drops-start-of-strings
  (is (= "oo" (sut/last-n "foo" 2)))
  (is (= "" (sut/last-n "" 2)))
  (is (= "foo" (sut/last-n "foo" 99))))

(deftest blank-strings
  (is (= "     " (sut/blank-str 5))))
