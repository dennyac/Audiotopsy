INSERT OVERWRITE LOCAL DIRECTORY '/home/ec2-user/denny/tmp_choropleth_data_us/' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY '\t'
select artist_id,artist_name,geohash,artist_location,artist_hotttnesss,song_hotttnesss,danceability,energy,duration,loudness,tempo,count from msd_choropleth_master where country = "US";