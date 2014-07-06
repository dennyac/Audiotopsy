package com.dennyac.HBaseClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AllCountriesHash {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HBaseConnection hb = new HBaseConnection("all_countries_hash");
		String[] fields = null;
		FileUtils fu = new FileUtils("/home/ec2-user/denny/tmp_all_countries_hash");
		for(String file: fu.listFiles()){
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				try{
					
					fields = line.split("\\t");
					if(fields[6].length() == 64 && fields[9].length() == 2){
						hb.put(fields[6] + fields[0],"cf1", "cc", fields[9]);
						if(fields[11].trim().length() != 0){
							hb.put(fields[6] + fields[0],"cf1", "a1", fields[11]);
						}			        
					}
			        
   				  }
				catch(Exception e){
					System.out.println("Exception occured in AllCountriesHash");
					e.printStackTrace();
				}
			}
			br.close();
		}
		hb.disconnectHBase();
	}
}