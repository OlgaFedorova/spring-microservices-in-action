#!/usr/bin/env bash

while  [ "$( psql --username 'postgres' --no-password -tAc "SELECT 1 FROM pg_database WHERE datname='spring_microservices'" )" != '1' ]
do
    echo "Database does not exist"
    sleep 10
done

echo "Database already exists"

pg_ctl -D "$PGDATA" -m fast -w stop
pg_ctl -D "$PGDATA" -o "-c listen_addresses='localhost'" -w start
flyway migrate