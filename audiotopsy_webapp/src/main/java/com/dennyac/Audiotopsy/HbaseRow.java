package com.dennyac.Audiotopsy;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HbaseRow {
	String key;
	HashMap<String,String> colVal;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public HashMap<String, String> getColVal() {
		return colVal;
	}
	public void setColVal(HashMap<String, String> colVal) {
		this.colVal = colVal;
	}
}
