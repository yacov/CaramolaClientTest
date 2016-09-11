package il.carambola;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

public class Layout120_Ads_Tests extends TestNgTestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //  public WebDriver driver;

    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void initPageObjects() {
        //layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);

    }

    //This test runs several times, every iteration with new url. Urls are stored in resources/urlList.data file
// Test Case 1
    @Features("Ads tests")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", timeOut = 60000)
    // (groups = {"ADS", "positive"},
    // IO Exception added after using File export for screenshot
    public void AdLoading(String url) throws IOException, InterruptedException, EmailException {

        Log.info("time before loaing page");
        long maxPageRunTime = (30 + 10); // 30 for page load + 10 for the test
        //driver.manage().timeouts().pageLoadTimeout(maxPageRunTime, TimeUnit.SECONDS); // will work on the pages with synch loading, but this doesn't solve the problem on pages loading stuff in asynch, the tests will fail all the time if we set the pageLoadTimeOut.
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get(url);
        // wait.until(ExpectedConditions.titleContains("Rushing")); // nice to have- dont start until element X is on page
        Log.info("\n-----  Test Case 1: ** ADS test ** with URL: " + url + "-----");

        // step 1
        //** make sure the method is TRUE. if not- log a message
        assertTrue("From test: Script is not valid on url " + url, layout_120_page.isScriptValidHere());
        //    layout_120_page.WaitUntilLayoutIsLoaded();
        layout_120_page.chekLayerisCorrect();
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
        Log.info("From test: Assert is OK, centerWrapper exists");
        // scroll to unit (to see the x btn. in co-optimus.com it fails)
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('InContent-container-centerWrapper0').scrollIntoView(true);");
        jse.executeScript("window.scrollBy(0,-150)");
        System.out.println("JS scroll executed");
        //step 2
        //softAssert.assertTrue(layout_120_page.isAdAppearance(1,0),"couldnt find banner 300*250");
        assertTrue("couldnt find banner 300*250", layout_120_page.isAdAppearance(1,0));
        assertTrue("couldnt find banner 728*90",layout_120_page.isAdAppearance(4,0));
        assertTrue("couldnt find x btn", layout_120_page.isAdCloseBtnExists(4,true));


    }


}
