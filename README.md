# search

This is Logan Campbell's implementation of the Zendesk search code challenge.

[![asciicast](https://asciinema.org/a/FOxJpn8dXeEHTwhXYOmc1kaS9.png)](https://asciinema.org/a/FOxJpn8dXeEHTwhXYOmc1kaS9)

## Dependencies

* Java 1.8

If you run `java -version` you should see output like:

    java version "1.8.0_25"
    Java(TM) SE Runtime Environment (build 1.8.0_25-b17)
    Java HotSpot(TM) 64-Bit Server VM (build 25.25-b02, mixed mode)

Clojure, Leiningen (the build tool) and all project dependencies will be
installed when you first run the application. Everything (including Clojure)
will be installed as jars within your local Maven cache. This should not effect
any existing installations you have or any installations you chose to do in the
future.

## Usage

    ./scripts/run
    
On first run this will take a little while to download dependencies. On further
runs startup time will still be about 15 seconds as the Java Virtual Machine
loads up Clojure and the project's dependencies.

## Tests

    ./scripts/test

If neither the app not test suite has ever been run then all dependencies will
be downloaded an installed.

If test pass there will be a unix status code of 0 set. If tests fail the status
code will be non-zero. There will also be output to `stdout` detailing progress
and any failures.

Travis CI is being used for continuous integration. Contact Logan if you'd like
access to [the build](https://travis-ci.com/logaan/search).

## Code Coverage

    ./scripts/coverage

This command will run the test suite and let you know which lines of code were
exercised. A colorful table will be printed to the screen and a html report will
be created in `target/coverage/index.html`.

All code without side effects has tests. Unfortunately the nature of a ncurses
style terminal app makes testing the generated output impractical. Coverage will
be about 70%.
