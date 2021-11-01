# Spring-Boot-Multiple-Authentication-Provider-Demo

This is the demo Spring Boot project for multiple authentication provider.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Running the application locally

```shell
mvnw spring-boot:run
```

The application will be exposed via http://localhost:8080

## Test It

#### Login for SG Authentication Provider

```shell
curl -H "Content-Type: application/json" -X POST http://localhost:8080/authenticate -d "{\"username\": \"jackie-sg\", \"password\": \"password\", \"country\": \"SG\"}"
```
#### Login for MY Authentication Provider
curl -H "Content-Type: application/json" -X POST http://localhost:8080/authenticate -d "{\"username\": \"jackie-my\", \"password\": \"password\", \"country\": \"MY\"}"
```

#### Use token to access secured endpoint
```shell
curl http://localhost:8080/sensitive -H "Accept: application/json" -H "Authorization: Bearer s3Cr3t-t0k3n"
```

## Copyright

Released under the Apache License 2.0
