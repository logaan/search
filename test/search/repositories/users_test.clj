(ns search.repositories.users-test
  (:require [search.repositories.users :as sut]
            [search.repositories.common-test :refer [utc-date-time]]
            [clojure.test :refer [deftest is]]))

(def first-user
  {"_id"             1
   "url"             "http://initech.zendesk.com/api/v2/users/1.json"
   "external_id"     "74341f74-9c79-49d5-9611-87ef9b6eb75f"
   "name"            "Francisca Rasmussen"
   "alias"           "Miss Coffey"
   "created_at"      (utc-date-time 2016 4 15 5 19 46 -10)
   "active"          true
   "verified"        true
   "shared"          false
   "locale"          "en-AU"
   "timezone"        "Sri Lanka"
   "last_login_at"   (utc-date-time 2013 8 4 1 3 27 -10)
   "email"           "coffeyrasmussen@flotonic.com"
   "phone"           "8335-422-718"
   "signature"       "Don't Worry Be Happy!"
   "organization_id" 119
   "tags"            ["Springville"
                      "Sutton"
                      "Hartsville/Hartley"
                      "Diaperville"]
   "suspended"       true
   "role"            "admin"})

(deftest loads-first-user
  (is (= first-user (first (sut/load-json)))))

