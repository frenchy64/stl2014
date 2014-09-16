(ns stl2014.encrypt
  (:require [clojure.core.typed :refer [ann defalias]]))

(defalias RawKey
  "A raw public or private key"
  String)

(defalias EncryptedKey
  "An encrypted private key"
  String)

(defalias RawKeyPair
  "A keypair with a raw private key"
  (HMap :mandatory {:public-key RawKey, :private-key RawKey}
        :complete? true))

(defalias EncryptedKeyPair
  "A keypair with an encrypted private key"
  (HMap :mandatory {:public-key RawKey, :encrypted-private-key EncryptedKey}
        :complete? true))

(ann encrypt [RawKey -> EncryptedKey])
(defn encrypt [k] k)