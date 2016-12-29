(ns gamayun.core)


(defn -main
  [& args]
  (println "Hello, World!"))

(def id-count 0)

(defn def-keyvalue
  [m fn-name]
 (:doc (meta (var (str "#'clojure.core/" (get m fn-name)))))

(def deck-structure
 {:deck {:card {:fm-name
                       :fm-def
                       :fm-args}})
(defn add-card
 (assoc-in deck-structure


(def example-card {:id (inc id-count)
                   :fuctname 'map
                   :args '[funct (coll & rest)]
                   :def (def-keyvalue example-card :fuctname)
                   :example '(map + [4 5 6] [1 2 3])})

(def example-deck [ example-card example-card example-card])
