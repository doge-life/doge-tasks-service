#!/bin/bash

sudo apt-get update && sudo apt-get install -y openjdk-8-jre tomcat8

cat > ~/.bashrc <<"EOF"
export CATALINA_HOME=/usr/share/tomcat8
EOF