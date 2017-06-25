(ns search.repositories.users
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]))

(defn load-json []
  (-> (io/resource "users.json")
      slurp
      json/parse-string))
