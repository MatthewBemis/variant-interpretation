# rwb

This is a trimmed-down copy of [verily-src/workbench-app-devcontainers](https://github.com/verily-src/workbench-app-devcontainers),
keeping only what this app needs to be packaged and deployed as a Verily
Workbench custom app:

- `src/variant-interpretation/` - the devcontainer/docker-compose packaging
  for this app; see its [README](src/variant-interpretation/README.md).
- `LICENSE` - the upstream repo's license, kept for attribution since the
  files above originated there.

The upstream repo also vendors dozens of other unrelated app templates
(Jupyter, RStudio, VSCode, ...), shared devcontainer features, and a test
harness for all of them. None of that is used by this app, so it isn't
included here. If you need to pull in an upstream fix to the packaging
files above, refer to the upstream repo directly.

Note: `startupscript/` lives at the repository root, not under `rwb/` - see
[its README](../startupscript/README.md) for why.
