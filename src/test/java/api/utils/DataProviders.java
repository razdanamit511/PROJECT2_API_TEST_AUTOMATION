
package api.utils;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "AllData")
	public String[][] getAllData() throws IOException {

		File file1 = new File("TestData\\UserData.xlsx");

		String XLFIle = file1.getAbsolutePath();
		String sheetName = "Sheet1";
		int rowcount = XLUtils.getRowCount(XLFIle, sheetName);
		int colcount = XLUtils.getCellCount(XLFIle, sheetName, 0);

		String apiData[][] = new String[rowcount][colcount];

		for (int i = 1; i <= rowcount; i++) {

			for (int j = 0; j < colcount; j++) {

				apiData[i - 1][j] = XLUtils.getCellData(XLFIle, sheetName, i, j);

			}
		}

		return apiData;

	}
}
