package com.extractpdfdata.Main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import com.extractpdfdata.utilities.*;
import com.extractpdfdata.textextractor.ExtractPDFData;




public class PDFMain {
	
String status="";
int totalfiles=0;
int totalfileprocessed=0;
String csvfile="";
public PDFMain(){
	try {
	Properties prop=new Utility().loadproperties();
	String pdfdir=prop.getProperty("PDF_DIR");
	ArrayList<String> obj=new Utility().listfiles(pdfdir);
	totalfiles=obj.size();
	if(obj.size()==0) {status="NO FILES FOUND IN PDF_DIRECTORY";}
	else {
		csvfile=prop.getProperty("FILENAME")+new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date());
		BufferedWriter writer = new BufferedWriter(new FileWriter(csvfile));
	    writer.write(new Utility().loadproperties().getProperty("REPORT_COLUMNS"));
	    writer.close();
	    ExtractPDFData data;
	    for(int i=0;i<obj.size();i++) {

		data=new ExtractPDFData(pdfdir+obj.get(i));
		if(data.file_processed)totalfileprocessed=totalfileprocessed+1;
		data.WritetoCSV(csvfile);
		
	}
	    
	status="REPORT GENERATED___AVAILABLE_PDFS___:"+totalfiles+"___PROCESSED_PDFS___:"+(totalfileprocessed+1)+
			"___UNPROCESSED_PDFS___:"+(totalfiles-(totalfileprocessed+1));
	
	}
	}
	catch(Exception e) {
		status=e.getMessage();
	}
	
}
public String getstatus() {
	return status;
	
}
public String getcsvfile() {
	return csvfile;
}
	public static void main(String[] args) throws InvalidPasswordException, IOException {
		new PDFMain();
	}

}
