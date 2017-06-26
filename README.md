# search

This is Logan Campbell's implementation of the Zendesk search code challenge.

## Dependencies

* Java 1.8

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
