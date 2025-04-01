package utilities;

import java.io.IOException;
public class DataProvider {
	//DataProvider
	@org.testng.annotations.DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
	//String path="./openCart123/testData//openCart_LoginData.xlsx";
		String path=".//testData//openCart_LoginData.xlsx";//taking xl file from testData
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for xlUtility
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet", 1);
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store data
		for(int i=1;i<=totalrows;i++) {//1//read the data from xl storing in 2 dimensional array
			for(int j=0;j<totalcols;j++) //0//i is row j is col
				{
				logindata[i-1][j]=xlutil.getCellData("Sheet", i, j);//1,0
			}
		}
		return logindata;
	}
	
	//DataProvider3...

}