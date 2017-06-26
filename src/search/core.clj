(ns search.core
  (:require [lanterna.screen :as s]
            [search.ui.text :as text]
            [search.ui.core :as ui]
            [search.ui.table :as table]))

(def initial-state
  {:dataset "Users"
   :field   "_id"
   :query   "72"
   :table   {:selected 0
             :expanded false}
   :index   0})

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
      (if (= :exit action)
        (println "exiting")
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
