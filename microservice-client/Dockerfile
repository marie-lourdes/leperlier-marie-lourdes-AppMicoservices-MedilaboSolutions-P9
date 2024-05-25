FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /lib

COPY ./target/*.jar /lib

EXPOSE 8080
  
CMD ["java","-jar","/lib/microservice-client-0.0.1-SNAPSHOT.jar"]

