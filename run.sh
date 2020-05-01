#!/bin/bash

until nc -z "${MYSQL_HOST}" "${MYSQL_PORT}"; do
    echo "$(date) - waiting for MySQL..."
    sleep 1
done

java -jar ./app.jar
