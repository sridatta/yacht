(defproject yacht "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-figwheel "0.5.0-1"]
            [lein-cljsbuild "1.1.3"]]
  :clean-targets ^{:protect false} ["resources/public/js" :target]
  :source-paths ["src" "cljs_src"]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [reagent "0.6.0-alpha"]
                 [matchbox "0.0.8-SNAPSHOT"]]
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [figwheel-sidecar "0.5.0-1"]]
                   :source-paths ["cljs_src" "dev"] }}
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["cljs_src"]
                :figwheel { :on-jsload "yacht.core/start-app" }
                :compiler {:main yacht.core
                           :asset-path "js/out"
                           :output-to "resources/public/js/yacht.js"
                           :output-dir "resources/public/js/out" }}]}
  )
