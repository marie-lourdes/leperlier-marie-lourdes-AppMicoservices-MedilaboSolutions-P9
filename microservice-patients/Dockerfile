FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /lib

COPY ./target/*.jar /lib

EXPOSE 9001
  
CMD ["java","-jar","/lib/microservice-patients-0.0.1-SNAPSHOT.jar"]
