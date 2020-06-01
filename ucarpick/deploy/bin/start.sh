#!/bin/bash

cd `dirname $0`
BASE_DIR="/data"
PROJECT_DIR="${BASE_DIR}/project-folder-name"
BIN_DIR="${PROJECT_DIR}/bin"
APP_DIR="${PROJECT_DIR}/app"

LOGS_DIR=${BASE_DIR}/logs
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log


TMP_DIR=${BASE_DIR}/tmp
if [ ! -d $TMP_DIR ]; then
    mkdir $TMP_DIR
fi

cd $BASE_DIR
JAR_PATH=$(find $APP_DIR -maxdepth 2 -name "*.jar")
echo "jar path is :$JAR_PATH"

JAR_NAME=$(basename $JAR_PATH)
echo "jar name is :$JAR_NAME"

SERVER_NAME=$(basename $JAR_PATH .jar)
echo "server name is :$SERVER_NAME"

SERVER_PORT="8080"

SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`
if [ $SERVER_PORT_COUNT -gt 0 ]; then
    echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
    exit 1
fi


PIDS=`ps -ef | grep java | grep "${SERVER_NAME}" |awk '{print $2}'`
if [ "$1" = "status" ]; then	  
    if [ -n "$PIDS" ]; then
        echo "The $SERVER_NAME is running...!"
        echo "PID: $PIDS"
        exit 0
    else
        echo "The $SERVER_NAME is stopped"
        exit 0
    fi
fi


if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi




JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Djava.io.tmpdir=$TMP_DIR "

jt=0
jt=$(hostname|grep -E '\-test|\.test' -q && echo 1 || echo 0)
jpre=$(hostname|grep -E '\-pre|\.pre' -q && echo 1 || echo 0)
jprod=$(hostname|grep -E '\-prod|\.prod' -q && echo 1 || echo 0)
luckjt=$(hostname|grep luck -q && echo 1 || echo)
if [[ $jt -eq 1 ]] || [[ $jpre -eq 1 ]]
then
    [ -f "/usr/local/spy/sandbox/latest/lib/sandbox-agent.jar" ] && JAVA_OPTS="$JAVA_OPTS -javaagent:/usr/local/spy/sandbox/latest/lib/sandbox-agent.jar " #PLATICAGENTfi
fi

JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi


JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

JAVA_MEM_OPTS=""
while read -r line  
do
if [[ $line =~ ^$ || $line =~ ^# ]]; then
continue
fi

JAVA_MEM_OPTS=${JAVA_MEM_OPTS}" "${line}
done  < ${BIN_DIR}/jvm.properties


CONFIG_FILES=" -Dlogging.path=$LOGS_DIR "
for line in `cat ${BIN_DIR}/uboot.properties`
do
CONFIG_FILES=${CONFIG_FILES}" -D$line"
done


#echo "${CONFIG_FILES}"
echo -e "Starting the $SERVER_NAME ..."

echo  "nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $CONFIG_FILES -jar $JAR_PATH --server.port=$SERVER_PORT"

#su - tomcat -c "nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $CONFIG_FILES -jar $JAR_PATH --server.port=$SERVER_PORT >/dev/null 2>$STDOUT_FILE &"
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $CONFIG_FILES -jar $JAR_PATH --server.port=$SERVER_PORT >/dev/null 2>$STDOUT_FILE &


echo "OK!"
#PIDS=`ps -ef | grep java | grep "${SERVER_NAME}" | awk '{print $2}'`
#echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"


