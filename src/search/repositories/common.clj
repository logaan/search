(ns search.repositories.common
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            [clj-time.format :as time]))


(def date-time-format
  (time/formatter "y-M-d'T'k:m:s Z"))

(defn parse-date-time [date-time-string]
  (if date-time-string
    (time/parse date-time-format date-time-string)))

(defn load-json [path]
  (->> (io/resource path)
       io/reader
       json/parse-stream))
