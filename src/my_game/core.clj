(ns my-game.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def empty-map [[" " " " " "] [" " " " " "] [" " " " " "]])

(defn draw-board [m]
  (seq (map println m))
  (println "test"))

(defn next-player [p] (if (= p "X") "O" "X"))
(defn player-wins? [m] false)

(defn mark-cell [m c r contents]
  (let [c (Integer/parseInt c)
        r (Integer/parseInt r)
        old-row (get m r)
        new-row (assoc old-row c contents)]
    (assoc m r new-row)))

(defn start-game []
  (println "Start Game!")
  (loop [m empty-map
         player "X"]
    (draw-board m)
    (println "Player " player " move:")
    (let [input (read-line)
          [c r] (.split input " ")
          ;; MUTATION BY ANY OTHER NAME...
          m (mark-cell m c r player)]
      (if (player-wins? m)
        (println "Player " player " Wins!")
        (recur m (next-player player))))))

(def cli-options
  [["-p" "--play" "Play game"
    :id :play]
   ["-h" "--help" "Help"
    :id :help]])

(defn -main [& args]
  (let [opts (:options (parse-opts args cli-options))]
    (cond (:play opts) (start-game)
          (:help opts) (println "Use -p to play game"))))
