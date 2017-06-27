(ns search.ui.summaries
  (:require [lanterna.screen :as s]
            [search.repositories.common :as common]
            [clojure.string :as str]))

(defmulti header (fn [record] (:type (meta record))))

(defmulti summarise (fn [record] (:type (meta record))))

(defmulti detail (fn [_ record] (:type (meta record))))

(defn detail-map [scr record]
  (s/move-cursor scr 0 0)
  (dorun
   (for [[line [key value]] (map list (range 2 22) record)]
     (let [output (-> (format "%16s: %-80s" key value)
                      (common/first-n 78))]
       (s/put-string scr 0 line output)))))

(defn column-summary [values widths]
  (let [pattern (str/join " " (map #(str "%-" % "s") widths))
        trimmed (map common/first-n values widths)]
    (apply (partial format pattern) trimmed)))

;;- user -----------------------------------------------------------------------
(def user-widths [20 15 9 31])

(defmethod header :user [_]
  (column-summary ["Name" "Alias" "Role" "Email"] user-widths))

(defmethod summarise :user [{:strs [name alias role email]}]
  (column-summary [name alias role email] user-widths))

(defmethod detail :user [scr user]
  (detail-map scr user))

;;- ticket ---------------------------------------------------------------------
(def ticket-widths [9 9 8 49])

(defmethod header :ticket [_]
  (column-summary ["Type" "Priority" "Status" "Subject"] ticket-widths))

(defmethod summarise :ticket [{:strs [type priority status subject]}]
  (column-summary [type priority status subject] ticket-widths))

(defmethod detail :ticket [scr ticket]
  (detail-map scr ticket))

;;- organization ---------------------------------------------------------------
(def organization-widths [15 15 46])

(defmethod header :organization [_]
  (column-summary ["Name" "Details" "Domain Names"] organization-widths))

(defmethod summarise :organization [{:strs [name details domain_names]}]
  (let [joined-domains (str/join ", " domain_names)]
    (column-summary [name details joined-domains] organization-widths)))

(defmethod detail :organization [scr organization]
  (detail-map scr organization))
