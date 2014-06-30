#!/bin/bash

. ./config.sh


. ./$1 > ${LOG_PATH}/$1_${dt}_run.log 2>&1
