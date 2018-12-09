package com.pdfextractor.Main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import com.loadConfig.*;
import com.pdftextextractor.ExtractPDFData;




public class PDFMain {
	
	

	public static void main(String[] args) throws InvalidPasswordException, IOException{
		Properties prop=new Loadconfig().loadproperties();
		String csvfile=prop.getProperty("FILENAME")+new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date());
		String pdfdir=prop.getProperty("PDF_DIR");
	    BufferedWriter writer = new BufferedWriter(new FileWriter(csvfile));
	    writer.write(new Loadconfig().loadproperties().getProperty("REPORT_COLUMNS"));
	    writer.close();
		ArrayList obj=new Loadconfig().listfiles(pdfdir);
		
		for(int i=0;i<obj.size();i++) {
			try {
				
			
			ExtractPDFData data=new ExtractPDFData(pdfdir+obj.get(i));
			data.WritetoCSV(csvfile);
			}
			catch(IOException e) {
				
			}
			continue;
			
		}
		
	}

}
