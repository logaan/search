(ns search.repositories.users
  (:require [search.repositories.common :as common]
            [clojure.pprint :refer [cl-format]]))

(defn parse-user-dates [unparsed-user]
  (-> unparsed-user
      (update-in ["created_at"]    common/parse-date-time)
      (update-in ["last_login_at"] common/parse-date-time)))

(defn load-json []
  (->> (common/load-json "users.json")
       (map parse-user-dates)))

(def column-widths
  [20 15 9 31])

(def format-str
  (let [[n a r e] column-widths]
    (str "%-" n "s %-" a "s %-" r "s %-" e "s")))

(def header
  (format format-str "Name" "Alias" "Role" "Email"))

(defn summarise [{:strs [name alias email role]}]
  (let [[n a r e] column-widths
        f         common/first-n]
    (format format-str (f name n) (f alias a) (f role r) (f email e))))
