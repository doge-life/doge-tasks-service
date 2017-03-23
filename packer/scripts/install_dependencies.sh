#!/bin/bash

CATALINA_EXPORT='export CATALINA_HOME=/usr/share/tomcat8'

sudo apt-get update && sudo apt-get install -y openjdk-8-jre tomcat8

echo $CATALINA_EXPORT >> ~/.bash_profile
echo $CATALINA_EXPORT >> /home/ubuntu/.bash_profile
