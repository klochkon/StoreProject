FROM openjdk:17-jdk
WORKDIR /app
COPY target/analytics-service.jar /app/analytics-service.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
