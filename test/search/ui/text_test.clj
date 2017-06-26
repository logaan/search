(ns search.ui.text-test
  (:require [search.ui.text :as sut]
            [clojure.test :refer [deftest is are]]))

(deftest backspace-deletes-last
  (is (= [:set "fo"] (sut/input "foo" :backspace)))
  (is (= [:set ""] (sut/input "" :backspace))))

(deftest character-keys-get-appended
  (is (= [:set "foo"] (sut/input "fo" \o)))
  (is (= [:set "a"] (sut/input "" \a))))

(deftest handles-input
  (let [text "foo"]
    (are [input result] (= result (sut/input text input))
      :escape      [:exit text]
      :enter       [:next text]
      :tab         [:next text]
      :down        [:next text]
      :reverse-tab [:prev text]
      :up          [:prev text]
      :backspace   [:set "fo"]
      \a           [:set "fooa"]
      \z           [:set "fooz"]
      :home        [:set text])))

(deftest drops-start-of-strings
  (is (= "oo" (sut/last-n "foo" 2)))
  (is (= "" (sut/last-n "" 2)))
  (is (= "foo" (sut/last-n "foo" 99))))

(deftest blank-strings
  (is (= "     " (sut/blank-str 5))))
