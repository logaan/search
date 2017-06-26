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

(defn draw [scr records selected-row focus?]
  (let [lines   (range 8 22)
        indexes (range)]
    (s/put-string scr 1 6 (summaries/header (first records)))
    (s/put-string scr 1 7 divider)
    (dorun
     (for [[line index record] (map list lines indexes records)]
       (row scr line focus? (= selected-row index) record)))))
