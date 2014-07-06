package com.dennyac.HBaseClient;

import java.io.IOException;
import java.io.InterruptedIOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseConnection {	

	byte[] tableName;
	byte[] familyName;
	Configuration config;
	HTable htable;
	
	HBaseConnection(String tableName, String familyName) throws IOException{
		this.tableName = Bytes.toBytes(tableName);
		this.familyName = Bytes.toBytes(familyName);
		connectHBase();
		
	}
	
	HBaseConnection(String tableName) throws IOException{
		this.tableName = Bytes.toBytes(tableName);
		connectHBase();
	}
	
	public HTable getTable(){
		return htable;
	}

	public void put(String key, String colQualifier, String value)
			throws RetriesExhaustedWithDetailsException, InterruptedIOException {

		Put put = new Put(Bytes.toBytes(key));

		put.add(familyName, Bytes.toBytes(colQualifier),
				Bytes.toBytes(value));

		htable.put(put);

	}
	
	public void put(String key, String colFam, String colQualifier, String value)
			throws RetriesExhaustedWithDetailsException, InterruptedIOException {

		Put put = new Put(Bytes.toBytes(key));

		put.add(Bytes.toBytes(colFam), Bytes.toBytes(colQualifier),
				Bytes.toBytes(value));

		htable.put(put);

	}

	private void connectHBase()
			throws IOException {

		config = HBaseConfiguration.create();
		htable = new HTable(config, tableName);
	}
	
	public void disconnectHBase() throws IOException{
		htable.close();
		config.clear();
	}

}
