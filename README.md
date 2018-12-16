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

### Running maven
```sh
# Install dependencies
mvn install

# Execute Unit Tests (**/*Test.java classes)
mvn test

# Execute Integrated Tests (**/*IT.java classes)
mvn verify

# Start application
mvn spring-boot:run
# It will start the server on localhost:8888/comexport
# There are sample requets to import and run using Postman (see postman_samples.json)
```

##  Running with docker
```sh
# create the java spring boot image
docker build -t spring-boot-docker-app .

# Running application and database
docker-compose up -d

# Scale application
docker-compose scale application=2
```

## License

All the code in this project is a public domain work, dedicated using [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/). Feel free to do whatever you want with it.