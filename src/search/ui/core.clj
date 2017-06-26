(ns search.ui.core
  (:require [lanterna.screen :as s]
            [search.ui.text :as text]
            [search.ui.bindings :as bindings]
            [search.repositories.users :as users]
            [search.repositories.tickets :as tickets]
            [search.repositories.organizations :as organizations]
            [search.ui.table :as table]))

(defn search-results [{:keys [dataset field query data]}]
  (let [dataset (data dataset)]
    (vec
     (if dataset
       (filter (fn [row] (= query (str (row field))))
               @dataset)))))

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

(defn draw [{:keys [dataset field query table index table] :as state} scr]
  (let [focus   (fields index)
        records (search-results state)]
    (doto scr
      (s/clear)
      (heading)
      (text-fields dataset field query focus)
      (table/draw records table (= :table focus))
      (bindings/draw)
      (s/redraw))))
