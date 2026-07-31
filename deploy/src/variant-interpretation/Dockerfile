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
# post-startup.sh (run via postCreateCommand, see .devcontainer.json) requires
# these -- sudo in particular, since it runs every step via
# `sudo -u root bash -l -c ...`, and fails immediately without it.
RUN apt-get update && \
    apt-get install -y --no-install-recommends sudo jq git lsb-release && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
COPY --from=backend-build /backend/build/libs/*.jar app.jar
COPY deploy/src/variant-interpretation/entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh
EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]
