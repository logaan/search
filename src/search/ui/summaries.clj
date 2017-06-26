(ns search.ui.summaries
  (:require [lanterna.screen :as s]
            [search.repositories.common :as common]
            [clojure.string :as str]))

(def f common/first-n)

(defmulti header (fn [record] (:type (meta record))))

(defmulti summarise (fn [record] (:type (meta record))))

(defmulti detail (fn [_ record] (:type (meta record))))

(defn detail-map [scr record]
  (dorun
   (for [[line [key value]] (map list (range 6 22) record)]
     (let [output (-> (format "%s: %s" key value)
                      (common/first-n 78))]
       (s/put-string scr 1 line output)))))

(defn column-summary [values widths]
  (let [pattern (str/join " " (map #(str "%-" % "s") widths))
        trimmed (map common/first-n values widths)]
    (apply (partial format pattern) trimmed)))

;;- user -----------------------------------------------------------------------
(defmethod header :user [_]
  (format user-format "Name" "Alias" "Role" "Email"))

(defmethod summarise :user [{:strs [name alias role email]}]
  (column-summary [name alias role email] [20 15 9 31]))

(defmethod detail :user [scr user]
  (detail-map scr user))

;;- ticket ---------------------------------------------------------------------
(defmethod header :ticket [_]
  (format ticket-format "Type" "Priority" "Status" "Subject"))

(defmethod summarise :ticket [{:strs [type priority status subject]}]
  (column-summary [type priority status subject] [9 9 8 49]))

(defmethod detail :ticket [scr ticket]
  (detail-map scr ticket))

;;- organization ---------------------------------------------------------------
(defmethod header :organization [_]
  (format organization-format "Name" "Details" "Domain Names"))

(defmethod summarise :organization [{:strs [name details domain_names]}]
  (let [joined-domains (str/join ", " domain_names)]
    (column-summary [name details joined-domains] [15 15 46])))

(defmethod detail :organization [scr organization]
  (detail-map scr organization))
