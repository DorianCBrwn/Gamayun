(ns gamayun.core)


(def clj-core-map
  (ns-map 'clojure.core))


(defn transform-vals [map f & rkeys]
  (reduce-kv (fn [map k v]
              (assoc map k (f v))) {} map))


(defn name-kw? [map]
  (contains? map :name))

(defn doc-kw? [map]
  (contains? map :doc))
test
(defn arg-kw? [map]
  (contains? map :arglists))

(defn filter-by-doc [namespace]
  (filter doc-kw? (pull-meta-data namespace)))

(defn filter-by-name [namespace]
  (filter name-kw? (pull-meta-data namespace)))

(defn filter-by-args [namespace]
  (filter arg-kw? (pull-meta-data namespace)))

(defn create-card-num-keywords [namespace]
  (range (count (filter doc-kw? (pull-meta-data namespace)))))

(defn create-card-map [namespace]
  (zipmap (create-card-num-keywords namespace) (filter-by-doc namespace)))
