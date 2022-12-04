FROM openjdk:8
COPY target/bank-mandiri-project-0.0.1.jar bank-mandiri-project-0.0.1.jar
ENTRYPOINT ["java", "-jar", "bank-mandiri-project-0.0.1.jar"]
EXPOSE 8080