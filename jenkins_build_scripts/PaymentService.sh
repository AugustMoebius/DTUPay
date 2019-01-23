#!/bin/bash
set -e

# find service
pushd ./services/payment_service/service

# Build and run tests
mvn clean package

popd