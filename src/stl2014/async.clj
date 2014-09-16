(ns stl2014.async
  (:refer-clojure :exclude [loop doseq])
  (:require [clojure.core.typed :refer [ann loop doseq Any] :as t]
            [clojure.core.typed.async :refer [go chan Chan TimeoutChan] :as tasync]
            [clojure.core.async.impl.protocols :as asyncp]
            [clojure.core.async :as async :refer [<! >! <!! timeout alt!]]))

(t/defalias Kind
  "Id for search"
  t/Kw)

(t/defalias Query
  "A google query"
  t/Str)

(t/defalias QueryPair
  "A pair of search kind and query string."
  '[Kind Query])

(t/defalias FakeSearch [(Chan QueryPair) Query -> (Chan QueryPair)])

(ann fake-search [Kind -> FakeSearch])
(defn fake-search [kind]
  (fn [c query]
    (go :- QueryPair
        (<! (timeout (rand-int 100)))
        (>! c [kind query]))))

(t/ann-many FakeSearch
            web1 web2 image1 image2 video1 video2)

(def web1 (fake-search :web1))
(def web2 (fake-search :web2))
(def image1 (fake-search :image1))
(def image2 (fake-search :image2))
(def video1 (fake-search :video1))
(def video2 (fake-search :video2))

(ann fastest [Query FakeSearch * -> (Chan QueryPair)])
(defn fastest [query & replicas]
  (let [c (chan :- QueryPair)]
    (doseq [replica :- FakeSearch, replicas]
      (replica c query))
    c))

(t/defalias QueryPair (t/U nil '[Kind Query]))
(t/defalias QueryPairVec (t/Vec QueryPair))

(ann google [Query -> (Chan QueryPairVec)])
(defn google [query]
  (let [c (chan :- QueryPair)
        t (timeout 80)]
    (go (>! c (<! (fastest query web1 web2))))
    (go (>! c (<! (fastest query image1 image2))))
    (go (>! c (<! (fastest query video1 video2))))
    (go :- (Chan (t/Vec QueryPair))
        (loop [i :- t/Int 0
               ret :- QueryPairVec, []]
          (if (= i 3)
            ret
            (recur (inc i) (conj ret (t/ann-form (alt! [c t] ([v] v))
                                                 QueryPair))))))))

(fn [] (<!! (google "clojure")))
















































