(ns gamayun.core)


(defn -main
  [& args]
  (println "Hello, World!"))

(def id-count 0)

(defn def-keyvalue
  [m fn-name]
 (:doc (meta (symbol (str "#'clojure.core/" (get m fn-name))))))


(def deck-structure {:deck {:card {:fm-name "map"
                                   :fm-def "Returns a lazy sequence consisting of the result of applying f to
                                            the set of first items of each coll, followed by applying f to the
                                            set of second items in each coll, until any one of the colls is
                                            exhausted."
                                   :fm-args "(map f coll)"}}})
(defn add-card [map card]
 (assoc deck-structure :deck card))

(defn remove-card [card]
 (dissoc deck-structure card))


(def example-card {:id (inc id-count)
                   :fuctname 'map
                   :args '[funct (coll & rest)]
                   :def (def-keyvalue example-card :fuctname)
                   :example '(map + [4 5 6] [1 2 3])})

(def example-deck [ example-card example-card example-card])

(defn pull-meta-data [nspace]
  (map meta (vals (ns-map nspace))))

(defn pull-fm-name [nspace]
  (remove nil? (map :name (pull-meta-data nspace))))

(defn pull-fm-args [nspace]
  (remove nil? (map :arglists (pull-meta-data nspace))))

(defn pull-fm-docs [nspace]
  (remove nil? (map :doc (pull-meta-data nspace))))

(defn name-kw? [map]
  (contains? map :name))

(defn doc-kw? [map]
  (contains? map :doc))

(defn filter-by-doc [namespace]
  (filter doc-kw? (pull-meta-data namespace)))

(defn create-card-num-keywords [namespace]
  (range (count (filter doc-kw? (pull-meta-data namespace)))))

(defn create-card-map [namespace]
  (zipmap (create-card-num-keywords namespace) (filter-by-doc namespace)))
