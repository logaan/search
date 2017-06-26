(ns search.ui.table
  (:require [lanterna.screen :as s]
            [search.repositories.users :as users]))

(def divider
  "------------------------------------------------------------------------------")

(defn row [scr line focus? selected? user]
  (let [style (if selected? {:bg :white :fg :black})]
    (s/put-string scr 1 line (users/summarise user) style)
    (if (and focus? selected?)
      (s/move-cursor scr 0 line))))

(defn draw [scr selected-user focus?]
  (let [users   (users/load-json)
        lines   (range 8 22)
        indexes (range)]
    (s/put-string scr 1 6 users/header)
    (s/put-string scr 1 7 divider)
    (dorun
     (for [[line index user] (map list lines indexes users)]
       (row scr line focus? (= selected-user index) user)))))
