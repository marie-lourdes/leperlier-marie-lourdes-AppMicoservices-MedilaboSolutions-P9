FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /lib

COPY ./target/*.jar /lib

EXPOSE 8080
  
CMD ["java","-jar","/lib/microservice-client-1.0.0.jar"]

