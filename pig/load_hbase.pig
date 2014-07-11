raw_data = LOAD 'denny/country_stats/000000_0' USING PigStorage( ',' ) AS (
country: chararray, 
hotttnesss: chararray,
count: chararray);



STORE raw_data INTO 'hbase://country_stats' USING
org.apache.pig.backend.hadoop.hbase.HBaseStorage (
'info:ht info:ct');
