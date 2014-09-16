
(ns stl2014.core
  (:require [clojure.core.typed :as t]))

(t/defalias Expr
  (t/Rec [Expr]
     (t/U (t/HMap :mandatory {:op (t/Val :if)
                              :test Expr
                              :then Expr
                              :else Expr})
          (t/HMap :mandatory {:op (t/Val :do)
                              :exprs (t/Vec Expr)}))))

(t/ann walk [Expr -> String])
(defmulti walk :op)

(defmethod walk :if
  [{:keys [test then else]}]
  (str :if (map walk [test then else])))
