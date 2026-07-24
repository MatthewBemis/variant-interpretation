# hello-world

Proof-of-concept Workbench application. It serves the VIA variant-search entry
page as a static site via nginx: entering candidate variants and clicking
Search navigates to the (also static/mocked) results page.

## Configuration

- **Image**: nginx:1.27 (Debian-based — the `devcontainer` features below require `bash`/`apt-get`, which Alpine images don't have)
- **Port**: 8080
- **User**: root
- **Home Directory**: /root
- **Content**: `html/via-search-entry-v1.html` (default page, served by nginx using `nginx.conf`), which links to `html/via-search-results-v1.4.html` on Search

## Access

Once deployed in Workbench, access the app at the app URL (port 8080).

For local testing:
1. Create Docker network: `docker network create app-network`
2. Run the app: `devcontainer up --workspace-folder .`
3. Access at: `http://localhost:8080`

## Customization

- `html/via-search-entry-v1.html` - The entry page (default document). Edit this to change the search form.
- `html/via-search-results-v1.4.html` - The mocked results page navigated to on Search.
- `nginx.conf` - nginx server config (listens on port 8080, serves `html/`, default document is `via-search-entry-v1.html`).
- `.devcontainer.json` - Devcontainer configuration and features.
- `docker-compose.yaml` - Docker Compose configuration.
- `devcontainer-template.json` - Template options and metadata.

## Testing

To test this app template:

```bash
cd test
./test.sh hello-world
```

## Usage

1. Fork the repository
2. Modify the configuration files as needed
3. In Workbench UI, create a custom app pointing to your forked repository
4. Select this app template (hello-world)
