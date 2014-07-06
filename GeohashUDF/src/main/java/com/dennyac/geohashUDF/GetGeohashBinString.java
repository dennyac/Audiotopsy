package com.dennyac.geohashUDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import ch.hsr.geohash.GeoHash;
 
public final class GetGeohashBinString extends UDF {
  public Text evaluate(final Text s) {
    if (s == null) { return null; }
    
    GeoHash hash;
    try{
    	String[] point = s.toString().split(",");
    	hash = GeoHash.withBitPrecision(Double.parseDouble(point[0]), Double.parseDouble(point[1]), 64);
    }
    catch(Exception e){
    	return new Text("0");
    }
    
    return new Text(hash.toBinaryString());
  }
}
