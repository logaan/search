(ns search.ui.text
  (:require [lanterna.screen :as s]
            [search.ui.fields :as fields]))

(def field-length 40)

(def text-field-opts
  {:styles #{:underline}})

(defn remove-last [text]
  (if (empty? text)
    ""
    (subs text 0 (dec (count text)))))

(defn focus [state]
  (fields/order (:index state)))

(defn find-text [state]
  (state (focus state)))

(defn set-text [state new-text]
  (assoc state (focus state) new-text))

(defn input [state key]
  (let [text (find-text state)]
    (case key
      :escape      [:exit state]
      :enter       [:next state]
      :tab         [:next state]
      :down        [:next state]
      :reverse-tab [:prev state]
      :up          [:prev state]
      :backspace   [:set (set-text state (remove-last text))]

      (if (char? key)
        [:set (set-text state (str text key))]
        [:set state]))))

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
