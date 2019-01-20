#!/bin/bash
pushd ./build_scripts
./mvn_package_all.sh
./docker_deploy_all.sh
popd