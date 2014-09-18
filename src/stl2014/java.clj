(ns stl2014.java
  (:import (java.io File))
  (:require [clojure.core.typed :as t :refer [ann U]]))

(ann grand-parent [File -> (U nil File)])
(defn grand-parent [^File f]
  (let [p1 (.getParentFile f)]
    (.getParentFile p1)))

#_(ann pparent1 [File -> (U nil File)])
#_(defn pparent1 [^File f]
  (let [p1 (.getParentFile f)]
    (when p1
      (.getParentFile p1))))
