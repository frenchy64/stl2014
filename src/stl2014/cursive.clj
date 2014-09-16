(ns stl2014.cursive
  (:refer-clojure :exclude [defn])
  (:require [clojure.core.typed :refer [U Int defn]]))

(defn pos-or-neg? [n :- Int]
  (or (pos? n) (neg? n)))

(defn nonzero-or-nil? [n :- (U nil Int)]
  (or (pos-or-neg? n) (nil? n)))