CREATE EXTERNAL TABLE msd_choropleth_states (
  artist_id STRING, 
  artist_name STRING, 
  geohash STRING, 
  artist_location STRING, 
  artist_hotttnesss DOUBLE, 
  song_hotttnesss DOUBLE, 
  danceability DOUBLE, 
  energy DOUBLE, 
  duration DOUBLE, 
  loudness DOUBLE, 
  tempo DOUBLE,
  count BIGINT, state STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LOCATION '/user/ec2-user/denny/msd_choropleth_states';
