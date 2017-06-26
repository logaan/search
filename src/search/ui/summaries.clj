(ns search.ui.summaries
  (:require [search.repositories.common :as common]
            [clojure.string :as str]))

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

(def organization-widths
  [15 15 46])

(def organization-format
  (let [[n d dns] organization-widths]
    (str "%-" n "s %-" d "s %-" dns "s")))

(defmethod header :organization [_]
  (format organization-format "Name" "Details" "Domain Names"))

(defmethod summarise :organization [{:strs [name details domain_names]}]
  (let [[n d dns] organization-widths
        f         common/first-n
        joined-domains (str/join ", " domain_names)]
    (format organization-format (f name n) (f details d) (f joined-domains dns))))
