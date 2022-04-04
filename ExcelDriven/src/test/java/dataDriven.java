import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//fileInputStream argument
		FileInputStream fis= new FileInputStream("C://Users//nahid.mahmud//Downloads//demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheets= workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
			{
			XSSFSheet sheet= workbook.getSheetAt(i);
			Iterator <Row> rows = sheet.rowIterator();//sheet is collection of rows
			Row firstrow= rows.next();
			Iterator<Cell> ce = firstrow.cellIterator();//row is collection of cells
			int k=0;
			int coloumn = 0;
		while(ce.hasNext())
		{
				Cell value= ce.next();
				if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
				{
					coloumn=k;
				}
				k++;
			}
		System.out.println(coloumn);
		
		//rows counts for the column
		
		while(rows.hasNext())
		{
			Row r= rows.next();
			if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase("Purchase"))
			{
				//after grap pull all the data
				Iterator<Cell> cv= r.cellIterator();
				while(cv.hasNext())
				{
					System.out.println(cv.next().getStringCellValue()) ;
				}

			}
			
		}
		
}
		}
	}
}