package com.dennyac.HBaseImport;

import java.io.File;
import java.util.ArrayList;

public class FileUtils {

	File basePath;
	
	FileUtils(String path){
		this.basePath = new File(path);
	}
	
	public ArrayList<String> listFiles() {
		ArrayList<String> files = new ArrayList<String>();
		for (final File fileEntry : basePath.listFiles()) {
			if (!fileEntry.isDirectory()) {
				files.add(fileEntry.getAbsolutePath());

			}
		}
		return files;
	}

}
