# CurrencyRateDataTransformer

Project that generates json response of USD/PLN rates.

## Requirements

The fully fledged server uses the following:

* Spring Framework
* SpringBoot
* Feign
* Appache-commons
* Swagger2

## Dependencies

There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.

## Building

You will need:

*	Java JDK 17 or higher
*	Maven 3.8.5 or higher
*	Git

Clone the project and use Maven to build the server:

```bash
mvn clean install
```

## Endpoints

* /api/dates/{startDate} - returns USD/PLN rates from {startDate} to current date in json form
* /api/dates/{startDate}/{endDate} - returns USD/PLN rates from {startDate} to {endDate} in json form

## License

Project is licensed under the [MIT](LICENSE) license.  


## Documentation

To see the documentation, run the project and visit:
http://localhost:8080/swagger-ui/index.html

## PostmanTests

Postman test collection:

File: CurrencyRateDataTransformer.postman_collection.json
