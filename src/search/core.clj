(ns search.core
  (:require [lanterna.screen :as s]
            [search.repositories.users :as users]
            [search.repositories.tickets :as tickets]
            [search.repositories.organizations :as organizations]
            [search.input :as input]
            [clojure.tools.namespace.repl :refer [refresh]]))

(def initial-state
  {:dataset "users"
   :field   "_id"
   :query   "71"
   :table   {:selected 0
             :expanded false
             :scroll   0}
   :index   3
   :data    {"users"         (delay (users/load-json))
             "tickets"       (delay (tickets/load-json))
             "organizations" (delay (organizations/load-json))}})

(defn start [type]
  (let [scr (s/get-screen type)]
    (s/in-screen scr (input/listen scr initial-state))))

(defn -main [& args]
  (start :text))

(defn refresh-start []
  (refresh)
  (start :auto))

(comment

  (refresh-start)

  )
