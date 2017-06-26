(ns search.ui.summaries
  (:require [search.repositories.common :as common]
            [clojure.string :as str]))

(def f common/first-n)

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
  (let [[n a r e] user-widths]
    (format user-format (f name n) (f alias a) (f role r) (f email e))))

;;- ticket ---------------------------------------------------------------------

(def ticket-widths
  [9 9 8 49])

(def ticket-format
  (let [[t p st sj] ticket-widths]
    (str "%-" t "s %-" p "s %-" st "s %-" sj "s")))

(defmethod header :ticket [_]
  (format ticket-format "Type" "Priority" "Status" "Subject"))

(defmethod summarise :ticket [{:strs [type priority status subject]}]
  (let [[t p st sj] ticket-widths]
    (format ticket-format (f type t) (f priority p) (f status st) (f subject sj))))

;;- organization ---------------------------------------------------------------

(def organization-widths
  [15 15 46])

(def organization-format
  (let [[n d dns] organization-widths]
    (str "%-" n "s %-" d "s %-" dns "s")))

(defmethod header :organization [_]
  (format organization-format "Name" "Details" "Domain Names"))

(defmethod summarise :organization [{:strs [name details domain_names]}]
  (let [[n d dns] organization-widths
        joined-domains (str/join ", " domain_names)]
    (format organization-format (f name n) (f details d) (f joined-domains dns))))
