(ns search.ui.bindings
  (:require [lanterna.screen :as s]))

(def key-style
  {:fg :white
   :bg :cyan})

(defn draw [scr]
  (doto scr
    (s/put-string 1 23 "    Quit       Next     Next           Previous")
    (s/put-string 1 23 "Esc" key-style)
    (s/put-string 10 23 "Enter" key-style)
    (s/put-string 21 23 "Tab" key-style)
    (s/put-string 30 23 "Shift-Tab" key-style)))
