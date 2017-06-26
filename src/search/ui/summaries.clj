(ns search.ui.summaries
  (:require [search.repositories.common :as common]))

(defmulti header (fn [record] (:type (meta record))))
(defmulti summarise (fn [record] (:type (meta record))))

(def column-widths
  [20 15 9 31])

(def user-format
  (let [[n a r e] column-widths]
    (str "%-" n "s %-" a "s %-" r "s %-" e "s")))

(defmethod header :user [_]
  (format user-format "Name" "Alias" "Role" "Email"))

(defmethod summarise :user [{:strs [name alias email role]}]
  (let [[n a r e] column-widths
        f         common/first-n]
    (format user-format (f name n) (f alias a) (f role r) (f email e))))
