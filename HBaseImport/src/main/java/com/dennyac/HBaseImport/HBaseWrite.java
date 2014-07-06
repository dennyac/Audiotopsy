package com.dennyac.HBaseImport;

import java.io.IOException;
import java.io.InterruptedIOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseWrite {	

	// TODO : update the table name with your username
	byte[] tableName;
	byte[] familyName;
	Configuration config;
	HTable htable;
	
	HBaseWrite(String tableName, String familyName){
		this.tableName = Bytes.toBytes(tableName);
		this.familyName = Bytes.toBytes(familyName);
		
	}
	
	HBaseWrite(String tableName){
		this.tableName = Bytes.toBytes(tableName);		
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

	public void connectHBase()
			throws IOException {

		config = HBaseConfiguration.create();
		htable = new HTable(config, tableName);
	}
	
	public void disconnectHBase() throws IOException{
		htable.close();
		config.clear();
	}

}
