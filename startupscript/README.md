# startupscript/

This is a copy of the subset of `rwb/startupscript/` needed by `post-startup.sh`
at container runtime (`emit.sh`, `install-java.sh`, `install-cli.sh`,
`setup-bashrc.sh`, `git-setup.sh`, `bash-completion.sh`, `gcp/`, `aws/`).

It exists here, at the repo root, because the Verily Workbench devcontainer
framework (`050-parse-devcontainer.sh`, fetched fresh from
`verily-src/workbench-app-devcontainers` on every VM boot) hardcodes
`/home/core/devcontainer/startupscript` -- i.e. it expects a `startupscript/`
folder at the root of whatever git repo gets cloned, and copies it into the
app's devcontainer folder (`rwb/src/variant-interpretation/`) before running
`postCreateCommand`. Since this repo's app code lives at the true root with
the devcontainer framework vendored under `rwb/`, that copy has to live here
instead of only in `rwb/startupscript/`.

If you update `rwb/startupscript/`, re-copy the relevant files here too --
nothing keeps these two copies in sync automatically.
