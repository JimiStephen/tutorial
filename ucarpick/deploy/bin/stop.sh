#!/bin/bash

cd `dirname $0`
BASE_DIR="/data"
PROJECT_DIR="${BASE_DIR}/project-folder-name"
BIN_DIR="${PROJECT_DIR}/bin"
APP_DIR="${PROJECT_DIR}/app"


cd $BASE_DIR
JAR_PATH=$(find $APP_DIR -maxdepth 2 -name "*.jar")
echo "jar path is :$JAR_PATH"

SERVER_NAME=$(basename $JAR_PATH .jar)
echo "server name is :$SERVER_NAME"

PIDS=`ps -ef | grep java | grep "${SERVER_NAME}" |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The ${SERVER_NAME} does not started!"
    exit
fi

if [ "$1" = "dump" ]; then
    $BIN_DIR/dump.sh
fi

echo -e "Stopping the ${SERVER_NAME} ...\c"
for PID in $PIDS ; do
    #echo kill
    kill $PID > /dev/null 2>&1
done



for((i=1;i<=50;i++)); do
   echo -e ".\c"
   sleep 3
   COUNT=0

   for PID in $PIDS ; do
	#echo $i
        PID_EXIST=`ps -ef -p $PID | grep java`

        if [ -n "$PID_EXIST" ]; then

           # 3m   kill -9 
	   if [ "$i" -gt 5 ]; then
		echo -n "$(date)" >> /tmp/kill9.log
		echo 'run kill -9'  >> /tmp/kill9.log
		kill -9  $PID > /dev/null 2>&1
	   else
		kill $PID > /dev/null 2>&1
                COUNT=1
	    fi
        fi
    done

    if [ "$COUNT" -eq 0 ]; then 
	exit 
    fi
    
done

echo "OK!"
echo "PID: $PIDS"
