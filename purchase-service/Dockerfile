FROM openjdk:17-jdk
WORKDIR /app
COPY target/purchase-service.jar /app/customer-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
