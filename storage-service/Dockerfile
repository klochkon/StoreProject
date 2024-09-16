FROM openjdk:17-jdk
WORKDIR /app
COPY target/storage-service.jar /app/customer-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
