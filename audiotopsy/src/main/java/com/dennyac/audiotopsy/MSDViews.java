package com.dennyac.audiotopsy;

import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
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
				
			}
			HBaseConnection.closeTable(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return mainObj;
	}
	
	public JSONObject countryStats() {
		JSONObject mainObj = new JSONObject();

		try {
			ResultScanner scanner = null;
			HTableInterface table = HBaseConnection.getTable("country_stats");
			Scan scan = new Scan();
			scanner = table.getScanner(scan);
			double hot;
			String hottness = null;

			for (Result res : scanner) {

				JSONObject countryJson = new JSONObject();
				mainObj.put(Bytes.toString(res.getRow()), countryJson);

				for (Entry<byte[], byte[]> col : res.getFamilyMap(
						Bytes.toBytes("info")).entrySet()) {
					countryJson.put(HBaseConnection.getColName(Bytes.toString(col.getKey())),
							Bytes.toString(col.getValue()));
					if(Bytes.toString(col.getKey()).equals("ht")){
						hot = Double.parseDouble(Bytes.toString(col.getValue()));
						if(hot > 0.60){
							hottness = "Hot";
						}
						else if(hot > 0.30){
							hottness = "Medium";
						}
						else{
							hottness = "Cold";
						}
						countryJson.put("fillKey", hottness);
					}
				}
				
			}
			HBaseConnection.closeTable(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return mainObj;
	}
	
	public JSONArray getYearWiseStatsGraph() {

		JSONArray yearWiseHotttnesss = new JSONArray();
		try {
			ResultScanner scanner = null;
			HTableInterface table = HBaseConnection.getTable("msd_year");
			Scan scan = new Scan();
			scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("ht"));
			scanner = table.getScanner(scan);

			
			JSONObject hotJson = new JSONObject();
			yearWiseHotttnesss.put(hotJson);
			hotJson.put("key", "Hotttnesss");
			hotJson.put("color", "#ff7f0e");
			JSONArray dataPoints = new JSONArray();
			hotJson.put("values",dataPoints);
			for (Result res : scanner) {
				JSONObject dataPoint = new JSONObject();
				dataPoint.put("x",Bytes.toString(res.getRow()));
				dataPoint.put("y",Double.parseDouble(Bytes.toString(res.value())));
	
				dataPoints.put(dataPoint);
				
			}
			HBaseConnection.closeTable(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return yearWiseHotttnesss;
	}

	// Expects year to be a valid year. Returns top songs for the input year.
	public JSONArray getYearTopTracks(String year) {
		//JSONObject mainObj = new JSONObject();
		JSONArray yearTopTracks = null;
		Filter filter = new PageFilter(50);
		

		try {
			ResultScanner scanner = null;
			HTableInterface table = HBaseConnection.getTable("msd_hotttnesss_yearwise");
			Scan scan = new Scan(Bytes.toBytes(year), Bytes.toBytes(Integer
					.toString(Integer.parseInt(year) + 1)));
			scan.setFilter(filter);
			scanner = table.getScanner(scan);

			yearTopTracks = new JSONArray();
			//mainObj.put(year, yearTopTracks);

			for (Result res : scanner) {

				JSONObject songJson = new JSONObject();

				for (Entry<byte[], byte[]> col : res.getFamilyMap(
						Bytes.toBytes("info")).entrySet()) {
					songJson.put(HBaseConnection.getColName(Bytes.toString(col.getKey())),
							Bytes.toString(col.getValue()));
				}
				yearTopTracks.put(songJson);
				
			}
			HBaseConnection.closeTable(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return yearTopTracks;
	}
	
	// Expects year to be a valid year. Returns top songs for the input year.
		public JSONObject getYearTopTracksk(String year, String key) {
			JSONObject mainObj = new JSONObject();
			byte[] lastKey = null;
			JSONArray yearTopTracks = null;
			Filter filter = new PageFilter(20);
			

			//Adding multiple filters
			try {
				ResultScanner scanner = null;
				HTableInterface table = HBaseConnection.getTable("msd_hotttnesss_yearwise");
				Scan scan = new Scan(Bytes.toBytes(year), Bytes.toBytes(Integer
						.toString(Integer.parseInt(year) + 1)));
				//See if it makes sense to send the below statement in the constructor
				if(!key.isEmpty() && key != null){
					scan.setStartRow(Bytes.toBytes(key));
				}
				scan.setFilter(filter);
				scanner = table.getScanner(scan);

				yearTopTracks = new JSONArray();
				//mainObj.put(year, yearTopTracks);

				for (Result res : scanner) {

					JSONObject songJson = new JSONObject();

					for (Entry<byte[], byte[]> col : res.getFamilyMap(
							Bytes.toBytes("info")).entrySet()) {
						songJson.put(HBaseConnection.getColName(Bytes.toString(col.getKey())),
								Bytes.toString(col.getValue()));
					}
					lastKey = res.getRow();
					yearTopTracks.put(songJson);
					
				}
				HBaseConnection.closeTable(table);
				mainObj.append("year_top_tracks", yearTopTracks);
				mainObj.append("last_key",Bytes.toString(lastKey));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		
			return mainObj;
		}
	
	public JSONArray getYearTopTracksp(String year) {
		//JSONObject mainObj = new JSONObject();
		JSONArray yearTopTracks = null;
		int counter = 0;
		JSONObject songJson = null;

		try {
			ResultScanner scanner = null;
			HTableInterface table = HBaseConnection.getTable("msd_hotttnesss_yearwise");
			Scan scan = new Scan(Bytes.toBytes(year), Bytes.toBytes(Integer
					.toString(Integer.parseInt(year) + 1)));
			scanner = table.getScanner(scan);

			yearTopTracks = new JSONArray();
			//mainObj.put(year, yearTopTracks);

			for (Result res : scanner) {

				songJson = new JSONObject();

				for (Entry<byte[], byte[]> col : res.getFamilyMap(
						Bytes.toBytes("info")).entrySet()) {
					songJson.put(HBaseConnection.getColName(Bytes.toString(col.getKey())),
							Bytes.toString(col.getValue()));

				}
				if(counter++ == 50){
					break;
				}
				yearTopTracks.put(songJson);
			}
			
			HBaseConnection.closeTable(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return yearTopTracks;
	}
	
	// Expects year to be a valid year. Returns top songs for the input year.
	public JSONObject TopTracks() {
		JSONObject mainObj = new JSONObject();
		Filter filter = new PageFilter(50);

		try {
			ResultScanner scanner = null;
			HTableInterface table = HBaseConnection.getTable("msd_hotttnesss");
			Scan scan = new Scan(Bytes.toBytes("hot0.00"), Bytes.toBytes("hot1.00"));
			scan.setFilter(filter);
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
