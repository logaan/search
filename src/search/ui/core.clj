(ns search.ui.core
  (:require [lanterna.screen :as s]
            [search.ui.text :as text]
            [search.ui.bindings :as bindings]
            [search.ui.table :as table]))

(def fields
  [:dataset :field :query :table])

(defn heading [scr]
  (s/put-string scr 1 0 "Zendesk Search Coding Challenge" {:styles #{:bold}}))

(defn text-fields [scr dataset field query focus]
  (doto scr
    (s/put-string 1 2 "Dataset:")
    (text/field 11 2 dataset (= :dataset focus))

    (s/put-string 4 3 "Field:")
    (text/field 11 3 field (= :field focus))

    (s/put-string 4 4 "Query:")
    (text/field 11 4 query (= :query focus))))

(defn draw [{:keys [dataset field query table index row]} scr]
  (let [focus (fields index)]
    (doto scr
     (heading)
     (text-fields dataset field query focus)
     (table/draw row (= :table focus))
     (bindings/draw)
     (s/redraw))))
