# variant-interpretation

A monorepo that contains a frontend (TypeScript + React) and backend (Java + Spring Boot) for a variant interpretation application.

This application will be used in the All of Us Verily Researcher Workbench.

## Structure

- `frontend/` - React + TypeScript app (Vite). Currently a "Hello world" page that
  fetches the current time from the backend.
- `backend/` - Java + Spring Boot app, built with Gradle. The API is
  defined API-first in `backend/src/main/resources/openapi/api.yaml`
  (server interfaces and models are generated from it at build time).
  Currently exposes:
  - `GET /api/hello` - returns `{"timestamp": "<ISO-8601 instant>"}`.
  - `GET /api/profile` - returns `{"userEmail": "<owner email>"}`, read from
    the `OWNER_EMAIL` environment variable set on the Workbench VM (empty if
    unset, e.g. in local dev without it exported).
- `deploy/` - Vendored [Verily Workbench devcontainer](deploy/README.md) tooling used to
  package and deploy this app as a Workbench custom app. The packaging for this
  app lives in `deploy/src/variant-interpretation/`; see its
  [README](deploy/src/variant-interpretation/README.md) for how it combines the
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

To see a real value from `GET /api/profile` locally, export `OWNER_EMAIL`
before starting the backend, e.g. `OWNER_EMAIL=you@example.org ./gradlew
bootRun`.

### Running as it will run in Workbench

In Workbench, the frontend and backend are packaged into a single container on
one port (Spring Boot serves the built frontend as static resources, so there's
no CORS or reverse proxy to configure). To build and run that image locally:

```bash
docker network create app-network  # first time only
OWNER_EMAIL=you@example.org docker compose -f deploy/src/variant-interpretation/docker-compose.yaml up --build
```

Then open `http://localhost:8080`.