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

(defn field [scr x y value focused?]
  (let [text-start (inc x)]
    (s/put-string scr x y "                    " text-field-opts)
    (s/put-string scr text-start y value text-field-opts)
    (if focused?
      (s/move-cursor scr (+ text-start (count value)) y))))
