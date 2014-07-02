INSERT OVERWRITE LOCAL DIRECTORY '/home/ec2-user/denny/tmp_msd_year/' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY '\t'
select year,
substring(avg(song_hotttnesss),1,4),
lpad(substring(avg(duration),1,instr(avg(duration),'.')-1),3,"0"),
lpad(substring(avg(tempo),1,instr(avg(tempo),'.')-1),3,"0"),
substring(avg(loudness),1,instr(avg(loudness),'.')-1),
substring(avg(danceability),1,4),
substring(avg(energy),1,4)
from msd_master 
where year is not null and year != 0
group by year;
