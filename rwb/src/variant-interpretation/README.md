# variant-interpretation

Custom Workbench application for the variant interpretation tool: a React +
TypeScript frontend served by a Java + Spring Boot backend, packaged into a
single container.

The `Dockerfile` here builds in two stages: it compiles the frontend
(`frontend/` at the repo root) with Node, copies the built assets into the
backend's static resources, then builds the Spring Boot jar (`backend/` at
the repo root) with Gradle. The resulting image runs the jar directly, so the
API (`/api/hello`) and the frontend are both served from port 8080 — no
reverse proxy or CORS configuration needed.

## Configuration

- **Build**: multi-stage `Dockerfile`, build context is the repository root
- **Port**: 8080
- **User**: root
- **Home Directory**: /root

## Access

Once deployed in Workbench, access the app at the app URL (port 8080).

For local testing:
1. Create Docker network: `docker network create app-network`
2. From the repo root, build and run: `docker compose -f rwb/src/variant-interpretation/docker-compose.yaml up --build`
3. Access at: `http://localhost:8080`

## Customization

- `../../../frontend` - React + TypeScript source
- `../../../backend` - Java + Spring Boot source
- `Dockerfile` - Multi-stage build combining both into one image
- `.devcontainer.json` - Devcontainer configuration and features
- `docker-compose.yaml` - Docker Compose configuration
- `devcontainer-template.json` - Template options and metadata

## Testing

To test this app template:

```bash
cd test
./test.sh variant-interpretation
```

## Usage

1. Fork the repository
2. In Workbench UI, create a custom app pointing to your forked repository,
   branch `main`, and folder `rwb/src/variant-interpretation`
