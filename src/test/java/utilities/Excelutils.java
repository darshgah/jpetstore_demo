package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;

public class Excelutils {

	    private static final String FILE_PATH = "testdata\\UserData.xlsx";

	    public static void writeCredentials(String username, String password) throws IOException {
	        XSSFWorkbook workbook;
	        XSSFSheet sheet;

	        File file = new File(FILE_PATH);
	        if (file.exists())
	        {
	            try (FileInputStream fis = new FileInputStream(file))
	            {
	                workbook = new XSSFWorkbook(fis);
	            } 
	            catch (IOException e) 
	            {
	                e.printStackTrace();
	                return;
	            }
	        } 
	        else 
	        {
	            workbook = new XSSFWorkbook();
	        }

	        sheet = workbook.getSheet("logindetails");
	        if (sheet == null) 
	        {
	            sheet = workbook.createSheet("logindetails");
	            XSSFRow header = sheet.createRow(0);
	            header.createCell(0).setCellValue("Username");
	            header.createCell(1).setCellValue("Password");
	    
	        }

	        int lastRow = sheet.getLastRowNum();
	        XSSFRow row = sheet.createRow(lastRow + 1);
	        row.createCell(0).setCellValue(username);
	        row.createCell(1).setCellValue(password);
	        
	      
	        FileOutputStream fos = new FileOutputStream(FILE_PATH);
	        
	            workbook.write(fos);
	            workbook.close();
	        
	    }

	    //Read the user credentials
	    public static String[] readLastCredentials() {
	        try (FileInputStream fis = new FileInputStream(FILE_PATH);
	             XSSFWorkbook workbook = new XSSFWorkbook(fis))
	        {

	            XSSFSheet sheet = workbook.getSheet("logindetails");
	            int lastRow = sheet.getLastRowNum();
	            XSSFRow row = sheet.getRow(lastRow);

	            String username = row.getCell(0).getStringCellValue();
	            String password = row.getCell(1).getStringCellValue();
	           
	            return new String[]{username, password};
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	        
	    }
	    
	    
	    
	     
	}

