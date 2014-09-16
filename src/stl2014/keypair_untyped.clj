(ns stl2014.keypair-untyped
  (:require [stl2014.encrypt :refer [encrypt]]))

(defn encrypt-keypair [{:keys [private-key] :as keypair}]
  (assoc (dissoc keypair :private-key)
    :encrypted-private-key (encrypt private-key)))