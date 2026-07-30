# Build context is the repository root (see docker-compose.yaml), so paths
# below are relative to the top of variant-interpretation, not this folder.

FROM node:20-alpine AS frontend-build
WORKDIR /frontend
COPY frontend/package*.json ./
RUN npm ci
COPY frontend/ .
RUN npm run build

FROM gradle:8.14.1-jdk21-noble AS backend-build
WORKDIR /backend
COPY backend/settings.gradle backend/build.gradle backend/gradlew ./
COPY backend/gradle ./gradle
RUN ./gradlew --no-daemon dependencies
COPY backend/src ./src
COPY --from=frontend-build /frontend/dist ./src/main/resources/static
RUN ./gradlew --no-daemon bootJar -x test

FROM eclipse-temurin:21-jre-noble
WORKDIR /app
COPY --from=backend-build /backend/build/libs/*.jar app.jar
COPY rwb/src/variant-interpretation/entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh
EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]
