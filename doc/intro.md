# Introduction to search

Hi there. Thanks for reviewing my code. I've written some notes here that will
hopefully make your task a little easier.

## Clojure

If you're new to Clojure these points cover some issues that I've noticed trip
up beginners.

* The parenthesis are on the outside of the function call. `printLn("Hello
  World")` is written as `(println "Hello World")` in Clojure.
* Clojure code is written inside out. So if you had `foo(1).bar(2, 3).baz(4)` in
  Clojure it would be written as `(baz (bar (foo 1) 2 3) 4)`.
* Inside out code can be hard to read so we have two macros that help straighten
  things out. `->` is pronounced Thread First and `->>` is pronounced Thread
  Last. `->` lets us write our above example as `(-> (foo 1) (bar 2 3) (baz
  4))`. `->>` does the same thing but it puts the value that is being threaded
  as the last argument rather than the first.
* `doto` works like `->` but instead of passing the return value through the
  chain it passes the initial value to every function call. This is only useful
  when you're calling the functions for side effects as their return values will
  be discarded.
* Clojure files are written bottom to top. Functions can only refer to functions
  that have already been defined. So you'll normally find the big important
  functions at the bottom of the file and the functions that help with their
  implementation towards the top.
* By convention `core.clj` will be the entry point file for a particular package.
  If you're looking through a directory it might help to read this file first.
* Clojure has literals for several data types. `[1 2 3]` is a vector. `{:a 1 :b
  2 :c 3}` is a map that is using the keywords (like symbols from ruby) as keys
  and integers as values. `#{1 3 2}` is an un-ordered set.
* `defmethod` and `defmulti` are one of the ways we have polymorphism in
  Clojure. It may seem unusual to someone more familiar with object oriented
  code to see methods outside of an object. It overcomes something called the
  expression problem and essentially means we can have polymorphism without
  ended up with objects that contain too many methods covering too many
  different concerns.
* Clojure supports destructuring. This is a way of pulling values out of a
  datastructure. It is mostly used in `defn`, `let`, and `for` blocks. Here are
  some examples:

```
     (let [point [10 12]
           [x y] point]
       (println x)
       (println y))
     
     (let [user          {:name "Logan"
                          :age  100}
           {:keys [age]} logan]
       (println age))
```
