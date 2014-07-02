package com.dennyac.Audiotopsy;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;


public class HBaseConnection {

	public static Configuration conf;
	public static HConnection connection;
	public static HashMap<String,String> colQual;
	
	public static void initializeColQualMappings(){
		colQual = new HashMap<String,String>();
		colQual.put("ht", "hotttnesss");
		colQual.put("du", "duration");
		colQual.put("tp", "tempo");
		colQual.put("db", "loudness");
		colQual.put("dn", "danceability");
		colQual.put("eg", "energy");
		colQual.put("an", "artist_name");
		colQual.put("sn", "title");
		colQual.put("al", "album");
	}

	public static void connectHbase() {

		try {
			conf = HBaseConfiguration.create();
			conf.set("hbase.zookeeper.quorum", "172.31.13.66");
			conf.set("hbase.zookeeper.property.clientPort", "2181");
			connection = HConnectionManager.createConnection(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getColName(String col) {
		return colQual.get(col);
	}

	public static HTableInterface getTable(String tableName) throws IOException {
		return connection.getTable(tableName);
	}

	public static void closeTable(HTableInterface htable) throws IOException {
		htable.close();
	}

	public static void disconnectHbase() {
		try {
			connection.close();
			conf.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
