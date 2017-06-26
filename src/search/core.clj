(ns search.core
  (:require [lanterna.screen :as s]
            [search.repositories.users :as users]
            [search.repositories.tickets :as tickets]
            [search.repositories.organizations :as organizations]
            [search.input :as input]))

(def initial-state
  {:dataset "tickets"
   :field   ""
   :query   ""
   :table   {:selected 0
             :expanded false
             :scroll   1}
   :index   3
   :data    {"users"         (delay (users/load-json))
             "tickets"       (delay (tickets/load-json))
             "organizations" (delay (organizations/load-json))}})

(defn start [type]
  (let [scr (s/get-screen type)]
    (s/in-screen scr (input/listen scr initial-state))))

(defn -main [& args]
  (start :text))

(comment

  (start :auto)

  )
