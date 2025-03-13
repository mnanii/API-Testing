# Use OpenJDK 21 with Maven pre-installed; base image for docker
FROM maven:3.9.6-eclipse-temurin-21

# Set working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . /app

# Default command to execute tests
CMD ["mvn", "test"]

