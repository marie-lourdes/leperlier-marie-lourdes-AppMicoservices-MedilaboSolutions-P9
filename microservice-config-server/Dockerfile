FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /lib

COPY ./target/*.jar /lib

EXPOSE 9101
  
CMD ["java","-jar","/lib/microservice-config-server-0.0.1-SNAPSHOT.jar"]
