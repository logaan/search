(ns search.repositories.users
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            [clj-time.format :as time]))

(def date-time-format
  (time/formatter "y-M-d'T'k:m:s Z"))

(defn parse-date-time [date-time-string]
  (time/parse date-time-format date-time-string))

(defn parse-user-dates [unparsed-user]
  (-> unparsed-user
      (update-in ["created_at"] parse-date-time)
      (update-in ["last_login_at"] parse-date-time)))

(defn load-json []
  (->> (io/resource "users.json")
       slurp
       json/parse-string
       (map parse-user-dates)))
