(ns yacht.core
  (:require [reagent.core :as r]
            [matchbox.core :as m]))


(defonce yacht-db (m/connect "https://yacht.firebaseio.com"))
(defonce app-state (r/atom {}))

(def hash-key (r/atom ""))

(defn gen-nonce []
  (js/btoa (* 4294967296 (js/Math.random))))

(defn find-open-key [cb]
  (let [nonce (gen-nonce)]
    (m/deref (m/get-in yacht-db nonce)
             (fn [event-type data]
               (if (nil? data)
                 ;; good to go
                 (cb nonce)
                 ;; retry this
                 (find-open-key cb))))))

(def demo-data {:habits-per-week {}
                :habit-data {}})


(defn dates-for-week [])

(defn week-for-date [])

(defn curr-week [])

(defn create-habit [name type]
  ;; Add habit to week
  )

(defn app [k]
  [:div
   [:h1 (str "User stats for " k)]])

(defn render-app [hk]
  (reset! hash-key hk)
  (set! js/window.location.hash (str "#" hk))
  (r/render [app hk] (js/document.getElementById "app")))

(defn start-app []
  (let [url-key js/document.location.hash]
    (if (not (empty? url-key))
      (render-app (subs url-key 1))
      (find-open-key (fn [k] (render-app k))))))

(aset "onreadystatechange" js/document start-app)
