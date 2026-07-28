# variant-interpretation

A monorepo that contains a frontend (TypeScript + React) and backend (Java + Spring Boot) for a variant interpretation application.

This application will be used in the All of Us Verily Researcher Workbench.

## Structure

- `frontend/` - React + TypeScript app (Vite). Currently a "Hello world" page that
  fetches the current time from the backend.
- `backend/` - Java + Spring Boot app. Currently exposes one endpoint,
  `GET /api/hello`, returning `{"timestamp": "<ISO-8601 instant>"}`.
- `rwb/` - Vendored [Verily Workbench devcontainer](rwb/README.md) tooling used to
  package and deploy this app as a Workbench custom app. The packaging for this
  app lives in `rwb/src/variant-interpretation/`; see its
  [README](rwb/src/variant-interpretation/README.md) for how it combines the
  frontend and backend into a single container.

## Local development

Run the backend and frontend separately, with Vite proxying `/api` calls to the
backend (see `frontend/vite.config.ts`):

```bash
# terminal 1
cd backend && ./gradlew bootRun

# terminal 2
cd frontend && npm install && npm run dev
```

Open the URL Vite prints (default `http://localhost:5173`) - you should see
"Hello world, it's `<timestamp>`".

## Running as it will run in Workbench

In Workbench, the frontend and backend are packaged into a single container on
one port (Spring Boot serves the built frontend as static resources, so there's
no CORS or reverse proxy to configure). To build and run that image locally:

```bash
docker network create app-network  # first time only
docker compose -f rwb/src/variant-interpretation/docker-compose.yaml up --build
```

Then open `http://localhost:8080`.