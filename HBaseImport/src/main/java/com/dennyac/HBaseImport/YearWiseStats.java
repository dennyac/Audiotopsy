package com.dennyac.HBaseImport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class YearWiseStats{
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HBaseWrite hb = new HBaseWrite("msd_year","info");
		hb.connectHBase();
		String[] fields = null;
		FileUtils fu = new FileUtils("/home/ec2-user/denny/tmp");
		for(String file: fu.listFiles()){
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				try{
					
					fields = line.split("\\t");
			        hb.put(fields[0], "ht", fields[1]);
			        hb.put(fields[0], "du", fields[2]);
			        hb.put(fields[0], "tp", fields[3]);
			        hb.put(fields[0], "db", fields[4]);
			        hb.put(fields[0], "dn", fields[5]);
			        hb.put(fields[0], "eg", fields[6]);
   				  }
				catch(Exception e){
					System.out.println(e.getMessage());
				}
     
			}
			br.close();
		}
	}
}