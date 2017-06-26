(ns search.core
  (:require [lanterna.screen :as s]
            [search.ui.text :as text]
            [search.ui.core :as ui]))

(def initial-state
  {:dataset "Users"
   :field   "_id"
   :query   "72"
   :table   {}
   :index   0
   :row     0})

(defn move-index [index direction]
  (mod (direction index) (count ui/fields)))

(defn input-loop [scr]
  (loop [state initial-state]
    (ui/draw state scr)
    (let [focus          (ui/fields (:index state))
          focus-value    (state focus)
          [action value] (text/input focus-value
                                     (s/get-key-blocking scr))]
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
  (ui/start :text))


(comment

  (start :auto)

  )
