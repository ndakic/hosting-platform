#!/bin/sh

spring_env=prod
server_ip=18.188.91.54
user=ubuntu
remote_server=$user@$server_ip
port=9000

ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null $remote_server "touch test.txt"
ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null $remote_server "sleep 5s"

exit 0