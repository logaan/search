(ns search.repositories.users-test
  (:require [search.repositories.users :as sut]
            [clojure.test :as t]))

(def first-user
  {"_id"             1
   "url"             "http://initech.zendesk.com/api/v2/users/1.json"
   "external_id"     "74341f74-9c79-49d5-9611-87ef9b6eb75f"
   "name"            "Francisca Rasmussen"
   "alias"           "Miss Coffey"
   "created_at"      "2016-04-15T05:19:46 -10:00"
   "active"          true
   "verified"        true
   "shared"          false
   "locale"          "en-AU"
   "timezone"        "Sri Lanka"
   "last_login_at"   "2013-08-04T01:03:27 -10:00"
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

(t/deftest loads-first-user
  (t/is (= first-user
           (first (sut/load-json)))))
