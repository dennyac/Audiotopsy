package com.dennyac.HBaseClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TopSongsYearwise{
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HBaseConnection hb = new HBaseConnection("msd_hotttnesss_yearwise","info");
		String[] fields = null;
		FileUtils fu = new FileUtils("/home/ec2-user/denny/tmp_msd_hotttnesss_yearwise");
		System.out.println("The size is " + fu.listFiles().size());
		for(String file: fu.listFiles()){
			System.out.println("The file is " + file);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				try{
					fields = line.split("\\t");
					int year = Integer.parseInt(fields[0].substring(0, 4));
					if(year > 1900 && year < 2015){
				        hb.put(fields[0], "an", fields[1]);
				        hb.put(fields[0], "sn", fields[2]);
				        hb.put(fields[0], "al", fields[3]);
				        hb.put(fields[0], "ht", fields[4]);
					}
   				  }
				catch(Exception e){
					System.out.println("Exception occured in TopSongsYearwise");
					e.printStackTrace();
				}
			}
			br.close();
		}
		hb.disconnectHBase();
	}
}