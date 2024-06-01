FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /lib

COPY ./target/*.jar /lib

EXPOSE 9003
  
CMD ["java","-jar","/lib/microservice-assessment-1.0.0.jar"]
