FROM openjdk:8
EXPOSE 8080
ADD target/elections-service.jar elections-service.jar
ENTRYPOINT ["java","-jar","/elections-service.jar"]
