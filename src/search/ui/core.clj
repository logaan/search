(ns search.ui.core
  (:require [lanterna.screen :as s]
            [search.ui.text :as text]))

;;- specific drawing -----------------------------------------------------------

(def fields
  [:dataset :field :query :table])

(def key-style
  {:fg :white
   :bg :cyan})

(defn draw [{:keys [dataset field query table index]} scr]
  (let [focus (fields index)]
    (doto scr
     (s/put-string 2 1 "Zendesk Search Coding Challenge" {:styles #{:bold}})

     (s/put-string 2 3 "Dataset:")
     (text/field 11 3 dataset (= :dataset focus))

     (s/put-string 4 5 "Field:")
     (text/field 11 5 field (= :field focus))

     (s/put-string 4 7 "Query:")
     (text/field 11 7 query (= :query focus))

     (s/put-string 1 23 "    Quit       Next     Next           Previous")
     (s/put-string 1 23 "Esc" key-style)
     (s/put-string 10 23 "Enter" key-style)
     (s/put-string 21 23 "Tab" key-style)
     (s/put-string 30 23 "Shift-Tab" key-style)

     (s/redraw))))

;;- core -----------------------------------------------------------------------

(def initial-state
  {:dataset "Users"
   :field   "_id"
   :query   "72"
   :table   {}
   :index   0})

(defn move-index [index direction]
  (mod (direction index) (count fields)))

(defn input-loop [scr]
  (loop [state initial-state]
    (draw state scr)
    (let [focus          (fields (:index state))
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

(comment

  (start :auto)

  )
