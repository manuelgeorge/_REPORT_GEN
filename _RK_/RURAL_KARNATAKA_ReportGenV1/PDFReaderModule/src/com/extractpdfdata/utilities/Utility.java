package com.extractpdfdata.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Utility {
	
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
  
	public String stringbetween(String main,String sub1,String sub2) {
		String tmp=main.split(sub2)[0];
		return tmp.split(sub1)[1];
	}
	public String stringafter(String main,String sub1) {
		return main.split(sub1)[1];
	}
	
	public void display(String[] outputstring) {
		for(int i=0;i<outputstring.length;i++) {
			System.out.println(i+"--"+outputstring[i].trim());
		}
		
	}
	public void display(String[] outputstring,int length) {
		for(int i=0;i<length;i++) {
			System.out.println(i+"--"+outputstring[i].trim());
		}
		
	}
  
  public static void main(String[] args) throws IOException {

	  Utility obj= new Utility();
	  //Properties  pop= obj.loadproperties();
	  obj.listfiles("D:\\MyOwnProject\\PDFDir");
	  
	  
  }
  
  
}
