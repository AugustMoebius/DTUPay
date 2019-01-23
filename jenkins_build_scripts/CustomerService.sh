#!/bin/bash
set -e

# Find service
pushd ./services/customer_service/service

# Buid and run tests
mvn clean package

popd