(ns stl2014.gen-vec
  (:require [clojure.core.typed :refer [U Num Vec Int ann]
             :as t]))

(ann gen-vec [(U Num (Vec Int)) -> (Vec Int)])
(defn gen-vec [n-or-v]
  (if (number? n-or-v)
    (vec (range n-or-v))
    n-or-v))

(gen-vec 5)
(gen-vec [1 2 3 4])
