#!/bin/bash
./mvn_package.sh ../services/payment_service/service
./mvn_package.sh ../services/customer_service/service
./mvn_package.sh ../services/token_service/service
./mvn_package.sh ../services/merchant_service/service
./mvn_package.sh ../services/report_service/service