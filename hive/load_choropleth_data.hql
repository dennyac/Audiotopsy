INSERT OVERWRITE LOCAL DIRECTORY '/home/ec2-user/denny/tmp_choropleth_data/' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY '\t'
select geo(concat(artist_latitude,',',artist_longitude)) as geohash,artist_location,artist_hotttnesss,avg(song_hotttnesss),avg(danceability),avg(energy),avg(duration),avg(loudness),avg(tempo),count(*) as count from msd_master where artist_latitude is not null group by artist_id,artist_name,artist_latitude,artist_longitude,artist_location,artist_hotttnesss;
