FROM ubuntu:16.04

ENV http_proxy='http://ltdtoo\zbrown:Asc3naPi11ar@l01piproxy02.corp.local:80'

RUN apt-get update && apt-get install -y \
    openjdk-8-jdk

ADD . /task-service

WORKDIR /task-service

ADD VERSION .

CMD ["./gradlew", "bootRun"]    
