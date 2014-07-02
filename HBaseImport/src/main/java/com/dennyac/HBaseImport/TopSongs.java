package com.dennyac.HBaseImport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TopSongs {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HBaseWrite hb = new HBaseWrite("msd_hotttnesss","info");
		hb.connectHBase();
		String[] fields = null;
		FileUtils fu = new FileUtils("/home/ec2-user/denny/tmp_msd_hotttnesss");
		for(String file: fu.listFiles()){
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				try{
					fields = line.split("\\t");
					if(fields[0].substring(0,2).equals("0.") ||
							fields[0].substring(0,2).equals("1.")){	
				        hb.put("hot" + fields[0], "an", fields[1]);
				        hb.put("hot" + fields[0], "sn", fields[2]);
				        hb.put("hot" + fields[0], "al", fields[3]);
				        hb.put("hot" + fields[0], "ht", fields[4]);
					}
   				  }
				catch(Exception e){
					e.printStackTrace();
				}
			}
			br.close();
		}
	}
}
