(ns stl2014.protocol
  (:refer-clojure :exclude [defprotocol deftype])
  (:require [clojure.core.typed :refer [deftype defprotocol Num]]))

(defprotocol Adder
  (add [this y :- Num] :- Adder))

(deftype A [x :- Num]
  Adder
  (add [this y] (+ x y)))

(add (A. 1) 34)
