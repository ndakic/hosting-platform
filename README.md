![hostplat-client](https://github.com/ndakic/hosting-platform/workflows/deploy-app-on-s3/badge.svg)

# Software Configuration Management 

Course Software Configuration Management at Faculty of Technical Sciences, University of Novi Sad

## Getting started

These instructions will help you to run this project.

### Prerequisites

All applications are dockerized and you only need to install:

 - [docker](https://docs.docker.com/engine/installation/)
 - [docker-compose](https://docs.docker.com/compose/install/)

Make sure following ports are open

- 4200, 9000, 9200, 9300, 5601, 5959, 3000, 8083, 8086

Supported applications are:

- hostplat-client
- hostplat-server
- elasticsearch
- kibana
- logstash
- influxdb
- grafana

## Launch applications

### Run all applications:
    docker-compose up

### Bring up specific service with command:
    docker-compose up [SERVICE_NAME]

### Stop all service:
    docker-compose down

### Bring down specific service with command:
    docker-compose down [SERVICE_NAME]
