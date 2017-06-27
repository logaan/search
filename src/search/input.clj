(ns search.input
  (:require [lanterna.screen :as s]
            [search.ui.core :as ui]
            [search.ui.text :as text]
            [search.ui.table :as table]
            [search.ui.fields :as fields]))

(def field-types
  {:dataset text/input
   :field   text/input
   :query   text/input
   :table   table/input})

(defn move-index [index direction]
  (mod (direction index) fields/number))

(defn perform-action [state action value]
  (case action
    :set  value
    :next (update-in state [:index] move-index inc)
    :prev (update-in state [:index] move-index dec)))

(defn listen [scr initial-state]
  (loop [state initial-state]
    (ui/draw state scr)
    (let [focus          (fields/order (:index state))
          handler        (field-types focus)
          input          (s/get-key-blocking scr)
          [action value] (handler state input)]
      (if (not= :exit action)
        (recur (perform-action state action value))))))
