(ns stl2014.ints
  (:require [clojure.core.typed :as t
             :refer [Coll Int ann-form U]]))

(t/ann f [(U '[Int] '[Boolean]) -> (U '[Int] '[Boolean])])
(defn f [m]
  (if (integer? (first m))
    (ann-form m '[Int])
    (ann-form m '[Boolean])))
