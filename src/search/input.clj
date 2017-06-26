(ns search.input
  (:require [lanterna.screen :as s]
            [search.ui.core :as ui]
            [search.ui.text :as text]
            [search.ui.table :as table]))

(defn move-index [index direction]
  (mod (direction index) (count ui/fields)))

(def field-types
  {:dataset text/input
   :field   text/input
   :query   text/input
   :table   table/input})

(defn perform-action [state action focus value]
  (case action
    :set  (assoc state focus value)
    :next (update-in state [:index] move-index inc)
    :prev (update-in state [:index] move-index dec)))

(defn listen [scr initial-state]
  (loop [state initial-state]
    (ui/draw state scr)
    (let [focus          (ui/fields (:index state))
          handler        (field-types focus)
          input          (s/get-key-blocking scr)
          [action value] (handler (state focus) input)]
      (if (not= :exit action)
        (recur (perform-action state action focus value))))))
