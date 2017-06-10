(defproject stardriver "0.1.0-SNAPSHOT"
  :description "Drives a Star - or is it an Asterisk?"
  :url "https://github.com/l3nz/stardriver"
  :license {:name "Apache License V2" }
  :dependencies [
        [org.clojure/clojure "1.7.0"]
        [aleph "0.4.1-beta2"]
        [gloss "0.2.5"]
        [org.clojure/core.async "0.2.374"]
  ]
  :main ^:skip-aot stardriver.core
  :target-path "target/%s"
  :plugins [[lein-marginalia "0.8.0"]]
  :profiles {:uberjar {:aot :all}})
