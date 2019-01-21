#!/bin/bash
if ! [[ $1 ]]; then
  echo "USAGE: ./mvn_package.sh <PROJECT_PATH>"
  exit 0
fi

PROJECT_PATH=$1

pushd $PROJECT_PATH
mvn package
popd