(defproject stardriver "0.1.0-SNAPSHOT"
  :description "Drives a Star - or is it an Asterisk?"
  :url "https://github.com/l3nz/stardriver"
  :license {:name "Apache License V2" }
  :dependencies [
        [org.clojure/clojure "1.6.0"]
        [io.netty/netty-all "4.0.25.Final"]
        [com.fasterxml.jackson.core/jackson-databind "2.5.1"]
        [gloss "0.2.4"]

  ]
  :main ^:skip-aot stardriver.core
  :target-path "target/%s"
  :plugins [[lein-marginalia "0.8.0"]]
  :profiles {:uberjar {:aot :all}})
