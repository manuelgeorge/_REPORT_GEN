package com.extractpdfdata.ui;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.swing.*;

import com.extractpdfdata.Main.PDFMain;
import com.extractpdfdata.utilities.*;


public class ReportGenUI {
	   private JFrame mainFrame;
	   private JLabel state_Label;
	   private JLabel inputdir_Label;
	   private JLabel outputdir_Label;
	   private JLabel status_label;
	   private JPanel controlPanel;
	   public Properties prop1;
	    
	   public ReportGenUI() throws IOException{
		  prop1=new Utility().loadproperties();
	      prepareGUI();
	   }
	   public static void main(String[] args) throws IOException{
		   ReportGenUI swingControlDemo = new ReportGenUI();  
	      swingControlDemo.showEventDemo();       
	   }
	   private void prepareGUI(){
	      mainFrame = new JFrame("REPORT_GEN UI-1.0");
	      mainFrame.setContentPane(new JLabel(new ImageIcon("Config/ascend.png")));
	      mainFrame.setSize(800,200);
	      mainFrame.setLayout(new GridLayout(5, 1));
	      state_Label = new JLabel("",JLabel.LEFT); 
	      inputdir_Label = new JLabel("",JLabel.LEFT);
	      outputdir_Label = new JLabel("",JLabel.LEFT);
	      status_label=new JLabel("",JLabel.LEFT);
	      state_Label.setText("STATE____:_"+prop1.getProperty("STATE"));
	      inputdir_Label.setText("PDF_DIR____:_"+prop1.getProperty("PDF_DIR"));
	      outputdir_Label.setText("REPORT_DIR____:_"+prop1.getProperty("FILENAME"));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());
	      mainFrame.add(state_Label);
	      mainFrame.add(inputdir_Label);
	      mainFrame.add(outputdir_Label);
	      mainFrame.add(status_label);
	      mainFrame.add(controlPanel);
	      mainFrame.setVisible(true);  
	   }
	   private void showEventDemo(){
	      JButton okButton = new JButton("OK");
	      okButton.setActionCommand("OK");
	      okButton.addActionListener(new ButtonClickListener()); 
	      controlPanel.add(okButton);
	      mainFrame.setVisible(true);  
	   }
	   private class ButtonClickListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	    	  
				PDFMain ps = new PDFMain();
			    status_label.setText(ps.getstatus());
			    outputdir_Label.setText("REPORT_FILE___:_"+ps.getcsvfile());
			
	        	
	      }		
	   }
	}
		 
		 
		 

