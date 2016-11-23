package il.carambola;

import il.carambola.pages.Layout_120_Page;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class LayOut120BrowserDataTest extends TestNgTestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //  public WebDriver driver;

    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void initPageObjects() {
        layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);
    }

    //This test runs several times, every iteration with new url. Urls are stored in resources/urlList.data file
// Test Case 1
    @Features("Check if Layer's UI was fully loaded")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", timeOut = 6000000)
    // IO Exception added after using File export for screenshot
    public void BasicFullLoad(String url) throws IOException {

        LogLog4j.startTestCase("START TEST CASE");
        Log.info("tc1 time before loaing page");
        long maxPageRunTime = (30 + 10); // 30 for page load + 10 for the test
        //driver.manage().timeouts().pageLoadTimeout(maxPageRunTime, TimeUnit.SECONDS); // will work on the pages with synch loading, but this doesn't solve the problem on pages loading stuff in asynch, the tests will fail all the time if we set the pageLoadTimeOut.
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get(url);
        // wait.until(ExpectedConditions.titleContains("Rushing")); // nice to have- dont start until element X is on page
        Log.info("\n-----  Test Case 1: ** UI test ** \nURL: " + url + "-----");
        driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
        // step 1
        //** make sure the method is TRUE. if not- log a message
        assertTrue("From test: Script is not valid on url " + url, layout_120_page.isScriptValidHere2());
        //    layout_120_page.WaitUntilLayoutIsLoaded();
        //step 2
        layout_120_page.chekLayerisCorrect();
        //step 3
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "CenterWrapper do not exist");
        Log.info("From test: Assert is OK, centerWrapper exists");
        //layout_120_page.sizeOfWrapper();
        //step 4
        //softAssert.assertTrue(layout_120_page.IsBoardExist(), "From test: Cbola board DOESNT exists");
        //step 4.2
        layout_120_page.isImageExists(0);
        // Log.info(layout_120_page.printImage(0)); printImage return String. its already in isFirstImageExists
        layout_120_page.isTextExists(0); // its a boolean in case we will want to make Assert
        layout_120_page.isScoreTitleExists();
        layout_120_page.isShareBtnExists("FB"); // excepts "FB" or "Twitter"
        layout_120_page.isShareBtnExists("Twitter");
        layout_120_page.isScoreUnitExists();
        layout_120_page.isUnitTitleExists();
        //layout_120_page.checkDuplicateItems();  // why was it here? it doesnt seem to work here cause there are no clicks
        layout_120_page.isPoweredByExists();
        layout_120_page.itemsTemp.clear();

        LogLog4j.endTestCase("test case ended");

    }


    @Features("Click buttons and check items")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", timeOut = 60000)
