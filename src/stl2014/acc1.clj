(ns stl2014.acc1
  (:require [clojure.core.typed :as t]))

(defn summarise
  ([nseq] (summarise nseq 0))
  ([nseq acc] (if nseq
                (* (summarise (rest nseq) (inc acc))
                   (first nseq))
                42)))

;(summarise [42 22 33])