(ns gamayun.core)

(defn transform-vals [map f]
  "apply given function to all values in a map"
  (reduce-kv (fn [map k v]
               (assoc map k (f v))) {} map))

(defn keywordize-symbols [m]
  "Turns symbol keys into keywords"
  (reduce-kv (fn [m k v]
               (assoc m (keyword k) v)) {} m ))

(defn contains-arglist [m]
  (apply dissoc m (for [[k v] m :when (not ( contains? v :arglists ))] k)))


(defn remove-vals [ns k]
  "remove keys from specific keys"
  (update-in (deck-map ns) [k] dissoc [:file :line :column :ns :static]))

(defn show-card [d]
    (println (get-in d [(rand-nth (keys d)) :name])))

(defn deck-map [ns]
  "creates a deck  map data structure based provided of the provided namespace"
  (contains-arglist (keywordize-symbols (transform-vals (ns-map ns) meta))))
