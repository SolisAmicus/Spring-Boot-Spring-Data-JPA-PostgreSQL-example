# Spring Boot + Spring data JPA + PostgreSQL example

## Framework

- Spring Boot 3.1.2
- Spring Data JPA (Hibernate 6 is the default JPA implementation)
- PostgreSQL 15
- Java 17
- Maven 3
- JUnit 5
- Spring Tests (REST Assured)
- Docker, [Testcontainers](https://testcontainers.com/) (for Spring integration tests using a MySQL container)

## How to run it

```shell
$ git clone git@github.com:SolisAmicus/Spring-Boot-Spring-Data-JPA-PostgreSQL-example.git
```

```shell
$ cd spring-data-jpa-mysql
```

```shell
$ docker run --name ${container_name} -p ${local_port}:${container_port} -e POSTGRES_USER=${user_name} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${database_name} -d postgres:15-alpine
```

- `--name ${container_name}`: Set the name of the container
- `-p ${local_port}:${container_port}`: Local port mapping to container port
- `-e`: Environment variables
  - `-e POSTGRES_USER=${user_name}`  – Set the username for the PostgreSQL superuser
  - `-e POSTGRES_PASSWORD=${password}` – Set the password for the PostgreSQL superuser
  - `-e POSTGRES_DB=${database_name}` – Set the name of the database
- `-d`: Run the container in the background
- `postgres:15-alpine`: Docker images——PostgreSQL 15, based on Alpine Linux

application.properties: 

```properties
spring.datasource.url=jdbc:postgresql://${host}:${port}/${database_name}
spring.datasource.username=${user_name}
spring.datasource.password=${password}
```

For example: Assume that you start the container locally.

```shell
$ docker run --name datasource_postgreSQL -p 5432:5432 -e POSTGRES_USER=solisamicus -e POSTGRES_PASSWORD=password -e POSTGRES_DB=mydb -d postgres:15-alpine
```

Modify application.properties:

```properties
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/mydb
spring.datasource.username=solisamicus
spring.datasource.password=password
```

Running on Unix systems:

```shell
$ ./mvnw clean package -Dmaven.test.skip=true
$ ./mvnw spring-boot:run
```

