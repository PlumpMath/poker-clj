(ns poker.core
  (:require [clojure.string :as str]))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s)))

(defn parse-rank
  [rank]
  (case rank
    "T" 10
    "J" 11
    "Q" 12
    "K" 13
    "A" 14
    (parse-int rank)))

(defn parse-card
  "parses a string like \"5S\" and returns a map with :suit and :rank"
  [card]
  (let [[_ val suit] (str/split card #"")
        rank (parse-rank val)]
    {:suit suit
     :rank rank}))

(defn pair?
  [hand]
  (let [just-ranks (map #(:rank (parse-card %)) hand)]
    (some #(= 2 %) (vals (frequencies just-ranks)))))

(def my-hand (map parse-card ["5S" "TH" "5S" "4H" "3C"]))
(frequencies (map :rank my-hand))

