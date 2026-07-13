# Fusion Eclipse Plugin

Eclipse IDE support for the [Fusion](https://github.com/voidifremoved) language (`.fu` files): syntax highlighting and keyword content assist.

## Install from the update site

1. In Eclipse, open **Help → Install New Software…**
2. Click **Add…**
3. Name: `Fusion`
4. Location: `https://voidifremoved.github.io/fusion-eclipse-plugin/`
5. Select **Fusion Language Support** and finish the wizard

After install, open any `.fu` file to use the Fusion editor.

## Build locally

Requires Java 21. Uses the Maven Wrapper:

```bash
./mvnw --batch-mode "-Dtycho.localArtifacts=ignore" clean verify
```

The installable p2 repository is written to:

`releng/com.rubberjam.fusion.repository/target/repository`

Install from that folder via **Help → Install New Software → Add → Local…**.

See [docs/BUILDING.md](docs/BUILDING.md) for module layout and details.

## Modules

| Module | Role |
|--------|------|
| `com.rubberjam.fusion.eclipse` | Editor plugin |
| `features/com.rubberjam.fusion.feature` | Installable feature |
| `releng/com.rubberjam.fusion.target` | Eclipse 2026-03 target platform |
| `releng/com.rubberjam.fusion.repository` | p2 update site |
| `tests/com.rubberjam.fusion.eclipse.tests` | Plugin tests |

## License

MIT — see [LICENSE](LICENSE).
