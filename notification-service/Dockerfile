FROM openjdk:17-jdk
WORKDIR /app
COPY target/notification-service.jar /app/customer-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
