#!/bin/bash
set -e

# Must be run from the root of the git directory
./deploy.sh

# Run system tests
sleep 200
cd ./system_tests
mvn clean test