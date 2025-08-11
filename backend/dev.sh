#!/usr/bin/env bash
set -euo pipefail

# Compilación inicial rápida, offline y paralela
./mvnw -q -DskipTests -T 1C -o compile || true

# Arranca la app con dev profile y flags para arranque rápido
./mvnw spring-boot:run \
  -Dspring-boot.run.profiles=dev \
  -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true -XX:+UseSerialGC -XX:TieredStopAtLevel=1" &
APP_PID=$!

# --- Watcher por POLLING (funciona con bind mounts de Windows) ---
last_hash=""
while true; do
  new_hash="$(find src/main -type f \( -name '*.java' -o -name '*.kt' -o -name '*.yml' -o -name '*.xml' \) -print0 \
    | sort -z \
    | xargs -0 sha1sum \
    | sha1sum \
    | awk '{print $1}')"

  if [ "$new_hash" != "$last_hash" ]; then
    echo "[watch] cambios detectados -> compilando..."
    ./mvnw -q -DskipTests -T 1C -o compile || true
    last_hash="$new_hash"
  fi

  sleep 1
done &

wait $APP_PID
