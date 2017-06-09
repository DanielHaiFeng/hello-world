package com.xa.boco;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.xa.boco.util.UUIDUtil;

public class ReadExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	    InputStream is = null;
	    XSSFWorkbook wb = null;
	    try {
	        is = new FileInputStream("C:/Users/boco-dt/Desktop/参数列表.xlsx");
	        wb = new XSSFWorkbook(is);
	         
	        
	        Sheet sheet = wb.getSheetAt(2);
	        
	        Iterator<Row> iterator = sheet.rowIterator();
	        
	        while (iterator.hasNext()) {
	        	Row rw = iterator.next();
	        	if(rw.getRowNum() == 0 || rw.getRowNum() == 1 || rw.getRowNum() == 2 || rw.getRowNum() == 3){
	        		continue;
	        	}
	        	System.out.println("insert into sub_system_para(para_id,para_name,para_default_value,para_type,sub_system_id) "
	        			+ "values('"+UUIDUtil.getUUID()+"','"+removeSquareBrackets(rw.getCell(0).toString())+"','"+convertNumber(rw.getCell(1).toString(),removeBrackets(changeVarchar(removeSquareBrackets(rw.getCell(2).toString()))))+"','"+removeBrackets(changeVarchar(removeSquareBrackets(rw.getCell(2).toString())))+"','5');");
			}
	         
	    } catch (FileNotFoundException e) {
	        throw e;
	    } finally {
	        if (is != null) {
	            is.close();
	        }
	    }
	}
	
	public static String removeSquareBrackets(String orignalString){
		String rmLeft = orignalString.replaceAll("\\[", "");
		return rmLeft.replaceAll("\\]", "");
	}
	
	public static String changeVarchar(String orignalString){
		return orignalString.replaceAll("nvarchar", "varchar");
	}
	
	public static String removeBrackets(String orignalString){
		int endIndex = orignalString.indexOf("(");
		if(endIndex == -1){
			return orignalString;
		}
		return orignalString.substring(0, endIndex);
	}
	
	public static String convertNumber(String data,String type){
		String rString = "";
		if(type.equals("bit") || type.equals("int")){
			rString = data.substring(0, data.indexOf("."));
		}else{
			rString = data;
		}
		
		return rString;
	}
}
