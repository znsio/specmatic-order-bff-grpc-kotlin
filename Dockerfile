FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the Gradle files
COPY build.gradle settings.gradle gradlew ./

# Copy the source code
COPY . .

# Build the application
RUN ./gradlew build -x test

# Run the application
CMD ["./gradlew", "bootRun"]