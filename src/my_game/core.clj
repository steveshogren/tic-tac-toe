(ns my-game.core
  (:gen-class))

(def empty-map [[" " " " " "] [" " " " " "] [" " " " " "]])

(defn draw-board [m]
  (seq (map println m))
  (println "test"))

(defn mark-cell [m c r contents]
  (let [c (Integer/parseInt c)
        r (Integer/parseInt r)
        old-row (get m c)
        new-row (assoc old-row c contents)]
    (assoc m r new-row)))

(defn next-player [p]
  (if (= p "X") "O" "X"))

(defn player-wins? [m]
  false)

(defn start-game []
  (println "Start Game!")
  (loop [m empty-map
         player "X"]
    (draw-board m)
    (println "Player " player " move:")
    (let [input (read-line)
          [c r] (.split input " ")
          m (mark-cell m c r player)]
      (if (player-wins? m)
        (println "Player " player " Wins!")
        (recur m (next-player player))))))

(defn -main [& args]
  (start-game))
