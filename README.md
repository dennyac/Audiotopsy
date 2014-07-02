Audiotopsy
==========================

Data pipeline for analyzing the Echonest Million Song Dataset

##Instructions to set up the data pipeline


###Prerequisite - 
Requires a Hadoop Cluster with Cloudera CDH 5.0.2 running
Ensure that maven is installed


1. Clone this repository
2. To fetch the Echonest Million Song Dataset and push the data to hdfs

> cd bash
> ./get_data.sh

3. To create the hive tables around the master dataset to facilitate ad-hoc querying

> cd ../hive
> ./hive_driver.sh

4. To run the batch jobs that will generate the views

> ./hive_load_tmp_data_driver.sh

5. To create the HBase tables and then load the views in HBase to enable real-time querying

> cd ../hbase
> ./hbase_driver.sh #To create the HBase tables
> cd ../HBaseImport
> mvn package
> cd ../bash
> ./load_hbase_data.sh #Driver script that run the jar files to get the data into HBase


##Instructions to set up the Web Application that provide RESTful API

###Prerequisite - 

Requires Apache Tomcat and Maven to deploy this web application

1. Create the war file for the web application

> cd audiotopsy_webapp
> mvn package
> cd target #The war file will be created in this directory

2. SSH into the system where you wish to deploy the web application and copy the war file to the webapps folder in tomcat. You should be able to access the data via RESTful APIs now!







