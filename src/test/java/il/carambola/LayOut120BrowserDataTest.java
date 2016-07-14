package il.carambola;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class LayOut120BrowserDataTest extends TestNgTestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //  public WebDriver driver;

    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void initPageObjects() {
        //layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);

    }


    //This test runs several times, every iteration with new url. Urls are stored in resources/urlList.data file
// Test Case 1
    @Features("Check if Layer Correct")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
    // IO Exception added after using File export for screenshot
    public void BasicFullLoad(String url) throws IOException {
        long maxPageRunTime = (60 + 10); // 60 for page load + 10 for the test
        driver.manage().timeouts().pageLoadTimeout(maxPageRunTime, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(url);
        Log.info("\n-----  From test: Test Case 1 with URL: " + url + "-----");
            // step 1
        assertTrue("From test: Script is not valid on url " + url, layout_120_page.isScriptValidHere());
        //    layout_120_page.WaitUntilLayoutIsLoaded();
            //step 2
        layout_120_page.chekLayerisCorrect();
            //step 3
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
        Log.info("From test: Assert is OK, centerwrapper exists");
            //step 4
        //softAssert.assertTrue(layout_120_page.IsBoardExist(), "From test: Cbola board DOESNT exists");
            //step 4.2
        layout_120_page.isFirstImageExists(0);
        layout_120_page.printImage(0);
        layout_120_page.isTextExists(0); // its a boolean in case we will want to make Assert
        layout_120_page.isScoreTitleExists();

    }

    @Features("Press buttons")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
// Test Case 2
    public void HalfGame(String url) throws InterruptedException {


        driver.get(url);
            Log.info("-----   Test Case 2 with URL: " + url + "-----");
            assertTrue("Script is not valid on url" + url, layout_120_page.isScriptValidHere());
            layout_120_page.WaitUntilLayoutIsLoaded().chekLayerisCorrect();

            layout_120_page.pressTrueButton(3);
            layout_120_page.pressFalseButton(2);

        // TODO: 13/07/2016
        // method: click + check image

          /*layout_120_page.pressYesButton();
          softAssert.assertTrue(layout_120_page.CheckThatYesButtonExists(), "Yes button do not exist");
          Log.info("Assert is OK, button Yes exists");
          layout_120_page.pressNoButton();
          softAssert.assertTrue(layout_120_page.CheckThatYesButtonExists(), "No button do not exist");
          Log.info("Assert is OK, button No exists");*/


        }

        //assertTrue("Yes button do not exist", layout_120_page.CheckThatYesButtonExists());



    /*@AfterClass(alwaysRun = true)
    public void tearDown() {
        this.driver.quit();
    }*/
}

