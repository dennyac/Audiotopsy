#!/bin/bash
. ../bash/config.sh

echo "hbase_driver.sh started execution at ${dt}" > ${LOG_PATH}/hbase_driver_${dt}.log


#Creating year wise stats table

hbase shell msd_year.hb

echo "msd_year table created" >> ${LOG_PATH}/hbase_driver_${dt}.log

hbase shell msd_hotttnesss_yearwise.hb

echo "msd_hotttnesss_yearwise table created" >> ${LOG_PATH}/hbase_driver_${dt}.log

hbase shell msd_hotttnesss.hb

echo "msd_hotttnesss table created" >> ${LOG_PATH}/hbase_driver_${dt}.log
