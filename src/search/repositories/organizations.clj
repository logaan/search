(ns search.repositories.organizations
  (:require [search.repositories.common :as common]))

(defn parse-organization-dates [unparsed-organization]
  (update-in unparsed-organization ["created_at"] common/parse-date-time))

(defn load-json []
  (->> (common/load-json "organizations.json")
       (map parse-organization-dates)))
