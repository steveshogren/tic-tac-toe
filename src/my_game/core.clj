(ns my-game.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def empty-board [[" " " " " "] [" " " " " "] [" " " " " "]])

(defn draw-board [m]
  (seq (map println m))
  (println "test"))

(defn next-player [p] (if (= p "X") "O" "X"))
(defn player-wins? [m] false)

(defn mark-cell [m c r contents]
  (let [c (Integer/parseInt c)
        r (Integer/parseInt r)]
    (update-in m [c r] (fn [_] contents))))


(defn start-game []
  (println "Start Game!")
  (loop [m empty-board
         player "X"]
    ;; DISPLAY OUTPUT
    (draw-board m)
    (println "Player " player " move:")
    ;; RECEIVE INPUT
    (let [[c r] (.split (read-line) " ")
          ;; MODIFY BOARD
          m (mark-cell m c r player)]
      (if (player-wins? m)
        (println "Player " player " Wins!")
        ;; LOOP AGAIN WITH NEW BOARD AND DIFFERENT PLAYER
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
