#!/bin/bash
if ! [[ $1 ]]; then
  echo "needs to provide Docker image tag as first argument, e.g. \"payment_service\""
  exit 0
fi

image_tag=$1
old_containers=$(docker ps -aqf "ancestor=$image_tag" --format="{{.ID}}")

echo "printing old containers"
echo $old_containers

if [[ $old_containers ]]; then
  # Stop containers and store stopped containers to variable
  deletables=$(docker stop $old_containers)

  echo "deleting old containers"
  # Delete historical containers
  docker rm $deletables

  # Delete old images by tag
  docker rmi $image_tag
else
  echo "no old containers found"
fi
