# Comexport Challenge

Source code to run the Comexport Challenge.

## Project

Build a REST API to manage Account Postings.

## Frameworks

This project uses the following frameworks in order to optimize the application source code and features:

* [Maven](https://maven.apache.org/)
* [Spring Boot](http://spring.io/)
* [MySQL](https://www.mysql.com/)

## Installation and Usage

```sh
git clone https://github.com/danielvito/comexport-backend.git
cd comexport-backend
```

### It's mandatory to start the database service before building the application
```sh
docker-compose up -d database

# add this line in the hosts file
127.0.0.1 database

# optionally, connect to the database
docker exec -it mysql mysql -uroot -proot 
```

### Running with maven
```sh
# Install dependencies (it will build and run unit and integration tests)
mvn install

# Execute build + Unit Tests (LancamentosContabeisControllerUTest)
mvn test

# Execute build + Unit Tests + Integrated Tests (LancamentosContabeisControllerIT)
mvn verify

# Start application
mvn spring-boot:run
# It will start the server on localhost:8888/comexport
# There are sample requets to import and run using Postman (see postman_samples.json)
```

###  Running with docker (its mandatory to run maven build before in order to have the jar file generated)
```sh
# create the java spring boot image with the app jar inside
docker build -t spring-boot-docker-app .

# Running application and database
docker-compose up -d

# Scale application (each app container will be mapped to a random port)
docker-compose scale application=2
```

## License

All the code in this project is a public domain work, dedicated using [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/). Feel free to do whatever you want with it.