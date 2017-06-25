(ns search.ui.text
  (:require [lanterna.screen :as s]))

(def text-field-opts
  {:styles #{:underline}})

(defn remove-last [input]
  (if (empty? input)
    ""
    (subs input 0 (dec (count input)))))

(defn input [input key]
  (case key
    :enter       [:next input]
    :tab         [:next input]
    :escape      [:exit input]
    :reverse-tab [:prev input]
    :backspace   [:set (remove-last input)]
    [:set (str input key)]))

(defn last [input length]
  (let [end   (count input)
        start (max (- end length) 0)]
    (subs input start end)))

(def field-length 19)

(defn field [scr x y value focused?]
  (let [end-of-text  (+ x (count value))
        end-of-field (+ x field-length)
        cursor-x     (min end-of-text end-of-field)]
    (s/put-string scr x y "                    " text-field-opts)
    (s/put-string scr x y (last value field-length) text-field-opts)
    (if focused?
      (s/move-cursor scr cursor-x y))))
