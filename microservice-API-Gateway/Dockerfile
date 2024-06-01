FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /lib

COPY ./target/*.jar /lib

EXPOSE 9103
  
CMD ["java","-jar","/lib/microservice-API-Gateway-1.0.0.jar"]

