(ns koans.03-lists
  (:require [koan-engine.core :refer :all]))

(def characters (map #(-> % char str symbol keyword) (range)))

(def a-to-z (take 26 (drop (.indexOf characters :a) characters)))

;; (def a-to-z (->> characters (drop (.indexOf characters :a)) (take 26)))

(def a-to-z-cycle (cycle a-to-z))

(meditations
  "Lists can be expressed by function or a quoted form"
  (= '(1 2 3 4 5) (list 1 2 3 4 5))

  "BONUS - or generate the list"
  (= (map inc (range 5)) (list 1 2 3 4 5))

  "They are Clojure seqs (sequences), so they allow access to the first"
  (= 1 (first '(1 2 3 4 5)))

  "As well as the rest"
  (= '(2 3 4 5) (rest '(1 2 3 4 5)))

  "Count your blessings"
  (= 3 (count '(dracula dooku chocula)))

  "Before they are gone"
  (= 0 (count '()))

  "The rest, when nothing is left, is empty"
  (= '() (rest '(100)))

  "Construction by adding an element to the front is easy"
  (= '(:a :b :c :d :e) (cons :a '(:b :c :d :e)))

  " TAKE 2! Construction by adding an element to the front is easy"
  (= (take 5 a-to-z) (cons :a '(:b :c :d :e)))

  "Conjoining an element to a list isn't hard either"
  (= '(:e :a :b :c :d) (conj '(:a :b :c :d) :e))

  " TAKE 2! Conjoining an element to a list isn't hard either"
  (= (cons :e (take 4 a-to-z)) (conj '(:a :b :c :d) :e))

  "You can use a list like a stack to get the first element"
  (= :a (peek '(:a :b :c :d :e)))

  "Or the others"
  (= '(:b :c :d :e) (pop '(:a :b :c :d :e)))

  "TAKE 2! Or the others"
  (= (->> a-to-z (take 5) (drop 1)) (pop '(:a :b :c :d :e)))

  "But watch out if you try to pop nothing"
  (= "No dice!" (try
                  (pop '())
                  (catch IllegalStateException e
                    "No dice!")))

  "The rest of nothing isn't so strict"
  (= '() (try
          (rest '())
          (catch IllegalStateException e
            "No dice!"))))
