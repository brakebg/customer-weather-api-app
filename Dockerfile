FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/customer-service-1.0.0.jar customer-service.jar
EXPOSE 8080
CMD ["java", "-jar", "customer-service.jar"]
