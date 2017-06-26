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

(defn draw [scr records {:keys [selected expanded] :as table} focus?]
  (if expanded
    (s/put-string scr 1 6 "expanded")
    (rows scr records selected focus?)))

(defn move-row [state direction]
  (update-in state [:selected] direction))

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
