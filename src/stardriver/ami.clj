(ns stardriver.ami
    (:require
        [clojure.walk :as wk]
        [gloss.core :as g]
        [gloss.io :as gio]
     )
  (:gen-class))


;; This is a basic encoder for the AMI protocol
;; Taken from example at http://derek.troywest.com/articles/by-example-gloss/

(def ^:const rn "\r\n")
(def ^:const rnrn "\r\n\r\n")

(defn output-to-map [data]
  (wk/keywordize-keys (into {} data)))

(defn input-to-vector [data]
  (vec (wk/stringify-keys data)))

(g/defcodec basic-header
  [(g/string :utf-8 :delimiters [": "]) (g/string :utf-8 :delimiters [rn rnrn])])

(def basic-rfc5322-headers
  (g/compile-frame
    (g/repeated basic-header
              :delimiters [rnrn]
              :strip-delimiters? false)
    input-to-vector
    output-to-map))


(defn decode-ami [buffer]
  (gio/decode basic-rfc5322-headers buffer))
