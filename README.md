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

# Install dependencies

# It's needed to start the database service to build
docker-compose up -d database

# Run with maven Application
mvn install
# It will start the server on localhost:8888/comexport

# Running with docker
# create the java spring boot image
docker build -t spring-boot-docker-app .

# Running application and database
docker-compose up -d

# Scale application
docker-compose scale application=2


## License

All the code in this project is a public domain work, dedicated using [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/). Feel free to do whatever you want with it.