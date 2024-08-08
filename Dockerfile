# Use the official Gradle image with JDK 17
FROM gradle:7.4.2-jdk17

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Make the gradlew script executable
RUN chmod +x ./gradlew

# Expose the port the app runs on
EXPOSE 8080

# Run the application using Gradle
CMD ["./gradlew", "clean", "test"]