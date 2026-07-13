# Building the Fusion Eclipse Plugin

## Prerequisites

- Java 21
- Network access to Maven Central and `https://download.eclipse.org/releases/2026-03/`

Builds use the Maven Wrapper (`mvnw`). A system Maven install is optional.

## Eclipse IDE import

1. **Resolve the Tycho m2e connector conflict** (required once per Eclipse installation):

   Modern Eclipse already includes Tycho support via `org.eclipse.m2e.pde.connector`. If the legacy **Tycho Project Configurators** (`org.sonatype.tycho.m2e`) is also installed, m2e reports lifecycle-mapping conflicts and `does not have an expanded version` errors.

   **Preferred fix:** uninstall the legacy connector:
   - **Help → About Eclipse IDE → Installation Details → Installed Software**
   - Search for `Tycho` or `sonatype`
   - Uninstall **Tycho Project Configurators** (`org.sonatype.tycho.m2e`)
   - Restart Eclipse

   **Alternative fix** (if you cannot remove the legacy connector yet):
   - **Eclipse → Settings → Maven → Lifecycle Mapping**
   - Set **Workspace lifecycle mappings file** to:
     `releng/eclipse-lifecycle-mapping-metadata.xml` in this repository
   - Click **Reload workspace lifecycle mappings metadata**
   - Restart Eclipse

2. **Import the reactor**:
   - **File → Import → Maven → Existing Maven Projects**
   - Root directory: this repository
   - Import all modules

3. **Set the target platform**:
   - Open `releng/com.rubberjam.fusion.target/com.rubberjam.fusion.target`
   - Click **Set as Active Target Platform**

4. **Refresh Maven configuration**:
   - Right-click the root `eclipse-fusion-lang-plugin` project
   - **Maven → Update Project…** (check all modules, OK)

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
