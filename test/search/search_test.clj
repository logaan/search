(ns search.ui.search-test
  (:require [search.search :as sut]
            [clojure.test :refer [deftest is are]]))

(deftest search
  (let [state {:dataset "users"
               :field "age"
               :query "20"
               :data {"users" (delay
                               [{"name" "foo" "age" 20}
                                {"name" "bar" "age" 22}
                                {"name" "baz" "age" 20}])}}]
    (is (= [{"name" "foo" "age" 20}
            {"name" "baz" "age" 20}]
           (sut/results state)))))
