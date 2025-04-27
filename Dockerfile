FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/PetCareRestApplication-1.0.0.jar PetCareRestApplication-1.0.0.jar
EXPOSE 80
CMD ["java", "-jar", "PetCareRestApplication-1.0.0.jar"]
