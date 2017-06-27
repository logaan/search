(ns search.input-test
  (:require [clojure.test :refer [deftest is are]]
            [search.input :as sut]))

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

(deftest set-field-value
  (let [state "kittens"]
    (is (= "puppies"
           (sut/perform-action state :set "puppies")))))

(deftest move-index-as-action
  (are [initial direction result]
      (= {:index result}
         (sut/perform-action {:index initial} direction {}))
    1 :next 2
    3 :next 0
    1 :prev 0
    0 :prev 3))
