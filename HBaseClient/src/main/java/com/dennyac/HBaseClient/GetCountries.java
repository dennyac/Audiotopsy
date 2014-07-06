package com.dennyac.HBaseClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class GetCountries {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		HBaseConnection hb = new HBaseConnection("all_countries_hash");
		HTable table = hb.getTable();

		// Get 50 nearest neighbors
		Filter filter = new PageFilter(50);
		ResultScanner scanner = null;
		Scan scan;
		HashMap<String, Integer> topCountry = new HashMap<String, Integer>();

		String[] fields = null;
		String cc;
		FileUtils fu = new FileUtils(
				"/home/ec2-user/denny/tmp_choropleth_data");
		File writeFile = new File(
				"/home/ec2-user/denny/predicted_countries/artist_countries.tsv");
		if (!writeFile.exists()) {
			writeFile.createNewFile();
		}
		FileWriter fw = new FileWriter(writeFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		for (String file : fu.listFiles()) {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {

				fields = line.split("\\t");

				try {

					scan = new Scan(Bytes.toBytes(fields[2].substring(0, 6)));
					scan.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("cc"));
					scan.setFilter(filter);
					scanner = table.getScanner(scan);

					// mainObj.put(year, yearTopTracks);

					for (Result res : scanner) {
						cc = Bytes.toString(res.value());
						topCountry
								.put(cc,
										topCountry.containsKey(cc) ? topCountry
												.get(cc) + 1 : 1);

					}

					bw.write(line + "\t"
							+ UtilityFunctions.sortHashMap(topCountry).get(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			br.close();
			bw.close();
		}
		hb.disconnectHBase();
	}

}
