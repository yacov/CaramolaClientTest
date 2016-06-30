package il.carambola;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertTrue;

public class LayOut120BrowserDataTest extends TestNgTestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //  public WebDriver driver;

    private SoftAssert softAssert = new SoftAssert();


    @BeforeClass(alwaysRun = true)
    public void initbrowser() throws Exception {


        // driver = new ChromeDriver();


    }

    @BeforeMethod
    public void initPageObjects() {
        //layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);

    }


    //This test runs several times, every iteration with new url. Urls are stored in resources/urlList.data file
// Test Case 1

    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
    public void BasicFullLoad(String url) {

        try {
            driver.manage().window().maximize();
            driver.get(url);
            Log.info("-----   Test Case 1 with URL: " + url + "-----");
            // step 1
            assertTrue("Script is not valid on url" + url, layout_120_page.isScriptValidHere());
            layout_120_page.WaitUntilLayoutIsLoaded();
            //step 2
            layout_120_page.chekLayerisCorrect();
            //step 3
            softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
            Log.info("Assert is OK, centerwrapper exists");
            //step 4
            assertTrue("Cbola board DOESNT exists", layout_120_page.IsBoardExist());
            //step 4.2
            layout_120_page.isFirstImageExists();
            layout_120_page.printImage();


        } catch (Exception e) {
            Log.info(e);
        }
    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
// Test Case 2
    public void HalfGame(String url) {

        try {
            driver.get(url);
            Log.info("-----   Test Case 2 with URL: " + url + "-----");
            assertTrue("Script is not valid on url" + url, layout_120_page.isScriptValidHere());
            layout_120_page.WaitUntilLayoutIsLoaded().chekLayerisCorrect();

            // TODO: 08/06/2016  create loop
            layout_120_page.pressYesButton();
          
          /*layout_120_page.pressYesButton();
          softAssert.assertTrue(layout_120_page.CheckThatYesButtonExists(), "Yes button do not exist");
          Log.info("Assert is OK, button Yes exists");
          layout_120_page.pressNoButton();
          softAssert.assertTrue(layout_120_page.CheckThatYesButtonExists(), "No button do not exist");
          Log.info("Assert is OK, button No exists");*/


        } catch (Exception e) {
            Log.info(e);
        }

        //assertTrue("Yes button do not exist", layout_120_page.CheckThatYesButtonExists());
    }


    /*@AfterClass(alwaysRun = true)
    public void tearDown() {
        this.driver.quit();
    }*/
}

