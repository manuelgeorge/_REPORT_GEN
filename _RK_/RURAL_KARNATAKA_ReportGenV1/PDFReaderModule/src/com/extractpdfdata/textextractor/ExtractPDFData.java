package com.extractpdfdata.textextractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import com.extractpdfdata.utilities.*;
import com.extractpdfdata.model.*;



public class ExtractPDFData extends Utility {
	RURAL_KARNATAKA_billmodel billobj;
	String pdffilepath;
	String outputstring[];
	int conditionedarraylength=0;
	String PDFTYPE="";
	Properties prop;
	public Boolean file_processed;

		//constructor
	public ExtractPDFData(String filepath) throws InvalidPasswordException, IOException{
		this.pdffilepath=filepath;
		prop = loadproperties();
		billobj=new RURAL_KARNATAKA_billmodel();
		file_processed=false;
		ExtractDatafromPDF();
		
		}
	//to avoid unwanted empty string		
	String[] arrayconditioning(String[] a){
		String tmp[]=new String[a.length];
		int i=0;
		for(int j=0;j<a.length;j++) {
			if(a[j].length()>3) {
				tmp[i]=a[j];i++;
			}
		}
		conditionedarraylength=i;
		return tmp;
		}
	
	
	//Load and Extract Data from PDF
	public void ExtractDatafromPDF() throws InvalidPasswordException, IOException {
		
		File file = new File(pdffilepath); 
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		document.close();
		
		if(text.contains(prop.getProperty("TYPE1_UNIQUEID"))) {
			PDFTYPE= "TYPE1";
			outputstring= text.split(System.getProperty("line.separator"));
			outputstring=arrayconditioning(outputstring);
			System.out.println(outputstring.length);
			display(outputstring,conditionedarraylength);
			getValueTYPE1();
			System.out.println(billobj.toString());
			file_processed=true;
		}
		else if(text.contains(prop.getProperty("TYPE2_UNIQUEID"))) {
			outputstring= text.split(System.getProperty("line.separator"));
			outputstring=arrayconditioning(outputstring);
			System.out.println(outputstring.length+"---"+conditionedarraylength);
			display(outputstring,conditionedarraylength);
			getValueTYPE2();
			System.out.println(billobj.toString());
			file_processed=true;
		}
		else if(text.contains(prop.getProperty("TYPE3_UNIQUEID"))) {
			outputstring= text.split(System.getProperty("line.separator"));
			outputstring=arrayconditioning(outputstring);
			System.out.println(outputstring.length+"---"+conditionedarraylength);
			display(outputstring,conditionedarraylength);
			getValueTYPE3();
			System.out.println(billobj.toString());
			file_processed=true;
			
			
		}
		else if(text.contains(prop.getProperty("TYPE4_UNIQUEID"))) {
			outputstring= text.split(System.getProperty("line.separator"));
			display(outputstring);
			getValueTYPE4();
			System.out.println(billobj.toString());
			file_processed=true;
		}else {}
	}
	
//--------------------------------------CHAMUNDESWARI--------------------------------------------------------------------------------------------------//
	public void getValueTYPE4() {
		billobj.setDefault(prop.getProperty("TYPE4_FIELD_NOT_AVAILABLE"));
		
		for(int j=0;j<outputstring.length;j++) {
			if(outputstring[j].contains("Bill Date")) {
				try {
				billobj.setBILLDATE(stringafter(outputstring[j],"Bill Date").trim());
				}catch(Exception e) {billobj.setBILLDATE("");}
				}
			if(outputstring[j].contains("Reading Date")) {
				billobj.setREADINGDATE(stringafter(outputstring[j],"Reading Date").trim());
				}
			if(outputstring[j].contains("Bill Number")) {
				billobj.setBILLNUMBER(stringafter(outputstring[j],"Bill Number").trim());
				}
			if(outputstring[j].contains("Present Reading")) {
				billobj.setFINALREAD(stringafter(outputstring[j],"Present Reading").trim());
				}
			if(outputstring[j].contains("Previous Reading")) {
				billobj.setINITREAD(stringafter(outputstring[j],"Previous Reading").trim());
				}
			if(outputstring[j].contains("Units")) {
				billobj.setCONSUMPTION(stringafter(outputstring[j],"Units").trim());
				}
			if(outputstring[j].contains("FC Amount")) {
				billobj.setFC(stringafter(outputstring[j],"FC Amount").trim());
				}
			if(outputstring[j].contains("EC Amount")) {
				billobj.setEC(stringafter(outputstring[j],"EC Amount").trim());
				}
			if(outputstring[j].contains("FAC")) {
				billobj.setFAC(stringafter(outputstring[j],"FAC").trim());
				}
			if(outputstring[j].contains("Arrears")) {
				billobj.setARREARS(stringafter(outputstring[j],"Arrears").trim());
				}
			if(outputstring[j].contains("Tax")) {
				billobj.setTAX(stringafter(outputstring[j],"Tax").trim());
				}
			if(outputstring[j].contains("Net Amount Due")) {
				billobj.setTOTAL(stringafter(outputstring[j],"Net Amount Due").trim());
				}
			if(outputstring[j].contains("Due Date")) {
				billobj.setDUEDATE(stringafter(outputstring[j],"Due Date").trim());
				}
		}
		
		
		
	}
//--------------------------------------CHAMUNDESWARI--------------------------------------------------------------------------------------------------//


	
//--------------------------------------HUBLI--------------------------------------------------------------------------------------------------//
	
