#!/bin/bash
. ./config.sh

echo "load_hbase_data.sh started execution at ${dt}" > ${LOG_PATH}/load_hbase_data_${dt}.log

echo "Task YearWiseStats started execution at ${dt}" > ${LOG_PATH}/load_hbase_data_${dt}.log
java -cp $(hbase classpath):/home/ec2-user/denny/HBaseImport/target/HBaseImport-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.dennyac.HBaseImport.YearWiseStats  > ${LOG_PATH}/load_hbase_data_${dt}.log 2>&1

echo "Task TopSongsYearwise started execution at ${dt}" > ${LOG_PATH}/load_hbase_data_${dt}.log 2>&1
java -cp $(hbase classpath):/home/ec2-user/denny/HBaseImport/targetHBaseImport-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.dennyac.HBaseImport.TopSongsYearwise > ${LOG_PATH}/load_hbase_data_${dt}.log 2>&1 

echo "Task TopSongs started execution at ${dt}" > ${LOG_PATH}/load_hbase_data_${dt}.log 2>&1
java -cp $(hbase classpath):/home/ec2-user/denny/HBaseImport/target/HBaseImport-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.dennyac.HBaseImport.TopSongs > ${LOG_PATH}/load_hbase_data_${dt}.log 2>&1 


#Have to clean up temporary files
