#!/bin/bash
. ./config.sh

dt=`date '+%Y%m%d%H%M'`

echo "get_data.sh started execution at $dt" > ${LOG_PATH}/get_data_${dt}.log

read_dom () {
  local IFS=\>
  read -d \< ENTITY CONTENT
}

#Getting list of files to download form S3 bucket

echo `curl http://tbmmsd.s3.amazonaws.com/` | while read_dom; do
  if [[ $ENTITY = "Key" ]] ; then
    wget -q http://tbmmsd.s3.amazonaws.com/$CONTENT
    echo "Downloaded file $CONTENT" >> ${LOG_PATH}/get_data_${dt}.log
    hdfs dfs -put $CONTENT /user/ec2-user/denny/msd_data
    echo "Placed $CONTENT in hdfs" >> ${LOG_PATH}/get_data_${dt}.log
    rm $CONTENT
    echo "Removed $CONTENT from local filesystem" >> ${LOG_PATH}/get_data_${dt}.log 
  fi
done 
