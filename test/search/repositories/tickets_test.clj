(ns search.repositories.tickets-test
  (:require [search.repositories.tickets :as sut]
            [search.repositories.common-test :refer [utc-date-time]]
            [clojure.test :refer [deftest is]]))

(def first-ticket
  {"_id"             "436bf9b0-1147-4c0a-8439-6f79833bff5b"
   "url"             "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json"
   "external_id"     "9210cdc9-4bee-485f-a078-35396cd74063"
   "created_at"      (utc-date-time 2016 4 28 11 19 34 -10)
   "type"            "incident"
   "subject"         "A Catastrophe in Korea (North)"
   "description"     "Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum."
   "priority"        "high"
   "status"          "pending"
   "submitter_id"    38
   "assignee_id"     24
   "organization_id" 116
   "tags"            ["Ohio"
                      "Pennsylvania"
                      "American Samoa"
                      "Northern Mariana Islands"]
   "has_incidents"   false
   "due_at"          (utc-date-time 2016 7 31 2 37 50 -10)
   "via"             "web"})

(deftest loads-first-ticket
  (is (= first-ticket (first (sut/load-json)))))
