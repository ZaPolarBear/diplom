### Diplom Project
This project was made for creating various type of graphics
There you can:
* Make gpahs
* Сoherent waves
* Beating waves
* Addition waves
* Wave interference
* Download graph images
* Get data tables to work with in another programs
### Technologies used
Stack:
* Java 17.
* Spring: Spring Boot, Spring MVC, Data JPA, Spring Validation.
* JFreeChart - fast graph generating with opportunity for image creating.
* MinIO - image and xml file storage.
* Lombok - reducing boilerplate code.
* Postgre - database.
* Thymeleaf is a modern server-side Java template engine for both web and standalone environments.
* HTML/CSS
* Liquibase - Fast database change. Fluid delivery.
Environment:
* Docker for containerization
### Quick start
Steps:
```
- git clone https://github.com/ZaPolarBear/diplom
- cd diplom
- mvn clean package
- docker-compose -f docker-compose.yml up -d --build
- docker run -d -p 8082:8082 -t diplom:0.0.1
- curl http://localhost:8082 - chech for app is up-to-date
```
   
