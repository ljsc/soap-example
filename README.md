# soap-example

Example of using wsimport to consume as soap webservice in clojure

## Usage

Create the java source files by runing `wsimport`:

```bash
$ wsimport -s src/java http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL
$ rm -fr com # leiningen will compile for us
```

## License

Copyright © 2015 Louis J. Scoras

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

