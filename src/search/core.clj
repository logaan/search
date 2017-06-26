(ns search.core
  (:require [lanterna.screen :as s]
            [search.ui.text :as text]
            [search.ui.core :as ui]
            [search.repositories.users :as users]
            [search.repositories.tickets :as tickets]
            [search.repositories.organizations :as organizations]
            [search.ui.table :as table]))

(def initial-state
  {:dataset "users"
   :field   "_id"
   :query   "71"
   :table   {:selected 0
             :expanded false}
   :index   0
   :data    {"users"         (delay (users/load-json))
             "tickets"       (delay (tickets/load-json))
             "organizations" (delay (organizations/load-json))}})

(defn move-index [index direction]
  (mod (direction index) (count ui/fields)))

(def field-types
  {:dataset text/input
   :field   text/input
   :query   text/input
   :table   table/input})

(defn input-loop [scr]
  (loop [state initial-state]
    (ui/draw state scr)
    (let [focus          (ui/fields (:index state))
          focus-value    (state focus)
          handler        (field-types focus)
          key            (s/get-key-blocking scr)
          [action value] (handler focus-value key)]
      (if (not= :exit action)
        (recur
         (case action
           :set  (assoc state focus value)
           :next (update-in state [:index] move-index inc)
           :prev (update-in state [:index] move-index dec)))))))

(defn start [type]
  (let [scr (s/get-screen type)]
    (s/in-screen scr (input-loop scr))))

(defn -main [& args]
  (start :text))

(comment

  (start :auto)
  
  )
