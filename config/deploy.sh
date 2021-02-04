#!/bin/sh

spring_env=dev
server_ip=18.188.91.54
user=ubuntu
pem_file=config/java-api-latest.pem # temp solution
remote_server=$user@$server_ip
port=9000

ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -i $pem_file $remote_server "touch test.txt"
ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -i $pem_file $remote_server "sleep 5s"

exit 0