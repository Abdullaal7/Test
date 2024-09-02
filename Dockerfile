FROM openjdk:8
WORKDIR /app
COPY target/Devops-test.jar Devops-test.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app/Devops-test.jar"]