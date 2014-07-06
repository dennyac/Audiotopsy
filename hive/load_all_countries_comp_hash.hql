add jar /home/ec2-user/denny/GeohashUDF/target/GeohashUDF-0.0.1-SNAPSHOT-jar-with-dependencies.jar

create temporary function geo as 'com.dennyac.geohashUDF.GetGeohashBinString';

INSERT OVERWRITE TABLE all_countries_comp_hash
select geonameid , 
  name , 
  asciiname , 
  alternatenames , 
  latitude , 
  longitude ,
  geo(concat(latitude,',',longitude)) , 
  feature_class , 
  feature_code , 
  country_code , 
  cc2 , 
  admin1_code , 
  admin2_code , 
  admin3_code , 
  admin4_code 
from all_countries_comp;
