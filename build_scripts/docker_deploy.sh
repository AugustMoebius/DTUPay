#!/bin/bash
if ! [[ $1 && $2 && $3 ]]; then
  echo "Usage: docker_deploy.sh <PROJECT_PATH> <CONTAINER_NAME> <PORT_NO>"
  exit 0
fi

PROJECT_PATH=$1
CONTAINER_NAME=$2
PORT_NO=$3

echo "$0: Deploying container with name $2"

pushd $PROJECT_PATH
docker build . --tag $CONTAINER_NAME
docker run -d --name $CONTAINER_NAME -p $PORT_NO:8080 --network dtupay-net $CONTAINER_NAME
popd