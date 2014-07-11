raw_data = LOAD 'denny/geonames_states/final.tsv' USING PigStorage( '\t' ) AS (
geohash: chararray, 
state: chararray);



STORE raw_data INTO 'hbase://geonames_states' USING
org.apache.pig.backend.hadoop.hbase.HBaseStorage (
'cf1:s');

