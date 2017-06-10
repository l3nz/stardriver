;; Per far girare un test - da instarepl:
;; (use 'stardriver.ami 'stardriver.ami-test 'gloss.io)
;; (basic-buf-decode)


(ns stardriver.ami-test
  (:require [clojure.test :refer :all]
            [stardriver.ami :refer :all]
            [gloss.core :as g]))

(defn to-buffer [str]
  (byte-streams/to-byte-buffer str))

(defn buf-to-str [buf]
  (byte-streams/convert buf String))


(deftest basic-buf-decode
  (testing "basic decoding of value"
    (let [buffer (to-buffer "name: value\nname2: value2\n!")
          decoded-buffer (decode-ami buffer)]

    (is (= {:name "value", :name2 "value2"} decoded-buffer)))))


(deftest basic-buf-encode
  (testing "basic encoding of value"
    (let [inp {:name "value", :name2 "value2"}
          encoded-buffer (encode-ami inp)
          res (buf-to-str encoded-buffer)]
    (is (= "name: value\nname2: value2\n\n" res)))))




