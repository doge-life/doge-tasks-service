#!/bin/bash

function echo_catalina_home() {
    echo 'export CATALINA_HOME=/usr/share/tomcat8'
}

sudo apt-get update && sudo apt-get install -y openjdk-8-jre tomcat8

echo_catalina_home >> ~/.bash_profile
echo_catalina_home >> /home/ubuntu/.bash_profile
