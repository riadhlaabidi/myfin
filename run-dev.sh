#!/bin/bash

pushd myfin-server/

docker compose -f docker/postgresql.yml up -d

mvn clean spring-boot:run

docker compose -f docker/postgresql.yml down

popd




