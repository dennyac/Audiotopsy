package com.dennyac.Audiotopsy;

import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class MSDViews {

	public JSONObject getYearWiseStats() {
		JSONObject mainObj = new JSONObject();

		try {
			ResultScanner scanner = null;
			HTableInterface table = HBaseConnection.getTable("msd_year");
			Scan scan = new Scan();
			scanner = table.getScanner(scan);

			JSONArray yearWiseJson = new JSONArray();
			mainObj.put("year_wise_stats", yearWiseJson);

			for (Result res : scanner) {

				JSONObject yearJson = new JSONObject();
				yearJson.put("year", Bytes.toString(res.getRow()));

				for (Entry<byte[], byte[]> col : res.getFamilyMap(
						Bytes.toBytes("info")).entrySet()) {
					yearJson.put(HBaseConnection.getColName(Bytes.toString(col.getKey())),
							Bytes.toString(col.getValue()));
				}
				yearWiseJson.put(yearJson);
				HBaseConnection.closeTable(table);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return mainObj;
	}

	// Expects year to be a valid year. Returns top songs for the input year.
	public JSONObject getYearTopTracks(String year) {
		JSONObject mainObj = new JSONObject();

		try {
			ResultScanner scanner = null;
			HTableInterface table = HBaseConnection.getTable("msd_hotttnesss_yearwise");
			Scan scan = new Scan(Bytes.toBytes(year), Bytes.toBytes(Integer
					.toString(Integer.parseInt(year) + 1)));
			scanner = table.getScanner(scan);

			JSONArray yearTopTracks = new JSONArray();
			mainObj.put(year, yearTopTracks);

			for (Result res : scanner) {

				JSONObject songJson = new JSONObject();

				for (Entry<byte[], byte[]> col : res.getFamilyMap(
						Bytes.toBytes("info")).entrySet()) {
					songJson.put(HBaseConnection.getColName(Bytes.toString(col.getKey())),
							Bytes.toString(col.getValue()));
				}
				yearTopTracks.put(songJson);
				HBaseConnection.closeTable(table);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return mainObj;
	}
	
	// Expects year to be a valid year. Returns top songs for the input year.
	public JSONObject TopTracks() {
		JSONObject mainObj = new JSONObject();

		try {
			ResultScanner scanner = null;
			HTableInterface table = HBaseConnection.getTable("msd_hotttnesss");
			Scan scan = new Scan(Bytes.toBytes("hot0.00"), Bytes.toBytes("hot1.00"));
			scanner = table.getScanner(scan);

			JSONArray yearTopTracks = new JSONArray();
			mainObj.put("top_tracks", yearTopTracks);

			for (Result res : scanner) {

				JSONObject songJson = new JSONObject();

				for (Entry<byte[], byte[]> col : res.getFamilyMap(
						Bytes.toBytes("info")).entrySet()) {
					songJson.put(HBaseConnection.getColName(Bytes.toString(col.getKey())),
							Bytes.toString(col.getValue()));
				}
				yearTopTracks.put(songJson);
				HBaseConnection.closeTable(table);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return mainObj;
	}

}
