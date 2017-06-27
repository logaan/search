(ns search.ui.table
  (:require [lanterna.screen :as s]
            [search.ui.no-results :as no-results]
            [search.ui.summaries :as summaries]
            [search.search :as search]))

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
  (if record (summaries/detail scr record)))

(defn draw [scr records {:keys [selected expanded scroll]} focus?]
  (let [scrolled (vec (drop scroll records))]
    (if (empty? records)
     (no-results/draw scr)
     (if expanded
       (detail scr (get scrolled selected))
       (rows scr scrolled selected focus?)))))

(defn move-row [state field direction]
  (let [last-result (dec (count (search/results state)))]
    (update-in state [:table field]
              (fn [row-number]
                (->> (direction row-number)
                     (max 0)
                     (min 13 last-result))))))

(defn prev-row [state]
  (move-row state :selected dec))

(defn next-row [state]
  (move-row state :selected inc))

(defn toggle-expand [state]
  (update-in state [:table :expanded] not))

(defn scroll-down [state]
  (move-row state :scroll inc))

(defn scroll-up [state]
  (move-row state :scroll dec))

(defn input [state key]
  (case key
    :escape      [:exit state]
    :enter       [:set (toggle-expand state)]
    :tab         [:next state]
    :reverse-tab [:prev state]
    :up          [:set (scroll-up state)]
    \k           [:set (prev-row state)]
    :down        [:set (scroll-down state)]
    \j           [:set (next-row state)]
    [:set state]))
