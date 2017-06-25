(ns search.ui.core
  (:require [lanterna.screen :as s]
            [search.ui.text :as text]
            [search.repositories.users :as users]))

;;- specific drawing -----------------------------------------------------------

(def fields
  [:dataset :field :query :table])

(def key-style
  {:fg :white
   :bg :cyan})

(def divider
  "------------------------------------------------------------------------------")

(defn draw-users [scr]
  (let [users (users/load-json)
        lines (range 8 22)]
    (s/put-string scr 1 6 users/header)
    (s/put-string scr 1 7 divider)
    (dorun
     (for [[line user] (map list lines users)]
       (s/put-string scr 1 line (users/summarise user))))))

(defn draw [{:keys [dataset field query table index]} scr]
  (let [focus (fields index)]
    (doto scr
     (s/put-string 1 0 "Zendesk Search Coding Challenge" {:styles #{:bold}})

     (s/put-string 1 2 "Dataset:")
     (text/field 11 2 dataset (= :dataset focus))

     (s/put-string 4 3 "Field:")
     (text/field 11 3 field (= :field focus))

     (s/put-string 4 4 "Query:")
     (text/field 11 4 query (= :query focus))

     (draw-users)

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
