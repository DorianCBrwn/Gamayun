(ns gamayun.core)

(defn transform-vals [map f]
  "apply given function to all values in a map"
  (reduce-kv (fn [map k v]
               (assoc map k (f v))) {} map))

(defn keywordize-symbols [dm]
  "Turns symbol keys into keywords"
  (reduce-kv (fn [dm k v]
               (assoc dm (keyword k) v)) {} dm ))

(defn contains-arglist [dm]
  (apply dissoc dm (for [[k v] dm :when (not ( contains? v :arglists ))] k)))


(defn remove-vals [ns k]
  "remove keys from specific keys"
  (update-in (deck-map ns) [k] dissoc [:file :line :column :ns :static]))

(defn show-card [dm]
    (println (get-in dm [(rand-nth (keys dm)) :name])))

(defn print-card [dm ck cv]
 (println (filter-map (get-in dm [ck]) [cv])))


(defn deck-map [ns]
  "creates a deck  map data structure based provided of the provided namespace"
  (contains-arglist (keywordize-symbols (transform-vals (ns-map ns) meta))))