// Test Case 2
    public void HalfGame(String url) throws InterruptedException, IOException {
        System.out.println("from test: getting url.." + GeneralUtils.date);
        //driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);

        driver.get(url);
        Log.info("\n-----  Test Case 2: ** FUNCTIONALITY test ** \nURL: " + url + "-----");
        //driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
        // basic tests- is script, is layout, is wrapper
        assertTrue("Script is not valid on url" + url, layout_120_page.isScriptValidHere2());
        layout_120_page.WaitUntilLayoutIsLoaded().chekLayerisCorrect();
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
        // scroll to unit
        //jse.executeScript("window.scrollBy(0,-100)"); // (-X) will scroll up
        layout_120_page.scrollUnit();

        //layout_120_page.pressTrueButton(1);
        //layout_120_page.pressFalseButton(2);
        /**
         * one method that combines true and false btns. define how many clicks to perform
         */
        // start test:
        layout_120_page.pressButtonAndCheckContent(false, 2);
        layout_120_page.pressButtonAndCheckContent(true, 3);

        // need to improve.. need to add the number of items I want to print (depends on how many clicks I made above)
        layout_120_page.printItemsList(4); // 5 clicks ==> 6 items

        assertFalse("There are duplicate TEXT items",layout_120_page.checkDuplicateItems());

        //** need to clear items List at the end of the @Test otherwise it will save it for the next url and it will have a long list
        layout_120_page.itemsTemp.clear();
        System.out.println("## END OF TEST ##");
        LogLog4j.endTestCase("test case ended");
        }

    /*@AfterClass(alwaysRun = true)
    public void tearDown() {
        this.driver.quit();
    }*/
    @Severity(SeverityLevel.MINOR)
    @Features("Check ending screen and next game's 1st item")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", timeOut = 80000)
    public void endingScreen(String url) throws InterruptedException, IOException {
        driver.get(url);
        Log.info("\n-----  Test Case 3: ** CONTENT test ** \nURL: " + url + "-----");
        driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
        // basic tests- is script, is layout, is wrapper
        assertTrue("Script is not valid on url" + url, layout_120_page.isScriptValidHere2());
        layout_120_page.WaitUntilLayoutIsLoaded().chekLayerisCorrect();
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
        //scroll to unit
       // JavascriptExecutor jse = (JavascriptExecutor) driver;
       // jse.executeScript("document.getElementById('InContent-container-centerWrapper0').scrollIntoView(true);");
       // jse.executeScript("window.scrollBy(0,-150)");
        layout_120_page.scrollUnit();
        System.out.println("JS scroll executed");
        // start test:
        // Step 2: click until game finishes
        Integer noClicks = 10;
        layout_120_page.pressFalseButton(noClicks);
        //layout_120_page.pressTrueButton(7);
        Thread.sleep(3000);
        // Step 3: Ending screen Message Structure- "next quiz"
        assertTrue("cant find ending screen msg title",layout_120_page.isEndingScreenMsgTitleExists()); // the assert will fail the test if needed
        //Step 3.1: name of next game
        assertTrue("cant find ending screen msg name",layout_120_page.findEndingScreenMsgName());
        //Step 3.2: btn "next"
        assertTrue("cant find ending screen \'Start\' Button",layout_120_page.isStartNextGameBtnExists());
        //Step 4: score unit structure - "you scored a__ " if it passes go to next method and check if the title is correct
        assertTrue("cant find ending screen score unit title",layout_120_page.isScoreUnitTitle());
        //Step 4.2: challange
        assertTrue("cant find ending screen share title",layout_120_page.isEndingScreenShareTitleExists());
        //Step 4.3: your friends
        assertTrue("cant find ending screen share title 2",layout_120_page.isEndingScreenShareTitle2Exists());
        //Step 5: share btns
        layout_120_page.isShareBtnEndingScreenExists("FB");
        layout_120_page.isShareBtnEndingScreenExists("Twitter");
        //Step 6: image 10 appears (next game's image)
        layout_120_page.isImageExists(11);
        //Step 7: is item 10 displayed? mustNOT because Ending screen covers it
        assertFalse("X- text of item 10 WAS displayed ending screen covers it",layout_120_page.isTextExists(noClicks));
        //Step 8: click START (new game)
        if(layout_120_page.isAdAppearance(1,0)) {
            Log.info("Can't click. trigger 1 blocks the way. Need to wait for it to close...waiting 8 sec...");
            Thread.sleep(8000);
            layout_120_page.pressStartBtn();
            Thread.sleep(1000);
        }else{
            layout_120_page.pressStartBtn();
            Thread.sleep(1000);
        }
        //Step 9: is item 10 displayed?
        assertTrue("X- text of item 10 WASNT displayed ending screen covers it",layout_120_page.isTextExists(noClicks));
        System.out.println("## END OF TEST ##");
        LogLog4j.endTestCase("test case ended");
    }
    @Features("Click on Powered by Icon and open new window")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", timeOut = 60000)
    public void clickPoweredByIcon(String url) throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(url);
        Log.info("\n-----  Test Case 4: ** FUNCTIONALITY test ** \nURL: " + url + "-----");
        driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
        // basic tests- is script, is layout, is wrapper
        assertTrue("Script is not valid on url" + url, layout_120_page.isScriptValidHere2());
        layout_120_page.WaitUntilLayoutIsLoaded().chekLayerisCorrect();
        softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");

        String mwh = driver.getWindowHandle();
        System.out.println("Main window handler: " + mwh);
        String mainTtile = driver.getTitle();
        System.out.println("Main window TITLE: " + mainTtile);

        // need to scroll to avoid bug that cant find the button
        layout_120_page.scrollUnit();

        layout_120_page.pressTrueButton(1); // close the 300*250 Ad that blocks the "powered by" icon
        Thread.sleep(2000); // wait for ad to close
        // click icon
        WebElement poweredBy = driver.findElement(By.xpath(Consts.POWERED_BY_XPATH));
        poweredBy.click();
        System.out.println("waiting 4 sec for new window to open...");
        Thread.sleep(4000);
        //** send the Main Window's Handler and title
        layout_120_page.closePopupWindow(mwh, mainTtile);

    }
    @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls", enabled = false)

    public void testSeleniumCommands(String url) throws InterruptedException {

        driver.manage().window().maximize();
        driver.get(url);

        // need to scroll to avoid bug that cant find the button
        layout_120_page.scrollUnit();

        layout_120_page.pressTrueButton(1);
        Thread.sleep(2000);



    }
}
