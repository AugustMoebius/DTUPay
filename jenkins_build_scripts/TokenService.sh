#!/bin/bash
set -e

# Find service
pushd ./services/token_service/service

# Build and run tests
mvn clean package

popd