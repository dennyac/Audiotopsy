INSERT OVERWRITE LOCAL DIRECTORY '/home/ec2-user/denny/tmp_msd_hotttnesss_yearwise/' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY '\t'
select concat(year,rpad(substring(1 - song_hotttnesss,1,4),4,"0"),track_id), artist_name, title,rpad(substring(song_hotttnesss,1,4),4,"0") release from msd_master where song_hotttnesss IS NOT NULL;
