(ns search.repositories.tickets
  (:require [search.repositories.common :as common]))

(defn parse-ticket-dates [unparsed-ticket]
  (-> unparsed-ticket
      (update-in ["created_at"] common/parse-date-time)
      (update-in ["due_at"]     common/parse-date-time)))

(defn load-json []
  (->> (common/load-json "tickets.json")
       (map parse-ticket-dates)))
