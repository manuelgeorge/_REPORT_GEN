package com.pdftextextractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import com.loadConfig.*;
import com.model.schema.*;



public class ExtractPDFData {
	
	String pdffilepath;
	String outputstring[];
	String PDFTYPE="";
	Properties prop;
	StringBuilder rowarray;
	Model obj;
	String row;
	int Type1_Defaultrow;
	//constructor
	public ExtractPDFData(String filepath) throws InvalidPasswordException, IOException{
		this.pdffilepath=filepath;
		prop = new Loadconfig().loadproperties();
		rowarray=new StringBuilder();
		obj= new Model();
		obj.setPDF_FILE(filepath);
		ExtractDatafromPDF();
		}
	
	
	//Load and Extract Data from PDF
	public void ExtractDatafromPDF()  throws InvalidPasswordException, IOException {
		
		File file = new File(pdffilepath); 
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		document.close();
		
		if(text.contains(prop.getProperty("TYPE2_UNIQUEID"))) {
			PDFTYPE= "TYPE2";
			outputstring= text.split("\\s+");
			//display();
			getValueTYPE2();
		}
		else {
			PDFTYPE="TYPE1";
			outputstring = text.split(System.getProperty("line.separator"));
			display();
			//System.out.println(text);
			getValueTYPE1();
		}
				
		
		}
	

	
	public void getValueTYPE2() {
		
		String[] column_element=prop.getProperty(PDFTYPE+"_COLUMNS").split(",");
		
		rowarray.append(this.pdffilepath+",");
		for(int i=0;i<column_element.length;i++) {
						
				if(this.getValueTYPE2(column_element[i]).charAt(0)==':') 
				{rowarray.append(this.getValueTYPE2(column_element[i]).replaceFirst(":",""));
				
				//System.out.println(rowarray[j]);
				}
				else {
					rowarray.append(this.getValueTYPE2(column_element[i]));
					//System.out.println(rowarray[j]);
				}
				rowarray.append(",");
				
	}
	}
	public String getValueTYPE2(String key) {
		String value="";
		for(int i=0;i<outputstring.length;i++) {
			if(outputstring[i].contains(key)&&!outputstring[i+1].equals(":")) 
			{System.out.println(outputstring[i]+"---"+outputstring[i+1]);
			value=outputstring[i+1];}
			else if(outputstring[i].contains(key)) 
			{System.out.println(outputstring[i]+"---"+outputstring[i+2]);
			value=outputstring[i+2];}
		}
		return value;
		
		
	}
	
	
	
	public void display() {
		for(int i=0;i<outputstring.length;i++) {
			System.out.println(i+"--"+outputstring[i]);
		}
		
	}
	

	
	//table1oftype1
	public String getValueTYPE1_tab1(String key) {
		System.out.println("--------"+key);
		String tab= "Deformed PDF,File,Detected,Please, open,the,PDF,and,do the entry,manually";
		try {
		String[] value=key.split("\\s+");
		tab=value[0]+","+value[1]+","+value[2]+","+value[3]+","+value[6]+","+value[6]+","+value[7]+","+value[8]+",";
		}
		catch(Exception e) {
			
		}
		
		return tab;
			
		
	}
	//NON_TABLE ITEMS IN TYPE1_PDF
	public void getValueTYPE1() {
		StringBuilder temp;
		

			for(int j=0;j<outputstring.length;j++) {
				if(outputstring[j].contains(prop.getProperty("TYPE1_DEFAULT_ROW")))
				{
					rowarray.append(this.pdffilepath+","+getValueTYPE1_tab1(outputstring[j+1]));
					
				}
								
					
					if(outputstring[j].contains("Reading")) {
						//System.out.println("-------------"+outputstring[j].split("\\s+").length);
						if(outputstring[j].split("\\s+").length>=3&&(outputstring[j-1].contains("Previous"))) {
						rowarray.append((outputstring[j].split("\\s+")[2]).trim()+",");
						System.out.println("------------MMMMMMMMMMMM-------"+outputstring[j].split("\\s+")[2]);
						}
						if(outputstring[j].split("\\s+").length>=3&&(outputstring[j].split("\\s+")[0].contains("Previous")||outputstring[j].split("\\s+")[0].contains("Present")))
						{rowarray.append((outputstring[j].split("\\s+")[3]).trim()+",");	
						System.out.println("--------MMMMM--------"+outputstring[j].split("\\s+")[3]);
					}
					
					//minor adjustment to handle previous Reading -Special case
					
				}
					if(outputstring[j].contains("Interest on Revenue")||outputstring[j].contains("Arrears")||outputstring[j].contains("Net Payable Amount"))
					{rowarray.append(outputstring[j].replaceAll("[^0-9|.|-]","").trim()+",");}
					System.out.println(outputstring[j].replaceAll("[^0-9|.|-]","").trim()+",");
					}
				}
			
		
	
	
	

	
	public void WritetoCSV(String fullfilepath) throws IOException {
		
		System.out.println(rowarray);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fullfilepath, true));
	    writer.append('\n');
	    writer.append(rowarray.toString());
	     
	    writer.close();
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ExtractPDFData obj=new ExtractPDFData("D:\\MyOwnProject\\PDFDir\\3216967000_321694375494_01-09-2018.pdf");
			//obj.getValueTYPE2("PvsBillDate");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
