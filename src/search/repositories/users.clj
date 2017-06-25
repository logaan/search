(ns search.repositories.users
  (:require [search.repositories.common :as common]))

(defn parse-user-dates [unparsed-user]
  (-> unparsed-user
      (update-in ["created_at"]    common/parse-date-time)
      (update-in ["last_login_at"] common/parse-date-time)))

(defn load-json []
  (->> (common/load-json "users.json")
       (map parse-user-dates)))
