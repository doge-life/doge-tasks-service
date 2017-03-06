|            |             |
------------ | -------------
Travis       | [![Travis](https://travis-ci.org/doge-life/doge-tasks-service.svg?branch=master)](https://travis-ci.org/doge-life/doge-tasks-service)
Circle       | [![CircleCI](https://circleci.com/gh/doge-life/doge-tasks-service.svg?style=svg)](https://circleci.com/gh/doge-life/doge-tasks-service)
Jenkins      | [![Build Status](http://ec2-107-21-21-140.compute-1.amazonaws.com/buildStatus/icon?job=doge-life/doge-tasks-service/master)](http://ec2-107-21-21-140.compute-1.amazonaws.com/job/doge-life/job/doge-tasks-service/job/master/)

|            |             |
------------ | -------------
Code Climate GPA | [![Code Climate](https://codeclimate.com/github/doge-life/doge-tasks-service/badges/gpa.svg)](https://codeclimate.com/github/doge-life/doge-tasks-service)
Code Climate Coverage | [![Test Coverage](https://codeclimate.com/github/doge-life/doge-tasks-service/badges/coverage.svg)](https://codeclimate.com/github/doge-life/doge-tasks-service/coverage)
Code Climate Issues | [![Issue Count](https://codeclimate.com/github/doge-life/doge-tasks-service/badges/issue_count.svg)](https://codeclimate.com/github/doge-life/doge-tasks-service)

# doge-tasks-service
A microservice for Doge Tasks!!

## Instructions to deploy with Packer
* Set `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` environment variables.
* If on Doge network, set `DOGE_PROXY_UNAME`, `DOGE_PROXY_PASSWORD`, `DOGE_PROXY_HOST` AND `DOGE_PROXY_PORT`
* `packer build packer.json` from packer directory.