FROM openjdk:17-jdk
WORKDIR /app
COPY target/customer-service.jar /app/customer-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
