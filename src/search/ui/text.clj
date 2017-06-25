(ns search.ui.text
  (:require [lanterna.screen :as s]))

(def field-length 40)

(def text-field-opts
  {:styles #{:underline}})

(defn remove-last [input]
  (if (empty? input)
    ""
    (subs input 0 (dec (count input)))))

(defn input [input key]
  (case key
    :escape      [:exit input]
    :enter       [:next input]
    :tab         [:next input]
    :down        [:next input]
    :reverse-tab [:prev input]
    :up          [:prev input]
    :backspace   [:set (remove-last input)]

    (if (char? key)
      [:set (str input key)]
      [:set input])))

(defn last-n [input length]
  (let [end   (count input)
        start (max (- end length) 0)]
    (subs input start end)))

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
