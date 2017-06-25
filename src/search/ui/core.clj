(ns search.ui.core
  (:require [lanterna.screen :as s]))

;;- text field -----------------------------------------------------------------

(def text-field-opts
  {:styles #{:underline}})

(defn remove-last [input]
  (if (empty? input)
    ""
    (subs input 0 (dec (count input)))))

(defn text-input [input key]
  (case key
    :enter       [:next input]
    :tab         [:next input]
    :escape      [:exit input]
    :reverse-tab [:prev input]
    :backspace   [:set (remove-last input)]
    [:set (str input key)]))

(defn text-field [scr x y value focused?]
  (let [text-start (inc x)]
    (s/put-string scr x y "                    " text-field-opts)
    (s/put-string scr text-start y value text-field-opts)
    (if focused?
      (s/move-cursor scr (+ text-start (count value)) y))))

;;- specific drawing -----------------------------------------------------------

(def fields
  [:dataset :field :query :table])

(defn draw [{:keys [dataset field query table index]} scr]
  (let [focus (fields index)]
    (doto scr
     (s/clear)

     (s/put-string 2 1 "Zendesk Search Coding Challenge" {:styles #{:bold}})

     (s/put-string 2 3 "Dataset:")
     (text-field 11 3 dataset (= :dataset focus))

     (s/put-string 4 5 "Field:")
     (text-field 11 5 field (= :field focus))

     (s/put-string 4 7 "Query:")
     (text-field 11 7 query (= :query focus))

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
          [action value] (text-input focus-value
                                     (s/get-key-blocking scr))]
      (if (= :exit action)
        (println "exiting")
        (recur
         (case action
           :set  (assoc state focus value)
           :next (update-in state [:index] move-index inc)
           :prev (update-in state [:index] move-index dec)))))))

(defn start []
  (let [scr (s/get-screen)]
    (s/in-screen scr (input-loop scr))))

(comment

  (start)
  
  )
