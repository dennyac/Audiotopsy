#!/bin/bash

. ./config.sh

dt=`date '+%Y%m%d%H%M'`

. ./$1 > ${LOG_PATH}/$1_${dt}_run.log 2>&1
