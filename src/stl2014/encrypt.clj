(ns stl2014.encrypt
  (:require [clojure.core.typed :refer [ann defalias]]))

(defalias RawKey
  "A raw public or private key"
  String)

(defalias EncryptedKey
  "An encrypted private key"
  String)

(ann encrypt [RawKey -> EncryptedKey])
(defn encrypt [k] k)