	public void getValueTYPE3() {
		billobj.setDefault("-NA-");
		for(int j=0;j<conditionedarraylength;j++) {
			if(outputstring[j].contains("BillDate")&&outputstring[j].contains("MeterNumber")) {
				billobj.setBILLDATE(outputstring[j+1].split("\\s+")[0].trim());
				}
			if(outputstring[j].contains("Tax :")) {
				billobj.setTAX(stringafter(outputstring[j],"Tax :").trim());
				}
						
			if(outputstring[j].contains("BillNumber")) {
				billobj.setBILLNUMBER(stringbetween(outputstring[j], "BillNumber :", "PvsBillDate").trim());
				billobj.setPVSBILLDATE(stringbetween(outputstring[j], "PvsBillDate :", "FC").trim());
				billobj.setFC(stringafter(outputstring[j],"FC :").trim());
				
			}
			if(outputstring[j].contains("ConnectionId :")) {
				billobj.setCONNECTIONID(stringbetween(outputstring[j], "ConnectionId :", "ReadingDate").trim());
				billobj.setREADINGDATE(stringbetween(outputstring[j], "ReadingDate :", "EC").trim());
				billobj.setEC(stringafter(outputstring[j],"EC :").trim());
				
			}
			if(outputstring[j].contains("ServiceNumber :")) {
				billobj.setSERVICENUMBER(stringbetween(outputstring[j], "ServiceNumber :", "NoOfDays").trim());
			}
			if(outputstring[j].contains("DueDate :")) {
				billobj.setDUEDATE(outputstring[j+1].split("\\s+")[0].trim());
			
			}
			//-------//
			if(outputstring[j].contains("Arrears :")) {
				billobj.setARREARS(stringafter(outputstring[j],"Arrears :").trim());
			}
			if(outputstring[j].contains("DisconnDate :")) {
				billobj.setDISCONNDATE(stringbetween(outputstring[j], "DisconnDate :", "Interest").trim());
				billobj.setINTEREST(stringafter(outputstring[j],"Interest :").trim());	
			}
			if(outputstring[j].contains("FinalRead :")) {
				billobj.setFINALREAD(stringbetween(outputstring[j], "FinalRead :", "ACCharge").trim());
					
			}
			if(outputstring[j].contains("NoOfACs")) {
				billobj.setFAC(stringbetween(outputstring[j], "NoOfACs :", "LastPayAmt").trim());
			}
			
			
			if(outputstring[j].contains("InitRead :")) {
				billobj.setINITREAD(stringbetween(outputstring[j], "InitRead :", "Others").trim());
				
			}
			if(outputstring[j].contains("Consumption:")) {
				billobj.setCONSUMPTION(stringbetween(outputstring[j], "Consumption:", "Total").trim());
				billobj.setTOTAL(stringafter(outputstring[j],"Total :").trim());	
			}
			if(outputstring[j].contains("Rebate :")) {
			billobj.setREBATE(stringafter(outputstring[j],"Rebate :").trim());	
			}
			
			if(outputstring[j].contains("Balance")&&!outputstring[j].contains("SDToPay")) {
				billobj.setBALANCE(outputstring[j+1].split("\\s+")[0].trim());
				//System.out.println(outputstring[j].split("\\s+")[0]);
			}
			if(outputstring[j].contains("LastPayAmt")) {
				billobj.setLASTPAYAMT(outputstring[j+1].split("\\s+")[0].trim());
				//System.out.println(outputstring[j].split("\\s+")[0]);
			}
			if(outputstring[j].contains("LastPayDate")) {
				billobj.setLASTPAYDATE(outputstring[j+1].split("\\s+")[0].trim());
				System.out.println(outputstring[j].split("\\s+")[0]);
			}
			if(outputstring[j].contains("DeductedFromSD")) {
				billobj.setDEDUCTEDFROMSD(outputstring[j+1].split("\\s+")[0].trim());
				System.out.println(outputstring[j].split("\\s+")[0]);
			}
			if(outputstring[j].contains("TotalToBePaid")) {
				billobj.setTOTALTOBEPAID(outputstring[j+1].split("\\s+")[0].trim());
				System.out.println(outputstring[j].split("\\s+")[0]);
			}
			
			if(outputstring[j].contains("BalanceSD")) {
				billobj.setBALANCESD(outputstring[j+1].split("\\s+")[0].trim());
				System.out.println(outputstring[j].split("\\s+")[0]);
			}
		}
			
		}

//---------------------------------------------HUBLI----------------------------------------------------------------------//	
	

//---------------------------------------------GULBARGA----------------------------------------------------------------------//
	
