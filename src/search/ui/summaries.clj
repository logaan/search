(ns search.ui.summaries
  (:require [search.repositories.common :as common]))

(defmulti header (fn [record] (:type (meta record))))

(defmulti summarise (fn [record] (:type (meta record))))

;;- user -----------------------------------------------------------------------

(def user-widths
  [20 15 9 31])

(def user-format
  (let [[n a r e] user-widths]
    (str "%-" n "s %-" a "s %-" r "s %-" e "s")))

(defmethod header :user [_]
  (format user-format "Name" "Alias" "Role" "Email"))

(defmethod summarise :user [{:strs [name alias email role]}]
  (let [[n a r e] user-widths
        f         common/first-n]
    (format user-format (f name n) (f alias a) (f role r) (f email e))))

;;- ticket ---------------------------------------------------------------------

(defmethod header :ticket [_]
  "ticket header")

(defmethod summarise :ticket [ticket]
  "ticket row")

;;- organization ---------------------------------------------------------------

(defmethod header :organization [_]
  "organization header")

(defmethod summarise :organization [organization]
  "organization row")
