(ns stl2014.acc4
  (:require [clojure.core.typed :as t
             :refer [ann IFn U NonEmptyColl Int
                     defalias]]))

(defalias NInts
  "nil or a non-empty persistent collection of integers"
  (U nil (NonEmptyColl Int)))

(ann summarise (IFn [NInts -> Int]
                    [NInts Int -> Int]))
(defn summarise
  ([nseq] (summarise nseq 0))
  ([nseq acc] (if nseq
                (* (summarise (next nseq) (inc acc))
                   (first nseq))
                42)))

;(summarise [42 22 33])