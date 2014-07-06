package com.dennyac.HBaseClient;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class UtilityFunctions {
	public static <E> List<E> sortHashMap(  final HashMap<E, Integer> map) {
	    Set<E> set = map.keySet();
	    List<E> keys = new ArrayList<E>(set);

	    Collections.sort(keys, new Comparator<E>() {

	        public int compare(E d1, E d2) {
	        	 return Integer.compare(map.get(d2), map.get(d1));
	        }
	    });
	    return keys;
	}
	
}

