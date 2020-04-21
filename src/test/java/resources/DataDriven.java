package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// identify Test cases column by scanning the entire 1st row
// once column is identified then scan entire test case column to identify Purchase Test case row
// after you grab Purchase test case row, then pull all the data of that row and feed into the test.
public class DataDriven {

	public ArrayList<String> getData(String testCaseName, String sheetName ) throws IOException {
		ArrayList<String> list = new ArrayList<>();

		// fileInputStream argument
		FileInputStream fis = new FileInputStream("F:\\UdemyREST_API\\ApachePOI_DataDrivenExcel\\ApachePOI_Excel.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet1 = workbook.getSheetAt(i);

				// Step1: identify Test cases column by scanning the entire 1st row
				Iterator<Row> row = sheet1.iterator(); // sheet is a collection of rows
				Row firstRow = row.next();

				Iterator<Cell> cell = firstRow.cellIterator(); // row is a collection of cells

				int k = 0;
				int coloumn = 0;
				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						coloumn = k;
					}
					k++;
				}
				//System.out.println(coloumn);

				// Ste2: once column is identified then scan entire test case column to identify
				// Purchase Test case row
				while (row.hasNext()) {
					Row r = row.next();
					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						// Step3: after you grab Purchase test case row, then pull all the data of that
						// row and feed into the test.
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							
							//list.add(cv.next().getStringCellValue());
							
							if (c.getCellType() == CellType.STRING)
							{
								list.add(c.getStringCellValue());
								
							}
							else
							{
								list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								
							}
						}
					}
				}
			}
		}
		return list;
	}
}
