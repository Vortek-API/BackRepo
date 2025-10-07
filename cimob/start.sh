#!/usr/bin/env bash
set -euo pipefail

# Allow overriding the full JDBC URL when needed
if [[ -z "${SPRING_DATASOURCE_URL:-}" ]]; then
  oracle_profile="${ORACLE_DATABASE_PROFILE:-high}"
  case "$oracle_profile" in
    high)
      descriptor="(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1521)(host=adb.sa-saopaulo-1.oraclecloud.com))(connect_data=(service_name=g6d674c66993d88_vortekradaresbd_high.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))"
      ;;
    medium)
      descriptor="(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1521)(host=adb.sa-saopaulo-1.oraclecloud.com))(connect_data=(service_name=g6d674c66993d88_vortekradaresbd_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))"
      ;;
    low)
      descriptor="(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1521)(host=adb.sa-saopaulo-1.oraclecloud.com))(connect_data=(service_name=g6d674c66993d88_vortekradaresbd_low.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))"
      ;;
    tp)
      descriptor="(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1521)(host=adb.sa-saopaulo-1.oraclecloud.com))(connect_data=(service_name=g6d674c66993d88_vortekradaresbd_tp.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))"
      ;;
    tpurgent)
      descriptor="(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1521)(host=adb.sa-saopaulo-1.oraclecloud.com))(connect_data=(service_name=g6d674c66993d88_vortekradaresbd_tpurgent.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))"
      ;;
    *)
      echo "Unknown ORACLE_DATABASE_PROFILE '$oracle_profile'. Valid options: high, medium, low, tp, tpurgent." >&2
      exit 1
      ;;
  esac

  export SPRING_DATASOURCE_URL="jdbc:oracle:thin:@${descriptor}"
fi

exec java -jar target/cimob-0.0.1-SNAPSHOT.jar
