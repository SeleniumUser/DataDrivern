package com.canopy.framework.datadriven.util;

import java.util.Hashtable;

public class Utility {
	// test suite is runnable
	public static boolean isSuiteRunnable(String suiteName,Xls_Reader xls) {
		int rows = xls.getRowCount(Constants.SUITE);
		for(int rNum=2;rNum<=rows;rNum++){
			String data = xls.getCellData(Constants.SUITE,Constants.SUITENAME_COL, rNum);
			System.out.println(data);
			if(data.equalsIgnoreCase(suiteName)){
				String runmode= xls.getCellData(Constants.SUITE,Constants.RUNMODE_COL, rNum);
				if(runmode.equals(Constants.RUNMODE_YES))
				  return true ;
				else
				  return false ;
				
			}
		}
		return false ;
	}
	
 public static boolean isTestCaseRunnable(String testCase,Xls_Reader xls){
	 int rows= xls.getRowCount(Constants.TESTCASES_SHEET);
	 for(int rNum=2;rNum<=rows;rNum++){
		String testNameXls =xls.getCellData(Constants.TESTCASES_SHEET,Constants.TESTCASES_COL, rNum);
		if(testNameXls.equalsIgnoreCase(testCase)){
		  String runmode = xls.getCellData(Constants.TESTCASES_SHEET,Constants.RUNMODE_COL, rNum);
		  if(runmode.equalsIgnoreCase(Constants.RUNMODE_YES))
			  return true;
		  else 
			  return false ;

		}
	 }
		return false; //default

 }

 public static Object[] [] getData(String testName,Xls_Reader xls){
	 int rows = xls.getRowCount(Constants.DATA_SHEET);
     System.out.println("Total Rows - "+rows);
     
     //row Number of testCase
     int testCaseRowNum=1;
     for(testCaseRowNum=1;testCaseRowNum<=rows;testCaseRowNum++){
     String testNameXls=	xls.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
     if(testNameXls.equalsIgnoreCase(testName))
     	break ;
    }
     System.out.println("Test Starts from row Number - "+testCaseRowNum);

     int dataStartRowNum=testCaseRowNum+2;
     int ColStartRowNum=testCaseRowNum+1;
     
     //rows of Data
     
     int testRows=1;
     while(!xls.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).equals("")){
     	testRows++ ;
     	
     }
     System.out.println("Total Rows of Data are - " +testRows);
            
     int testCols=0 ;
     while(!xls.getCellData(Constants.DATA_SHEET,testCols ,ColStartRowNum).equals("")){
 	   testCols++ ;
    }
    Object [][] data = new Object[testRows][1];
    //System.out.println("Total Cols - " + testCols);
    int r=0 ;
    for(int rNum=dataStartRowNum;rNum <(dataStartRowNum+testRows);rNum++){
    	Hashtable<String,String> table= new Hashtable<String,String> () ;
 	   for(int cNum=0;cNum<testCols;cNum++){
 		 //  System.out.println(xls.getCellData(Constants.DATA_SHEET, cNum, rNum));
 		//  data[r][cNum]=xls.getCellData(Constants.DATA_SHEET, cNum, rNum) ;
 		   table.put(xls.getCellData(Constants.DATA_SHEET, cNum, ColStartRowNum), xls.getCellData(Constants.DATA_SHEET, cNum, rNum));
 	   }
 	    data[r][0]=table;
 	     r++ ;
    }
    return data ;
 }
 
	
}
