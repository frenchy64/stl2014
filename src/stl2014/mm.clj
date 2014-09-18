(ns stl2014.mm
  (:require [clojure.core.typed :refer [defalias Rec Val
                                        ann
                                        Num]]))

(defalias Expr
  (Rec [Expr]
       (U '{:op (Val :if) :test Expr :then Expr :else Expr}
          '{:op (Val :const) :val Num})))

(ann walk [Expr [Num -> Num] -> Expr])
(defmulti walk (fn [e f] (:op e)))

(defmethod walk :if
  [{:keys [test then else]} f]
  {:op :if
   :test (walk test f)
   :then (walk then f)
   :else (walk else f)})

(defmethod walk :const
  [{:keys [val]} f]
  {:op :const :val (f val)})

(walk {:op :if 
       :test {:op :const :val 1}
       :then {:op :const :val 2}
       :else {:op :const :val 3}}
      inc)