	public void getValueTYPE2() {
		billobj.setDefault("-NA-");
		
		for(int j=0;j<conditionedarraylength;j++) {
			if(outputstring[j].contains("BillDate")&&outputstring[j].contains("Tax")) {
				billobj.setBILLDATE(stringbetween(outputstring[j], "BillDate :", "Tax").trim());
				billobj.setTAX(stringafter(outputstring[j],"Tax :").trim());
				
			}
			if(outputstring[j].contains("BillNumber")) {
				billobj.setBILLNUMBER(stringbetween(outputstring[j], "BillNumber :", "PvsBillDate").trim());
				billobj.setPVSBILLDATE(stringbetween(outputstring[j], "PvsBillDate :", "FC").trim());
				billobj.setFC(stringafter(outputstring[j],"FC :").trim());
				
			}
			if(outputstring[j].contains("ConnectionId :")) {
				billobj.setCONNECTIONID(stringbetween(outputstring[j], "ConnectionId :", "ReadingDate").trim());
				billobj.setREADINGDATE(stringbetween(outputstring[j], "ReadingDate :", "EC").trim());
				billobj.setEC(stringafter(outputstring[j],"EC :").trim());
				
			}
			if(outputstring[j].contains("ServiceNumber :")) {
				billobj.setSERVICENUMBER(stringbetween(outputstring[j], "ServiceNumber :", "NoOfDays").trim());
			}
			if(outputstring[j].contains("DueDate :")) {
				billobj.setDUEDATE(stringbetween(outputstring[j], "DueDate :", "Arrears").trim());
				billobj.setARREARS(stringafter(outputstring[j],"Arrears :").trim());
			}
			if(outputstring[j].contains("DisconnDate :")) {
				billobj.setDISCONNDATE(stringbetween(outputstring[j], "DisconnDate :", "Interest").trim());
				billobj.setINTEREST(stringafter(outputstring[j],"Interest :").trim());	
			}
			if(outputstring[j].contains("FinalRead :")) {
				billobj.setFINALREAD(stringbetween(outputstring[j], "FinalRead :", "ACCharge").trim());
					
			}
			if(outputstring[j].contains("NoOfACs")) {
				billobj.setFAC(stringbetween(outputstring[j], "NoOfACs :", "LastPayAmt").trim());
			}
			
			
			if(outputstring[j].contains("InitRead :")) {
				billobj.setINITREAD(stringbetween(outputstring[j], "InitRead :", "Others").trim());
				
			}
			if(outputstring[j].contains("Consumption:")) {
				billobj.setCONSUMPTION(stringbetween(outputstring[j], "Consumption:", "Total").trim());
				billobj.setTOTAL(stringafter(outputstring[j],"Total :").trim());	
			}
			if(outputstring[j].contains("Rebate :")) {
			billobj.setREBATE(stringafter(outputstring[j],"Rebate :").trim());	
			}
			
			if(outputstring[j].contains("Balance")&&!outputstring[j].contains("SDToPay")) {
				billobj.setBALANCE(stringafter(outputstring[j], "Balance  :").trim());
				//System.out.println(outputstring[j].split("\\s+")[0]);
			}
			if(outputstring[j].contains("LastPayAmt")) {
				billobj.setLASTPAYAMT(outputstring[j+1].split("\\s+")[0]);
				//System.out.println(outputstring[j].split("\\s+")[0]);
			}
			if(outputstring[j].contains("LastPayDate")) {
				billobj.setLASTPAYDATE(outputstring[j+1].split("\\s+")[0]);
				System.out.println(outputstring[j].split("\\s+")[0]);
			}
			if(outputstring[j].contains("DeductedFromSD")) {
				billobj.setDEDUCTEDFROMSD(outputstring[j+1].split("\\s+")[0]);
				System.out.println(outputstring[j].split("\\s+")[0]);
			}
			if(outputstring[j].contains("TotalToBePaid")) {
				billobj.setTOTALTOBEPAID(outputstring[j+1].split("\\s+")[0]);
				System.out.println(outputstring[j].split("\\s+")[0]);
			}
			
			if(outputstring[j].contains("BalanceSD")) {
				billobj.setBALANCESD(outputstring[j+1].split("\\s+")[0]);
				System.out.println(outputstring[j].split("\\s+")[0]);
			}
		}
			
		}
//---------------------------------------------GULBARGA----------------------------------------------------------------------//
			

		
//---------------------------------------------Bangalore----------------------------------------------------------------------//
		
	
	public void getValueTYPE1() {
		billobj.setDefault("-NA-");
			for(int j=0;j<conditionedarraylength;j++) {
				if(outputstring[j].contains("BillDate")&&outputstring[j].contains("Tax")) {
					billobj.setBILLDATE(stringbetween(outputstring[j], "BillDate :", "Tax").trim());
					billobj.setTAX(stringafter(outputstring[j],"Tax :").trim());
					
				}
				if(outputstring[j].contains("BillNumber")) {
					billobj.setBILLNUMBER(stringbetween(outputstring[j], "BillNumber :", "PvsBillDate").trim());
					billobj.setPVSBILLDATE(stringbetween(outputstring[j], "PvsBillDate :", "FC").trim());
					billobj.setFC(stringafter(outputstring[j],"FC :").trim());
					
				}
				if(outputstring[j].contains("ConnectionId :")) {
					billobj.setCONNECTIONID(stringbetween(outputstring[j], "ConnectionId :", "ReadingDate").trim());
					billobj.setREADINGDATE(stringbetween(outputstring[j], "ReadingDate :", "EC").trim());
					billobj.setEC(stringafter(outputstring[j],"EC :").trim());
					
				}
				if(outputstring[j].contains("ServiceNumber :")) {
					billobj.setSERVICENUMBER(stringbetween(outputstring[j], "ServiceNumber :", "NoOfDays").trim());
				}
				if(outputstring[j].contains("DueDate :")) {
					billobj.setDUEDATE(stringbetween(outputstring[j], "DueDate :", "Arrears").trim());
					billobj.setARREARS(stringafter(outputstring[j],"Arrears :").trim());
				}
				if(outputstring[j].contains("DisconnDate :")) {
					billobj.setDISCONNDATE(stringbetween(outputstring[j], "DisconnDate :", "Interest").trim());
					billobj.setINTEREST(stringafter(outputstring[j],"Interest :").trim());	
				}
				if(outputstring[j].contains("FinalRead :")) {
					billobj.setFINALREAD(stringbetween(outputstring[j], "FinalRead :", "FAC").trim());
					billobj.setFAC(stringafter(outputstring[j],"FAC :").trim());	
				}
				if(outputstring[j].contains("InitRead :")) {
					billobj.setINITREAD(stringbetween(outputstring[j], "InitRead :", "Others").trim());
					
				}
				if(outputstring[j].contains("Consumption:")) {
					billobj.setCONSUMPTION(stringbetween(outputstring[j], "Consumption:", "Total").trim());
					billobj.setTOTAL(stringafter(outputstring[j],"Total :").trim());	
				}
				if(outputstring[j].contains("Rebate :")) {
				billobj.setREBATE(stringafter(outputstring[j],"Rebate :").trim());	
				}
				
				if(outputstring[j].contains("Balance")&&!outputstring[j].contains("SDToPay")) {
					billobj.setBALANCE(outputstring[j+1].split("\\s+")[0]);
					//System.out.println(outputstring[j].split("\\s+")[0]);
				}
				if(outputstring[j].contains("LastPayAmt")) {
					billobj.setLASTPAYAMT(outputstring[j+1].split("\\s+")[0]);
					//System.out.println(outputstring[j].split("\\s+")[0]);
				}
				if(outputstring[j].contains("LastPayDate")) {
					billobj.setLASTPAYDATE(outputstring[j+1].split("\\s+")[0]);
					System.out.println(outputstring[j].split("\\s+")[0]);
				}
				if(outputstring[j].contains("DeductedFromSD")) {
					billobj.setDEDUCTEDFROMSD(outputstring[j+1].split("\\s+")[0]);
					System.out.println(outputstring[j].split("\\s+")[0]);
				}
				if(outputstring[j].contains("TotalToBePaid")) {
					billobj.setTOTALTOBEPAID(outputstring[j+1].split("\\s+")[0]);
					System.out.println(outputstring[j].split("\\s+")[0]);
				}
				
				if(outputstring[j].contains("BalanceSD")) {
					billobj.setBALANCESD(outputstring[j+1].split("\\s+")[0]);
					System.out.println(outputstring[j].split("\\s+")[0]);
				}
			}
	
	}
	
	
//---------------------------------------------Bangalore----------------------------------------------------------------------//

	

	
	
	public void WritetoCSV(String fullfilepath) throws IOException {
		
		System.out.println("CSV---"+billobj.toString());
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fullfilepath, true));
	    writer.append('\n');
	    writer.append(this.pdffilepath+","+billobj.toString());
	     
	    writer.close();
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new ExtractPDFData("D:\\MyOwnProject\\PDFDir\\PaymentReceipt(19).pdf");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
