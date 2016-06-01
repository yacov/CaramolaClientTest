/**
 * Created by Yair on 18/05/2016.
 */

package il.carambola;

import il.carambola.pages.Layout_120_Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Layout120Test_TC1_BasicFullLoad {
    public WebDriver driver;
    public Layout_120_Page layout_120_page;
    Integer layoutNumber = 120;

    String browserName = "fireFox";
    Integer urlNo = 0;

    @BeforeClass
    public void initbrowser() {
        driver = new FirefoxDriver();
        layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);
    }

   /* @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
    public void EntryPoint(String url){
        driver.get(url);
        List<String> errors = new ArrayList<String>();
       boolean isScriptValid=   Layout_120_Page.IsScriptValid(driver,errors,browserName,urlNo);
        boolean EntryPointTestRespone = EntryPointTest();
        assertTrue("Yes button do not exist", !isScriptValid);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
    public boolean EntryPointTest(){
        List<String> errors = new ArrayList<String>();
        boolean isScriptValid=   Layout_120_Page.IsScriptValid(driver,errors,browserName,urlNo);
        //assertTrue("Yes button do not exist", !isScriptValid);
        return isScriptValid;
    }*/

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.driver.quit();
    }


}