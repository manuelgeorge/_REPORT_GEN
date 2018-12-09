package com.extractpdfdata.model;

public class RURAL_KARNATAKA_billmodel {
	
	
	@Override
    public String toString() { 
        return String.format(BILLDATE+","+BILLNUMBER+","+CONNECTIONID+","+SERVICENUMBER+","+PVSBILLDATE+","+READINGDATE+","+DUEDATE+","+
    DISCONNDATE+","+FINALREAD+","+INITREAD+","+CONSUMPTION+","+FC+","+EC+","+TAX+","+ARREARS+","+INTEREST+","+REBATE+","+FAC+","+TOTAL+","+
        		BALANCE+","+LASTPAYAMT+","+LASTPAYDATE+","+DEDUCTEDFROMSD+","+TOTALTOBEPAID+","+BALANCESD); 
    } 
	

	
	public String getBILLDATE() {
		return BILLDATE;
	}
	public void setBILLDATE(String bILLDATE) {
		BILLDATE = bILLDATE;
	}
	public String getBILLNUMBER() {
		return BILLNUMBER;
	}
	public void setBILLNUMBER(String bILLNUMBER) {
		BILLNUMBER = bILLNUMBER;
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
	public String getCONSUMPTION() {
		return CONSUMPTION;
	}
	public void setCONSUMPTION(String cONSUMPTION) {
		CONSUMPTION = cONSUMPTION;
	}
	public String getFC() {
		return FC;
	}
	public void setFC(String fC) {
		FC = fC;
	}
	public String getEC() {
		return EC;
	}
	public void setEC(String eC) {
		EC = eC;
	}
	public String getTAX() {
		return TAX;
	}
	public void setTAX(String tAX) {
		TAX = tAX;
	}
	public String getARREARS() {
		return ARREARS;
	}
	public void setARREARS(String aRREARS) {
		ARREARS = aRREARS;
	}
	public String getINTEREST() {
		return INTEREST;
	}
	public void setINTEREST(String iNTEREST) {
		INTEREST = iNTEREST;
	}
	public String getREBATE() {
		return REBATE;
	}
	public void setREBATE(String rEBATE) {
		REBATE = rEBATE;
	}
	public String getFAC() {
		return FAC;
	}
	public void setFAC(String fAC) {
		FAC = fAC;
	}
	public String getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(String tOTAL) {
		TOTAL = tOTAL;
	}
	public String getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}
	public String getLASTPAYAMT() {
		return LASTPAYAMT;
	}
	public void setLASTPAYAMT(String lASTPAYAMT) {
		LASTPAYAMT = lASTPAYAMT;
	}
	public String getLASTPAYDATE() {
		return LASTPAYDATE;
	}
	public void setLASTPAYDATE(String lASTPAYDATE) {
		LASTPAYDATE = lASTPAYDATE;
	}
	public String getDEDUCTEDFROMSD() {
		return DEDUCTEDFROMSD;
	}
	public void setDEDUCTEDFROMSD(String dEDUCTEDFROMSD) {
		DEDUCTEDFROMSD = dEDUCTEDFROMSD;
	}
	public String getTOTALTOBEPAID() {
		return TOTALTOBEPAID;
	}
	public void setTOTALTOBEPAID(String tOTALTOBEPAID) {
		TOTALTOBEPAID = tOTALTOBEPAID;
	}
	public String getBALANCESD() {
		return BALANCESD;
	}
	public void setBALANCESD(String bALANCESD) {
		BALANCESD = bALANCESD;
	}

public void setDefault(String str) {
	BILLDATE=str;BILLNUMBER=str;CONNECTIONID=str;SERVICENUMBER=str;PVSBILLDATE=str;
	READINGDATE=str;DUEDATE=str;DISCONNDATE=str;FINALREAD=str;INITREAD=str;CONSUMPTION=str;
	FC=str;EC=str;TAX=str;ARREARS=str;INTEREST=str;REBATE=str;FAC=str;TOTAL=str;BALANCE=str;
	LASTPAYAMT=str;LASTPAYDATE=str;DEDUCTEDFROMSD=str;TOTALTOBEPAID=str;BALANCESD=str;

}

	String BILLDATE,BILLNUMBER,CONNECTIONID,SERVICENUMBER,PVSBILLDATE,READINGDATE,DUEDATE,DISCONNDATE,FINALREAD;
	String INITREAD,CONSUMPTION,FC,EC,TAX,ARREARS,INTEREST,REBATE,FAC,TOTAL,BALANCE,LASTPAYAMT,LASTPAYDATE,DEDUCTEDFROMSD;
	String TOTALTOBEPAID,BALANCESD;
	

}
