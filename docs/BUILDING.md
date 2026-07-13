# Building the Fusion Eclipse Plugin

## Prerequisites

- Java 21
- Network access to Maven Central and `https://download.eclipse.org/releases/2026-03/`

Builds use the Maven Wrapper (`mvnw`). A system Maven install is optional.

## Standard build

```bash
./mvnw --batch-mode "-Dtycho.localArtifacts=ignore" clean verify
```

`-Dtycho.localArtifacts=ignore` keeps the build from accidentally depending on bundles already installed in your local Maven repository.

Skip tests:

```bash
./mvnw --batch-mode "-Dtycho.localArtifacts=ignore" clean verify -DskipTests
```

## Local update site

After a successful build:

`releng/com.rubberjam.fusion.repository/target/repository`

In Eclipse: **Help → Install New Software → Add → Local…** and select that folder.

## GitHub Pages

Pushes to `master` run `.github/workflows/static.yml`, which builds with Tycho and deploys the p2 repository plus `site/` landing page to GitHub Pages.

Enable **Settings → Pages → Build and deployment → GitHub Actions** on the repository if Pages is not already configured.

Published URL:

`https://voidifremoved.github.io/fusion-eclipse-plugin/`

## Regenerating the Maven Wrapper

```bash
mvn -N wrapper:wrapper -Dmaven=3.9.9
```
