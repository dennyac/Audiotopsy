INSERT OVERWRITE TABLE msd_hotttnesss_yearwise
select concat(year,rpad(substring(1 - song_hotttnesss,1,4),4,"0")),
artist_name,
title,
release
from msd_master 
where song_hotttnesss IS NOT NULL;
