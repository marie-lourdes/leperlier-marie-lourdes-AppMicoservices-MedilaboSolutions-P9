# Application microservices Medilabo Solutions

Medilabo Solution is a Spring Boot application with architecture  microservices:
- microservice-eureka back for register instance of all microservices
- microservice-config-server back to call repository Config-repo-medilabo which stock all configuration of all microservices
- microservice-API-Gateway back manange request HTTP between microservices
- microservice-patients back  manage data of patients and CRUD operations with BDD MySQL
- microservice-medicalReports back depend on microservice-patients and manage CRUD operations od  medical report of patient data  with BDD NoSQL MongoDB
- microservice-assessment back depend on microservice-medicalReport  and evalutate risk diabete based on  symptoms in medcal report of patient 
- microservice-client  front depend on microservice-API-Gateway whiche request  microservices back to display datas with  beforehand authentication Spring Security

### Technologies

- Java 17 
- Spring Boot 3.X  
- Spring Cloud
- Spring Data Jpa
- Spring Data MongoDB
- Spring security
- Thymeleaf
- MySQL 8.0
- MongoDB Community Edition
- Docker-desktop 4.30.00
- Docker-compose v2.27.0-desktop.2


### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://java.tutorials24x7.com/blog/how-to-install-java-17-on-windows

2.Install Maven:

https://maven.apache.org/install.html

3.Install Spring Tools for Eclipse

https://www.eclipse.org/community/eclipse_newsletter/2018/february/springboot.php

5.Install Docker-desktop

https://www.docker.com/products/docker-desktop/

6.Install MySQL WorkBench or other BDD

https://www.mysql.com/products/workbench/

7.Upload structure BDD of file Data.sql in SQL BDD (in folder/config) or file Data.sql in folder /resources of microservice-patient and  then add your info connexion BDD in environment variable of your local system 

8.Install MongoDB and MongoDB Compass GUI

https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/

https://www.mongodb.com/try/download/compass

9.Launch MongoDB compass, then:
- create database 'historypatients'
- create collection 'medicalreports'
- add  data from the collection with file medicalReports.json  in folder /resources of microservice-medicalReports 

### Running App

Post installation of Java, Maven, and modules and Spring Tools 4, you will have to run each microservice in project in this order  with  Boot DashBoard of Spring Tools 
or with your CLI , mvn spring-boot:run :
- microservice-eureka
- microservice-config-server
- microservice-API-Gateway
- microservice-patients 
- microservice-medicalReports
- microservice-assessment
- microservice-client 

Finally, you will be ready to  use API and request 
The home page :http://localhost:8080/home

### Endpoints of microservices  

#### Endpoints microservice-patients

- POST: **localhost:9001/patient/creation**
- GET: **localhost:9001/patient/list**
- GET: **localhost:9001/patient/info-patient/{id}**

#### Endpoints microservice-medicalReports

- POST: **localhost:9002/patient/creationRapportMedical/{id}**
- GET: **localhost:9002/patient/rapport-medical/{id}**

#### Endpoints microservice-assessments

- GET: **localhost:9003/patient/assessment-diabetes/{id}**

#### microservice-client
- GET: **localhost:8080/home/**

-For authentication use login: user, password: user, 
or login: admin, passaword: admin

-For testing  all endpoints and forms, follow the menu and navigation in the application

### Use Docker image and  Docker Compose
In each microservice folder , the file Dockerfile allow you to build Docker image:

- Run command Docker in your favorite CLI:'docker build -t [name image  noted in docker-compose.yaml] .' for  creating  image of each microservice

- Then run: 'docker run -d -p [port]:[port] [name image noted in docker-compose.yaml]' for running container of each microservice 
you will see in the interface Docker images and containes of all microservices

Then, in root of project, the file docker-compose.yaml allow you to run all microservices together

- Run command Docker:'docker-compose up -d'


### Green Coding Suggestion

- Pipeline CI/CD with GithHub action help to Isolate code updates, tests, and deployments

- Favor microservice architecture rather than monolithic architecture like this project

- Avoid calling methods by low-level classes if they can be managed by high-level classes, this requires less application resources

 
 


