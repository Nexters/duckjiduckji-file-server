#!/bin/bash

echo "-----start duckjiduckji file-server--------"

profiles=$1

kill -9 `ps -ef | grep duckjiduckji-fileServer* | awk '{print $2}'`

nohup java -jar -Dspring.profiles.active=$profiles *.jar &

echo "-----end duckjiduckji file-server--------"

