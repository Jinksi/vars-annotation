#!/usr/bin/env bash

BUILD_HOME=`dirname "$0"`
APP_CONF=$BUILD_HOME/src/main/resources/application.conf

scp brian@m3:/u/brian/deployspace/m3.shore.mbari.org/vars-annotation/conf/application.conf \
    $APP_CONF &&
    mvn clean package -Dmaven.test.skip=true -X
#    mvn clean package exec:exec@deploy-app install -Dmaven.test.skip=true -X
#    mvn clean package -Dmaven.test.skip=true -X &&
#    mvn exec:exec@deploy-app -Dmaven.test.skip=true -X&&
#    mvn install -Dmaven.test.skip=true -X


if [ -f $APP_CONF ]; then
  rm $APP_CONF
fi