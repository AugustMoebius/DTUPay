#!/bin/bash
set -e

# find service
pushd ./services/merchant_service/service

# Build and run tests
mvn clean package

popd