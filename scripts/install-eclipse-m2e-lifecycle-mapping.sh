#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"
SOURCE_FILE="${REPO_ROOT}/releng/eclipse-lifecycle-mapping-metadata.xml"

usage() {
  cat <<EOF
Install m2e lifecycle mapping that prefers org.eclipse.m2e.pde.connector over
the legacy org.sonatype.tycho.m2e connector.

Usage:
  $0 <eclipse-workspace-directory>

Example:
  $0 ~/eclipse-workspace

After running:
  1. Restart Eclipse
  2. Maven -> Update Project... on all Tycho modules
EOF
}

if [[ $# -ne 1 ]]; then
  usage
  exit 1
fi

WORKSPACE="$(cd "$1" && pwd)"
TARGET_DIR="${WORKSPACE}/.metadata/.plugins/org.eclipse.m2e.core"
TARGET_FILE="${TARGET_DIR}/lifecycle-mapping-metadata.xml"

if [[ ! -d "${WORKSPACE}/.metadata" ]]; then
  echo "Error: ${WORKSPACE} does not look like an Eclipse workspace (.metadata missing)" >&2
  exit 1
fi

if [[ ! -f "${SOURCE_FILE}" ]]; then
  echo "Error: source file not found: ${SOURCE_FILE}" >&2
  exit 1
fi

mkdir -p "${TARGET_DIR}"
cp "${SOURCE_FILE}" "${TARGET_FILE}"

echo "Installed lifecycle mapping to:"
echo "  ${TARGET_FILE}"
echo
echo "Next steps in Eclipse:"
echo "  1. Restart Eclipse"
echo "  2. Settings -> Maven -> Lifecycle Mapping -> Reload workspace lifecycle mappings metadata"
echo "  3. Maven -> Update Project... (all modules)"
echo
echo "Preferred long-term fix: uninstall Tycho Project Configurators (org.sonatype.tycho.m2e)"
echo "from Help -> About -> Installation Details."
