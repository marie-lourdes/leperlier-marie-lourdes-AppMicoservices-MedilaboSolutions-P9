FROM eclipse-temurin:17-jdk-alpine 

WORKDIR /lib

COPY ./target/*.jar /lib

EXPOSE 9002
  
CMD ["java","-jar","/lib/microservice-medicalReports-0.0.1-SNAPSHOT.jar"]

