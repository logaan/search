(ns search.core
  (:require [search.ui.core :as ui]))

(defn -main [& args]
  (ui/start :text))
