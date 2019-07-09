# ecommerce-microservice

This is a demo project for spring cloud powered microservice.

Spring cloud tools used includes:
# Eureka Server, Zuul, Config Server, Ribbon, Hystrix, Kafka, Docker

This application includes three main services: 1) Account Service manages customer account balance, order history 2) Product service manages Product inventory information 3) Shopping-portal service manages customer interaction such as check if balance is enough to purchase product and order product and add to order history etc.

Eureka-server service is used to register all services

Zuul-gateway service is used to redirect all routes to individual services and expose only one domain to customer

Config-server service is used to manage the data source connections for account and product services at different environment, including DEV, QA and PROD

Hystrix is applied when shopping-portal request some recommended products from product service, if the product service was unable to respond, shopping-portal will return the fallback error message

Ribbon is applied purely for demo purpose - when customer try to get account info from shopping-portal service, it will return account info plus the server port which indicates which replica of account service returns the request

Two versions of kafka is used - apache kafka and spring kafka. For the kafka server, this project use the complimentary plan from an online kafka hoster. Apache kafka is implemented using a while(true) loop and the consumer class is running in a separate thread. Spring kafka consumer is implemented using the kafkalistener annotation.

docker image is generated in the account service using boost maven plugin.
