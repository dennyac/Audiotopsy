package com.dennyac.HBaseClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TopSongs {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HBaseConnection hb = new HBaseConnection("msd_hotttnesss","info");
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
					System.out.println("Exception occured in TopSongs");
					e.printStackTrace();
				}
			}
			br.close();
		}
		hb.disconnectHBase();
	}
}
