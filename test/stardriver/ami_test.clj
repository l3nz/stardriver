;; Per far girare un test - da instarepl:
;; (use 'stardriver.ami 'stardriver.ami-test 'gloss.io)
;; (basic-buf-decode)


(ns stardriver.ami-test
  (:require [clojure.test :refer :all]
            [stardriver.ami :refer :all]
            [gloss.core :as g]))

(defn to-buffer [str]
  (byte-streams/to-byte-buffer str))


(deftest basic-buf-decode
  (testing "basic decoding of value"
    (let [buffer (to-buffer "name: value\r\nname2: value2\r\n\r\n")
          decoded-buffer (decode-ami buffer)]

    (is (= {:name "value", :name2 "value2"} decoded-buffer)))))





