FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /lib

COPY ./target/*.jar /lib

EXPOSE 9102
  
CMD ["java","-jar","/lib/microservice-eureka-0.0.1-SNAPSHOT.jar"]
