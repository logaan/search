(ns search.ui.text-test
  (:require [search.ui.text :as sut]
            [clojure.test :as t]))

(deftest backspace-deletes-last
  (is (= [:set "fo"] (sut/input "foo" :backspace)))
  (is (= [:set ""] (sut/input "" :backspace))))

(deftest character-keys-get-appended
  (is (= [:set "foo"] (sut/input "fo" \o)))
  (is (= [:set "a"] (sut/input "" \a))))
