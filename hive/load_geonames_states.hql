INSERT OVERWRITE LOCAL DIRECTORY '/home/ec2-user/denny/geonames_us_state/' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY '\t'
select geohash, admin1_code from all_countries_comp_hash where admin1_code IN ('AK','CA','FL','GA','IA','KS','KY','MA','ME','MI','MO','MS','ND','NH','NJ','NV','OK','SC','TN','TX','VT','WA','WI','WY','AL','AR','AZ','CT','DC','DE','HI','ID','IL','IN','LA','MD','MN','MT','NC','NE','NM','NY','OH','OR','PA','RI','SD','UT','VA','WV') and length(geohash) = 64;
