package com.dennyac.HBaseImport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TopSongsYearwise{
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HBaseWrite hb = new HBaseWrite("msd_hotttnesss_yearwise","info");
		hb.connectHBase();
		String[] fields = null;
		FileUtils fu = new FileUtils("/home/ec2-user/denny/tmp_hotttnesss_yearwise");
		for(String file: fu.listFiles()){
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				try{
					
					fields = line.split("\\t");
			        hb.put(fields[0], "an", fields[1]);
			        hb.put(fields[0], "sn", fields[2]);
			        hb.put(fields[0], "al", fields[3]);
   				  }
				catch(Exception e){
					System.out.println(e.getMessage());
				}
     
			}
			br.close();
		}
	}
}