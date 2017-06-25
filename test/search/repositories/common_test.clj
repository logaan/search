(ns search.repositories.common-test
  (:require [search.repositories.common :as sut]
            [clojure.test :refer [deftest is]]

            [clj-time.core :as time]
            [clj-time.format :as f]))

(defn utc-date-time [year month day hour minute second timezone-offset]
  (-> (time/date-time year month day hour minute second)
      (time/from-time-zone (time/time-zone-for-offset timezone-offset))
      (time/to-time-zone time/utc)))

(deftest parse-date-time
  (is (= (utc-date-time 2016 4 15 5 19 46 -10)
         (sut/parse-date-time "2016-04-15T05:19:46 -10:00"))))
