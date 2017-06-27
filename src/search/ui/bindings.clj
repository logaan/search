(ns search.ui.bindings
  (:require [lanterna.screen :as s]))

(def key-style
  {:fg :white
   :bg :cyan})

(defn draw [scr]
  (doto scr
    (s/put-string 1 23 "    Quit       Next     Next           Previous                                 ")
    (s/put-string 1 23 "Esc" key-style)
    (s/put-string 10 23 "Enter" key-style)
    (s/put-string 21 23 "Tab" key-style)
    (s/put-string 30 23 "Shift-Tab" key-style)))

(defn table [scr]
  (doto scr
    (s/put-string 1 23 "    Quit       Open     Next           Previous         Scroll     Select       ")
    (s/put-string 1 23 "Esc" key-style)
    (s/put-string 10 23 "Enter" key-style)
    (s/put-string 21 23 "Tab" key-style)
    (s/put-string 30 23 "Shift-Tab" key-style)
    (s/put-string 49 23 "Up/Down" key-style)
    (s/put-string 64 23 "j/k" key-style)))

(defn detail [scr]
  (doto scr
    (s/put-string 1 23 "    Quit       Close     Next           Previous         Scroll     Select       ")
    (s/put-string 1 23 "Esc" key-style)
    (s/put-string 10 23 "Enter" key-style)
    (s/put-string 22 23 "Tab" key-style)
    (s/put-string 31 23 "Shift-Tab" key-style)
    (s/put-string 50 23 "Up/Down" key-style)
    (s/put-string 65 23 "j/k" key-style)))
