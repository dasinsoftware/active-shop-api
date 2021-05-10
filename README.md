# Active-shop-api
This GitHub repository contains the Active-shop-api -  and supports the following functionality:

* Lists products and its points price.

* Allows users to purchase those products based on the points they have.

## Prerequisites

- You must have Java 11 or above installed, and available to use on your command line (Please note that the CLI does not
  work with Java versions 10 or below).

## Getting started

- To start up the Api , in a shell run   
    
### Linux
```shell script
    ./gradlew bootRun
```
    
    

###    Windows
```shell script 
    gradlew.bat bootRun
```
  
     

###    Docker 
```shell script 
./gradlew docker
docker run -p 8080:8080 docker.momentum.co.za/za.co.momentum/active-shop-api:1.0.0
```
  
  
Note : For the docker option - You also need Docker, which only runs on 64-bit machines. See https://docs.docker.com/installation/#installation for details on setting Docker up for your machine. 

##Once Api is running, the ffg. links will be available:

- To view Swagger docs click  [here](http://localhost:8080/swagger-ui.html)


-  Health endpoint [link](http://localhost:8080/actuator/health)


- Metrics endpoints [link](http://localhost:8080/actuator/metrics)
  
  Sample "process.uptime" metric [link](http://localhost:8080/actuator/metrics/process.uptime)

