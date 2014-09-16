(ns stl2014.keypair
  (:require [clojure.core.typed :refer [ann defalias HMap]]
            [stl2014.encrypt :refer [encrypt RawKeyPair EncryptedKeyPair]]))

(ann encrypt-keypair [RawKeyPair -> EncryptedKeyPair])
(defn encrypt-keypair [{:keys [private-key] :as keypair}]
  (assoc (dissoc keypair :private-key)
    :encrypted-private-key (encrypt private-key)))