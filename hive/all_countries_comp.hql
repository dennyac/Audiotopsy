CREATE EXTERNAL TABLE all_countries (
  geonameid BIGINT, 
  name STRING, 
  asciiname STRING, 
  alternatenames STRING, 
  latitude DOUBLE, 
  longitude DOUBLE, 
  feature_class STRING, 
  feature_code STRING, 
  country_code STRING, 
  cc2 STRING, 
  admin1_code STRING, 
  admin2_code STRING, 
  admin3_code STRING, 
  admin4_code STRING, 
  population BIGINT, 
  elevation BIGINT, 
  dem STRING, 
  timezone STRING, 
  modification_date STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LOCATION '/user/ec2-user/denny/allcountries_comp/';

