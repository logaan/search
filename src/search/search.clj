(ns search.search)

(def results
  (memoize
   (fn [{:keys [dataset field query data]}]
     (let [dataset (data dataset)]
       (vec
        (if dataset
          (filter (fn [row] (= query (str (row field))))
                  @dataset)))))))
