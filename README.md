# CurrencyRateDataTransformer

Project that generates json response of USD/PLN rates.

[![MIT licensed][shield-mit]](LICENSE)
[![Java v17][shield-java]](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html)


## Building

In order to build project generating jar archive use:

```bash
mvn clean package
```

In order to build project generating jar, sources and javadoc archives use:

```bash
mvn -Prelease clean package
```

## Running

In order to run use:

```bash
java -jar target/CurrencyRateDataTransformer-1.0.0-SNAPSHOT.jar
```

## License

Project is licensed under the [MIT](LICENSE) license.  

## Used technologies

* Spring
* feign
* appache-commons
* swagger2

## Documentation

To see the documentation, run the project and visit:
http://localhost:8080/swagger-ui/index.html

## PostmanTests

Postman test collection:

TODO link do pliku z testami postmana
