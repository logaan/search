(ns search.repositories.organizations-test
  (:require [search.repositories.organizations :as sut]
            [search.repositories.common-test :refer [utc-date-time]]
            [clojure.test :refer [deftest is]]))

(def first-organization
  {"_id"            101
   "url"            "http://initech.zendesk.com/api/v2/organizations/101.json"
   "external_id"    "9270ed79-35eb-4a38-a46f-35725197ea8d"
   "name"           "Enthaze"
   "domain_names"   ["kage.com"
                     "ecratic.com"
                     "endipin.com"
                     "zentix.com"]
   "created_at"     (utc-date-time 2016 05 21 11 10 28 -10)
   "details"        "MegaCorp"
   "shared_tickets" false
   "tags"           ["Fulton"
                     "West"
                     "Rodriguez"
                     "Farley"]})

(deftest loads-first-organization
  (is (= first-organization (first (sut/load-json)))))
