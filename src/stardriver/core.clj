(ns stardriver.core
  (:require
  [aleph.http :as http]
    [byte-streams :as bs]
    [manifold.stream :as s]
    [manifold.deferred :as d]
    [manifold.bus :as bus]
    [clojure.core.async :as a])
  (:gen-class))

;; ## Is this a title?
;; And this looks like a general description.
;;
;; ### So this might be a subtitle?
;; While we are starting out, we won't use core for the moment.

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(def WSAST "ws://192.168.99.100:32768")

;; NON VA CON ASTERIK
(defn ping [wsurl]
(let [conn @(http/websocket-client wsurl)]

  ;;(s/put-all! conn
  ;;  (->> 15 range (map str)))

  (->> conn
    (s/transform (take 1))
    s/stream->seq
    doall)))

;; FUNZIONA
(defn mkConn [wsurl]
  @(http/websocket-client wsurl))


(defn killConn [c]
  (s/close! c))
