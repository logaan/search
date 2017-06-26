(ns search.ui.no-results
  (:require [lanterna.screen :as s]))

(def user-fields
  ["_id active alias created_at email external_id last_login_at locale name"
   "organization_id phone role shared signature suspended tags timezone url"
   "verified"])

(def ticket-fields
  ["_id assignee_id created_at description due_at external_id has_incidents"
   "organization_id priority status subject submitter_id tags type url via"])

(def organization-fields
  ["_id created_at details domain_names external_id name shared_tickets tags"
   "url"])

(defn put-lines [scr x y-start lines]
  (dorun
   (for [[line y] (map list lines (drop y-start (range)))]
     (s/put-string scr x y line))))

(defn sub-head [scr x y text]
  (s/put-string scr x y text {:styles #{:bold}}))

(defn draw [scr]
  (s/put-string scr 1 6 "Your search returned no results.")
  (s/put-string scr 1 8 "Available datasets and fields are:")
  (sub-head scr 1 10 "users")
  (put-lines scr 2 11 user-fields)
  (sub-head scr 1 15 "tickets")
  (put-lines scr 2 16 ticket-fields)
  (sub-head scr 1 19 "organizations")
  (put-lines scr 2 20 organization-fields))
