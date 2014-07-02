INSERT OVERWRITE LOCAL DIRECTORY '/home/ec2-user/denny/tmp_msd_hotttnesss/' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY '\t'
select concat(rpad(substring(1 - song_hotttnesss,1,4),4,"0"),track_id), artist_name, title, release, rpad(substring(song_hotttnesss,1,4),4,"0") from msd_master where song_hotttnesss IS NOT NULL;
