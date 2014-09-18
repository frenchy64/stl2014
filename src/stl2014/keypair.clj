(ns stl2014.keypair
  (:require [clojure.core.typed :refer [ann defalias HMap]]
            [stl2014.encrypt :refer [encrypt RawKey EncryptedKey]]))

(defalias RawKeyPair
  "A keypair with a raw private key"
  (HMap :mandatory {:public-key RawKey, :private-key RawKey}
        :complete? true))

(defalias EncryptedKeyPair
  "A keypair with an encrypted private key"
  (HMap :mandatory {:public-key RawKey, :encrypted-private-key EncryptedKey}
        :complete? true))

(ann encrypt-keypair [RawKeyPair -> EncryptedKeyPair])
(defn encrypt-keypair [{:keys [private-key] :as keypair}]
  (assoc keypair
    :encrypted-private-key (encrypt private-key)))
