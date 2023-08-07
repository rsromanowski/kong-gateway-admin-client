#!/usr/bin/env sh

docker kill kong-gateway
docker kill kong-database
docker container rm kong-gateway
docker container rm -v kong-database
docker network rm kong-net
