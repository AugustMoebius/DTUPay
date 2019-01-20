#!/bin/bash
./docker_clean_containers.sh payment-service
./docker_deploy.sh ./services/payment_service/service payment-service 8585

./docker_clean_containers.sh customer-service
./docker_deploy.sh ./services/customer_service/service customer-service 8686

./docker_clean_containers.sh token-service
./docker_deploy.sh ./services/token_service/service token-service 8787

./docker_clean_containers.sh merchant-service
./docker_deploy.sh ./services/merchant_service/service merchant-service 8888

./docker_clean_containers.sh report-service
./docker_deploy.sh ./services/report_service/service report-service 8989