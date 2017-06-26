(ns search.ui.table
  (:require [lanterna.screen :as s]
            [search.ui.summaries :as summaries]))

(def divider
  "------------------------------------------------------------------------------")

(defn row [scr line focus? selected? record]
  (let [style (if selected? {:bg :white :fg :black})]
    (s/put-string scr 1 line (summaries/summarise record) style)
    (if (and focus? selected?)
      (s/move-cursor scr 0 line))))

(defn rows [scr records selected focus?]
  (let [lines   (range 8 22)
        indexes (range)]
    (s/put-string scr 1 6 (summaries/header (first records)))
    (s/put-string scr 1 7 divider)
    (dorun
     (for [[line index record] (map list lines indexes records)]
       (row scr line focus? (= selected index) record)))))

(defn detail [scr record]
  (summaries/detail scr record))

(def user-fields
  ["_id active alias created_at email external_id last_login_at locale name"
   "organization_id phone role shared signature suspended tags timezone url"
   "verified"])

(def ticket-fields
  ["_id assignee_id created_at description due_at external_id has_incidents"
   "organization_id priority status subject submitter_id tags type url via"])

(def organization-fields
  ["_id created_at details domain_names external_id name shared_tickets tags"
   "url"])

(defn put-lines [scr x y-start lines]
  (dorun
   (for [[line y] (map list lines (drop y-start (range)))]
     (s/put-string scr x y line))))

(defn sub-head [scr x y text]
  (s/put-string scr x y text {:styles #{:bold}}))

(defn no-results [scr]
  (s/put-string scr 1 6 "Your search returned no results.")
  (s/put-string scr 1 8 "Available datasets and fields are:")
  (sub-head scr 1 10 "users")
  (put-lines scr 2 11 user-fields)
  (sub-head scr 1 15 "tickets")
  (put-lines scr 2 16 ticket-fields)
  (sub-head scr 1 19 "organizations")
  (put-lines scr 2 20 organization-fields))

(defn draw [scr records {:keys [selected expanded] :as table} focus?]
  (if (empty? records)
    (no-results scr)
    (if expanded
     (detail scr (nth records selected))
     (rows scr records selected focus?))))

(defn move-row [state direction]
  (update-in state [:selected]
             (fn [row-number]
               (->> (direction row-number)
                    (max 0)
                    (min 13)))))

(defn prev-row [state]
  (move-row state dec))

(defn next-row [state]
  (move-row state inc))

(defn toggle-expand [state]
  (update-in state [:expanded] not))

(defn input [state key]
  (case key
    :escape      [:exit state]
    :enter       [:set (toggle-expand state)]
    :tab         [:next state]
    :reverse-tab [:prev state]
    :up          [:set (prev-row state)]
    \k           [:set (prev-row state)]
    :down        [:set (next-row state)]
    \j           [:set (next-row state)]
    [:set state]))
