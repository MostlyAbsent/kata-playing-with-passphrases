(ns kata-playing-with-passphrases.kata-playing-with-passphrasest
  (:require
   [clojure.string :as str]))

(def alpha "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def digits "0123456789")

(defn shift-char [c n]
      (as-> (rem (+ n c) 26) $
        (subs alpha $ (inc $))))

(defn nines-compliment [d]
  (->> d
      int
      (- 9)))

(defn permute-char [idx c n]
  (let [a (str/index-of alpha c)
        d (str/index-of digits c)]
    (cond
      (and a (odd? idx)) (str/lower-case (shift-char a n))
      a (shift-char a n)
      d (nines-compliment d)
      :else c)))

(defn play-pass [s n]
  (-> (into []
            (map-indexed #(permute-char % %2 n) s))
      reverse
      str/join))
