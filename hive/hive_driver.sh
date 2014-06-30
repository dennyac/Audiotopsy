#!/bin/bash
. ../bash/config.sh

echo "hive_driver.sh started execution at ${dt}" > ${LOG_PATH}/hive_driver_${dt}.log


#Creating the master table

hive -f msd_master.hql

echo "msd_master table created" >> ${LOG_PATH}/hive_driver_${dt}.log

hive -f msd_year.hql

echo "msd_year table created. Stores year wise stats" >> ${LOG_PATH}/hive_driver_${dt}.log

hive -f msd_hotttnesss_yearwise.hql

echo "msd_hotttnesss_yearwise table created. Maintains the top songs yearwise, in sorted order." >> ${LOG_PATH}/hive_driver_${dt}.log

hive -f insert_msd_year.hql

echo "Data has been inserted in the Hbase table msd_year via the Hive table." >> ${LOG_PATH}/hive_driver_${dt}.log

hive -f insert_msd_hotttnesss_yearwise.hql

echo "Data has been inserted in the Hbase table msd_hotttnesss_yearwise via the corresponding Hive table." >> ${LOG_PATH}/hive_driver_${dt}.log
