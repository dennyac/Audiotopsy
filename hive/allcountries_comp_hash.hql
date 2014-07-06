CREATE EXTERNAL TABLE all_countries_comp_hash (
  geonameid BIGINT, 
  name STRING, 
  asciiname STRING, 
  alternatenames STRING, 
  latitude DOUBLE, 
  longitude DOUBLE,
  geohash STRING, 
  feature_class STRING, 
  feature_code STRING, 
  country_code STRING, 
  cc2 STRING, 
  admin1_code STRING, 
  admin2_code STRING, 
  admin3_code STRING, 
  admin4_code STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LOCATION '/user/ec2-user/denny/allcountries_comp_hash/';

