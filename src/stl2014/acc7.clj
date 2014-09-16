(ns stl2014.acc7
  (:require [clojure.core.typed :as t
             :refer [ann IFn U NonEmptyColl Int
                     defalias Coll Seqable]]))

(defalias NInts
  "nil or a persistent collection of integers"
  (U nil (Seqable Int)))

(ann summarise (IFn [NInts -> Int]
                    [NInts Int -> Int]))
(defn summarise
  ([nseq] (summarise nseq 0))
  ([nseq acc] (if (seq nseq)
                (* (summarise (next nseq) (inc acc))
                   (first nseq))
                42)))

;(summarise [42 22 33])