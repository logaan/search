(ns search.ui.text
  (:require [lanterna.screen :as s]))

(def field-length 40)

(def text-field-opts
  {:styles #{:underline}})

(defn remove-last [text]
  (if (empty? text)
    ""
    (subs text 0 (dec (count text)))))

(defn input [text key]
  (case key
    :escape      [:exit text]
    :enter       [:next text]
    :tab         [:next text]
    :down        [:next text]
    :reverse-tab [:prev text]
    :up          [:prev text]
    :backspace   [:set (remove-last text)]

    (if (char? key)
      [:set (str text key)]
      [:set text])))

(defn last-n [text length]
  (let [end   (count text)
        start (max (- end length) 0)]
    (subs text start end)))

(defn blank-str [length]
  (apply str (take length (repeat " "))))

(defn field [scr x y value focused?]
  (let [end-of-text  (+ x (count value))
        end-of-field (+ x field-length)
        cursor-x     (min end-of-text end-of-field)]
    (s/put-string scr x y (blank-str (inc field-length)) text-field-opts)
    (s/put-string scr x y (last-n value field-length) text-field-opts)
    (if focused?
      (s/move-cursor scr cursor-x y))))
