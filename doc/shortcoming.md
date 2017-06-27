# Shortcomings of this implementation

I've written this solution in my strongest language which is Clojure. However
the implementation has some shortcomings.

* Clojure is not the ideal choice for a terminal applications due to slow
  startup time. The JVM take several seconds just to load the Clojure runtime.
* Clojure has immature ncurses style terminal libraries. This meant that there
  was only one choice for cross platform realtime terminal apps. And it had no
  high level UI elements.
* My own form elements (text fields and table) don't support terminal resizing.
  The application is only designed for a 80x24 terminal.
* Any terminal application that uses styling runs the risk of colors or styles
  conflicting with the styles that the user has selected for their terminal.
  I've chosen high contrast options that I hope will print clearly for most
  people.
* There is an escape sequence that prints when the application closes. I believe
  it's a result of the underlying terminal library. It may not print on all
  platforms.
* There are no list inputs or auto completion for the text inputs. I'm not sure
  that I would have been able to implement an additional form of input in the
  time I allocated to this code challenge. Lists or auto completion would have
  improved the user experience when selecting a dataset or field.
* There is no test coverage of the UI rendering code. All functions used have
  side effects that are difficult to capture. Fragile, implementation specific,
  tests could be written by asserting functions are called. A better solution
  would be for drawing functions to return a data structure that describes the
  screen and then to have two renderers, one that can work with a terminal
  another that renders to a string for testing.
* Layout is manual. A UI framework might provide a component that automatically
  lays out sub components. That would have helped a great deal in enabling the
  app to scale when the terminal is resized. It would have also removed the
  magical numbers that are used through drawing code.
