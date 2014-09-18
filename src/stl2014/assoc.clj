(ns stl2014.assoc
  (:require [clojure.core.typed :refer [ann-form Num Str Sym]]))

(ann-form (assoc {} :a 1 :b "foo" :c 'baz)
          (HMap :mandatory {:a Num
                            :b Str
                            :c Sym}))
