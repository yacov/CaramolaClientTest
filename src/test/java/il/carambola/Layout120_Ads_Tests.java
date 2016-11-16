package il.carambola;

import il.carambola.pages.Layout_120_Page;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertFalse;
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
    @Features("Ads- close trigger 4 with btn")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", timeOut = 60000)
    // (groups = {"ADS", "positive"},
    // IO Exception added after using File export for screenshot
    public void openAdsOnloadTriggers1and4Close4WithXbtn(String url) throws IOException, InterruptedException, EmailException, AWTException {

        Log.info("from test: before get.url");
        long maxPageRunTime = (30 + 10); // 30 for page load + 10 for the test
        //driver.manage().timeouts().pageLoadTimeout(maxPageRunTime, TimeUnit.SECONDS); // will work on the pages with synch loading, but this doesn't solve the problem on pages loading stuff in asynch, the tests will fail all the time if we set the pageLoadTimeOut.

        driver.manage().window().maximize();
        driver.navigate().to(url); // can also use driver.get(url). there's no performance diff

        // wait.until(ExpectedConditions.titleContains("Rushing")); // nice to have- dont start until element X is on page
        Log.info("\n-----  Test Case 1: ** ADS test ** \nURL: " + url + "-----");

        // step 1
        //** make sure the method is TRUE. if not- log a message
        assertTrue("From test: Script is not valid on url " + url, layout_120_page.isScriptValidHere2());
        //    layout_120_page.WaitUntilLayoutIsLoaded();
        layout_120_page.chekLayerisCorrect();
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
        Log.info("From test: Assert is OK, centerWrapper exists");
        // scroll to unit (to see the x btn. in co-optimus.com it fails)
        layout_120_page.scrollUnit();

        //step 2
        softAssert.assertTrue(layout_120_page.isAdAppearance(1,0),"couldnt find banner 300*250");
        //assertTrue("couldn't find banner 300*250", layout_120_page.isAdAppearance(1,0));
        assertTrue("couldn't find banner 728*90",layout_120_page.isAdAppearance(4,0));
        assertTrue("couldn't find x btn", layout_120_page.isAdCloseBtnExists(4,true));

    }

    // Test Case 2
    @Features("Ads- open triggers 1 and 4 then check 1 is closed")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", timeOut = 60000)
    // (groups = {"ADS", "positive"},
    // IO Exception added after using File export for screenshot
    public void openAdsOnloadTriggers1and4(String url) throws IOException, InterruptedException, EmailException {

        Log.info("time before loaing page");
        long maxPageRunTime = (30 + 10); // 30 for page load + 10 for the test
        //driver.manage().timeouts().pageLoadTimeout(maxPageRunTime, TimeUnit.SECONDS); // will work on the pages with synch loading, but this doesn't solve the problem on pages loading stuff in asynch, the tests will fail all the time if we set the pageLoadTimeOut.
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get(url);
        // wait.until(ExpectedConditions.titleContains("Rushing")); // nice to have- dont start until element X is on page
        Log.info("\n-----  Test Case 2: ** ADS test ** \nURL: " + url + "-----");

        // step 1
        //** make sure the method is TRUE. if not- log a message
        assertTrue("From test: Script is not valid on url " + url, layout_120_page.isScriptValidHere2());
        //    layout_120_page.WaitUntilLayoutIsLoaded();
        layout_120_page.chekLayerisCorrect();
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
        Log.info("From test: Assert is OK, centerWrapper exists");
        // scroll to unit (to see the x btn. in co-optimus.com it fails)
        layout_120_page.scrollUnit();
        //step 2
        //softAssert.assertTrue(layout_120_page.isAdAppearance(1,0),"couldnt find banner 300*250");
        assertTrue("couldn't find banner 300*250", layout_120_page.isAdAppearance(1,0));
        assertTrue("couldn't find banner 728*90",layout_120_page.isAdAppearance(4,0));
        // waiting for ad to close
        assertFalse("could find banner 300*250", layout_120_page.isAdAppearance(1,9));
        Log.info(("YEAH! -AD Trigger 1 was NOT displayed on screen after waiting 9 sec in viewability"));
        assertTrue("couldn't find banner 728*90",layout_120_page.isAdAppearance(4,0)); // Don't need to wait another 5 seconds
    }

    // test case 3
    @Features("Ads- close trigger 1 by clicking false btn")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", timeOut = 60000)
    // (groups = {"ADS", "positive"},
    // IO Exception added after using File export for screenshot
    public void closeAdOnClickTrigger1(String url) throws IOException, InterruptedException, EmailException {

        Log.info("time before loading page");
        long maxPageRunTime = (30 + 10); // 30 for page load + 10 for the test
        //driver.manage().timeouts().pageLoadTimeout(maxPageRunTime, TimeUnit.SECONDS); // will work on the pages with synch loading, but this doesn't solve the problem on pages loading stuff in asynch, the tests will fail all the time if we set the pageLoadTimeOut.
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get(url);
        // wait.until(ExpectedConditions.titleContains("Rushing")); // nice to have- dont start until element X is on page
        Log.info("\n-----  Test Case 3: ** ADS test ** \nURL: " + url + "-----");

        // step 1
        //** make sure the method is TRUE. if not- log a message
        assertTrue("From test: Script is not valid on url " + url, layout_120_page.isScriptValidHere2());
        //    layout_120_page.WaitUntilLayoutIsLoaded();
        layout_120_page.chekLayerisCorrect();
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
        Log.info("From test: Assert is OK, centerWrapper exists");
        // scroll to unit (to see the x btn. in co-optimus.com it fails)
        layout_120_page.scrollUnit();
        //step 2
        //softAssert.assertTrue(layout_120_page.isAdAppearance(1,0),"couldnt find banner 300*250");
        assertTrue("could NOT find banner 300*250", layout_120_page.isAdAppearance(1,0));
        layout_120_page.pressFalseButton(1);
        assertFalse("Banner 300*250 FOUND- not good. it was suppose the be closed after the click", layout_120_page.isAdAppearance(1,1));
        Log.info(("YEAH! -AD Trigger 1 was NOT displayed on screen after clicking false button. it probably got CLOSED by the click"));
    }

}
