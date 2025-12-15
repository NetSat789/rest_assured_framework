package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{

    // ---------------- DATA FOR CREATE USER --------------------
    @DataProvider(name = "Data") 
    public String[][] getAllData() throws IOException
    {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);       
        String apidata[][] = new String[rownum][colcount];
     
        for (int i = 1; i <= rownum; i++)   // rows start from 1
        {         
            for (int j = 0; j < colcount; j++) // columns start from 0
            {     
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }

    // ---------------- DATA FOR DELETE USER --------------------
    
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException
    {

        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum = xl.getRowCount("Sheet1");      
        String apidata[] = new String[rownum];
        
        for (int i = 1; i <= rownum; i++)
        {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1); // column 1 = username
        }
        return apidata;
    }
}
