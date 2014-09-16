(ns stl2014.acc2
  (:require [clojure.core.typed :as t
             :refer [ann IFn U NonEmptyColl Int]]))

(ann summarise (IFn [(U nil (NonEmptyColl Int)) -> Int]
                    [(U nil (NonEmptyColl Int)) Int -> Int]))
(defn summarise
  ([nseq] (summarise nseq 0))
  ([nseq acc] (if nseq
                (* (summarise (rest nseq) (inc acc))
                   (first nseq))
                42)))

;(summarise [42 22 33])