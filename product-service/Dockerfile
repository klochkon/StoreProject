FROM openjdk:17-jdk
WORKDIR /app
COPY target/product-service.jar /app/customer-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
