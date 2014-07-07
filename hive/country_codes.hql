  CREATE EXTERNAL TABLE country_codes (
  alpha3 STRING, 
  alpha2 STRING, 
  country_name STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION '/user/ec2-user/denny/country_codes/';

