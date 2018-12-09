package com.loadConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Loadconfig {
	
  public Properties loadproperties() throws IOException {
	Properties prop = new Properties();
	InputStream input = null;
	input = new FileInputStream("Config/Config.properties");
	prop.load(input);
	input.close();
	return prop;
  }
  
  public ArrayList<String> listfiles(String directory) {
	  File folder = new File(directory);
	  File[] listOfFiles = folder.listFiles();
	  ArrayList<String> pdffiles=new ArrayList<String>(); 
	  for (int i = 0; i < listOfFiles.length; i++) {
	    if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".pdf") ) {
	      System.out.println("File " + listOfFiles[i].getName());
	      pdffiles.add(listOfFiles[i].getName());
	      } 
	  }
	  
	  return pdffiles;
	  
  }
  
  public static void main(String[] args) throws IOException {

	  Loadconfig obj= new Loadconfig();
	  //Properties  pop= obj.loadproperties();
	  obj.listfiles("D:\\MyOwnProject\\PDFDir");
	  
	  
  }
}
