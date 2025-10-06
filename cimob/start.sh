#!/usr/bin/env bash
set -euo pipefail

if [[ -z "${ORACLE_WALLET_ZIP_B64:-}" ]]; then
  echo "Environment variable ORACLE_WALLET_ZIP_B64 is required" >&2
  exit 1
fi

export ORACLE_WALLET_DIR="${ORACLE_WALLET_DIR:-/app/wallet}"
wallet_parent="$(dirname "$ORACLE_WALLET_DIR")"

mkdir -p "$wallet_parent"
tmp_zip="$(mktemp)"
trap 'rm -f "$tmp_zip"' EXIT

echo "$ORACLE_WALLET_ZIP_B64" | base64 -d > "$tmp_zip"
rm -rf "$ORACLE_WALLET_DIR"
export TMP_ZIP="$tmp_zip"
export WALLET_PARENT="$wallet_parent"

if command -v unzip >/dev/null 2>&1; then
  unzip -oq "$tmp_zip" -d "$wallet_parent"
else
  if command -v python3 >/dev/null 2>&1; then
    python3 - <<'PY'
import os, zipfile
tmp_zip = os.environ['TMP_ZIP']
wallet_parent = os.environ['WALLET_PARENT']
with zipfile.ZipFile(tmp_zip) as zf:
    zf.extractall(wallet_parent)
PY
  else
    python - <<'PY'
import os, zipfile
tmp_zip = os.environ['TMP_ZIP']
wallet_parent = os.environ['WALLET_PARENT']
with zipfile.ZipFile(tmp_zip) as zf:
    zf.extractall(wallet_parent)
PY
  fi
fi

export SPRING_DATASOURCE_URL="${SPRING_DATASOURCE_URL:-jdbc:oracle:thin:@vortekradaresbd_high?TNS_ADMIN=${ORACLE_WALLET_DIR}}"
export SPRING_DATASOURCE_HIKARI_DATA_SOURCE_PROPERTIES_ORACLE_NET_TNS_ADMIN="${SPRING_DATASOURCE_HIKARI_DATA_SOURCE_PROPERTIES_ORACLE_NET_TNS_ADMIN:-$ORACLE_WALLET_DIR}"
export SPRING_DATASOURCE_HIKARI_DATA_SOURCE_PROPERTIES_ORACLE_NET_WALLET_LOCATION="${SPRING_DATASOURCE_HIKARI_DATA_SOURCE_PROPERTIES_ORACLE_NET_WALLET_LOCATION:-(SOURCE=(METHOD=FILE)(METHOD_DATA=(DIRECTORY=${ORACLE_WALLET_DIR})))}"

exec java -jar target/cimob-0.0.1-SNAPSHOT.jar
