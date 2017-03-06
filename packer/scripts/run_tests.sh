#!/bin/bash

source ~/.bash_profile

sudo apt-get update && sudo apt-get install -y ruby
sudo gem install bundler
cd /tmp/tests
bundle install
bundle exec rake
