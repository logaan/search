(ns search.ui.core-test
  (:require [search.ui.core :as sut]
            [clojure.test :refer [deftest is are]]))

(deftest move-index-loops
  (are [initial direction result] (= result (sut/move-index initial direction))
    0 inc 1
    1 inc 2
    2 inc 3
    3 inc 0
    0 dec 3
    1 dec 0
    2 dec 1
    3 dec 2))

(deftest backspace-deletes-last
  (is (= [:set "fo"] (sut/text-input "foo" :backspace)))
  (is (= [:set ""] (sut/text-input "" :backspace))))

(deftest character-keys-get-appended
  (is (= [:set "foo"] (sut/text-input "fo" \o)))
  (is (= [:set "a"] (sut/text-input "" \a))))
