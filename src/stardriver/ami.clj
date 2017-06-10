
;; ## This is a basic encoder for the AMI protocol
;; Taken from example at http://derek.troywest.com/articles/by-example-gloss/

(ns stardriver.ami
    (:require
        [clojure.walk :as wk]
        [gloss.core :as g]
        [gloss.io :as gio]
     )
  (:gen-class))

;; End of normal line
(def ^:const rn "\n")
;; End of stanza
(def ^:const rnrn "!")


(defn output-to-map
  "Encoding [['a' 'b']['c' 'd']] to {:a 'b', :c 'd'}"
  [data]
  (wk/keywordize-keys (into {} data)))

(defn input-to-vector [data]
  (vec (wk/stringify-keys data)))

;; A line is in the format 'Keyword: Value' and we map it to ['keyword' 'value']
(g/defcodec basic-header
  [(g/string :utf-8 :delimiters [": "])
   (g/string :utf-8 :delimiters [rn] :strip-delimiters? true)])

;; The encoder/decoder gets a set of basic headers that ends with rnrn
;; and uses the transformers specified above to match a map
(def basic-rfc5322-headers
  (g/compile-frame
    (g/repeated basic-header
              :delimiters [rnrn]
              :strip-delimiters? true)
    input-to-vector
    output-to-map))


(defn decode-ami
  "Decoding a byte-buffer to a map"
  [buffer]
  (gio/decode basic-rfc5322-headers buffer))

(defn encode-ami
  "Decoding a byte-buffer to a map"
  [block]
  (gio/encode basic-rfc5322-headers block))

