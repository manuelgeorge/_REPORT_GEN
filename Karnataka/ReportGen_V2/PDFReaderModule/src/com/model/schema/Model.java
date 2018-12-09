package com.model.schema;

public class Model {
	public String getPDF_FILE() {
		return PDF_FILE;
	}






	public void setPDF_FILE(String pDF_FILE) {
		PDF_FILE = pDF_FILE;
	}






	public String getCONNECTIONID() {
		return CONNECTIONID;
	}






	public void setCONNECTIONID(String cONNECTIONID) {
		CONNECTIONID = cONNECTIONID;
	}






	public String getSERVICENUMBER() {
		return SERVICENUMBER;
	}






	public void setSERVICENUMBER(String sERVICENUMBER) {
		SERVICENUMBER = sERVICENUMBER;
	}






	public String getBILLNUMBER() {
		return BILLNUMBER;
	}






	public void setBILLNUMBER(String bILLNUMBER) {
		BILLNUMBER = bILLNUMBER;
	}






	public String getPVSBILLDATE() {
		return PVSBILLDATE;
	}






	public void setPVSBILLDATE(String pVSBILLDATE) {
		PVSBILLDATE = pVSBILLDATE;
	}






	public String getREADINGDATE() {
		return READINGDATE;
	}






	public void setREADINGDATE(String rEADINGDATE) {
		READINGDATE = rEADINGDATE;
	}






	public String getBILLDATE() {
		return BILLDATE;
	}






	public void setBILLDATE(String bILLDATE) {
		BILLDATE = bILLDATE;
	}






	public String getDUEDATE() {
		return DUEDATE;
	}






	public void setDUEDATE(String dUEDATE) {
		DUEDATE = dUEDATE;
	}






	public String getDISCONNDATE() {
		return DISCONNDATE;
	}






	public void setDISCONNDATE(String dISCONNDATE) {
		DISCONNDATE = dISCONNDATE;
	}






	public String getFINALREAD() {
		return FINALREAD;
	}






	public void setFINALREAD(String fINALREAD) {
		FINALREAD = fINALREAD;
	}






	public String getINITREAD() {
		return INITREAD;
	}






	public void setINITREAD(String iNITREAD) {
		INITREAD = iNITREAD;
	}






	public String getINTEREST() {
		return INTEREST;
	}






	public void setINTEREST(String iNTEREST) {
		INTEREST = iNTEREST;
	}






	public String getARREARS() {
		return ARREARS;
	}






	public void setARREARS(String aRREARS) {
		ARREARS = aRREARS;
	}






	public String getTOTAL() {
		return TOTAL;
	}






	public void setTOTAL(String tOTAL) {
		TOTAL = tOTAL;
	}






	String PDF_FILE;
	String CONNECTIONID;
	String SERVICENUMBER;
	String BILLNUMBER;
	String PVSBILLDATE;
	String READINGDATE;
	String BILLDATE;
	String DUEDATE;
	String DISCONNDATE;
	String FINALREAD;
	String INITREAD;
	String INTEREST;
	String ARREARS;
	String TOTAL;
	
	
	
	@Override
	public String toString() {
		return PDF_FILE+","+CONNECTIONID+","+SERVICENUMBER+","+BILLNUMBER+","+PVSBILLDATE+","+READINGDATE+","+BILLDATE+","+DUEDATE+","+DISCONNDATE+","+FINALREAD+","+INITREAD+","+INTEREST+","+ARREARS+","+TOTAL;
		
	}
	
	
	public static void main(String[] args) {
		Model jd= new Model();
		jd.setBILLDATE("23-44-55");
		System.out.println(jd.toString());
		// TODO Auto-generated method stub

	}

}
