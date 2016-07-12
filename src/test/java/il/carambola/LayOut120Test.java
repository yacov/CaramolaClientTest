package il.carambola;

import il.carambola.pages.Layout_120_Page;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertTrue;

public class LayOut120Test {
  private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public WebDriver driver;
    public Layout_120_Page layout_120_page;
  private SoftAssert softAssert = new SoftAssert();

    @Parameters({"browser_name"})
    //  @BeforeClass(alwaysRun = true)
    public void initbrowser(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {

            //create firefox instance

            driver = new FirefoxDriver();

        }

        //Check if parameter passed as 'chrome'

        else if (browser.equalsIgnoreCase("chrome")) {

            //set path to chromedriver.exe You may need to download it from http://code.google.com/p/selenium/wiki/ChromeDriver


            //create chrome instance

        driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("ie")) {

            //set path to IEdriver.exe You may need to download it from

            // 32 bits http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_Win32_2.42.0.zip

            // 64 bits http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_x64_2.42.0.zip

            //  System.setProperty("webdriver.ie.driver","C:\\IEdriver.exe");

            //create chrome instance

            driver = new InternetExplorerDriver();

        } else {

            //If no browser passed throw exception

            throw new Exception("Browser is not correct");

        }
        // driver = new ChromeDriver();



  }

    @BeforeMethod(alwaysRun = true)
  public void initPageObjects() {
        layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);
  }



  //This test runs several times, every iteration with new url. Urls are stored in resources/urlList.data file
// Test Case 1
  //@Parameters("browser")
  @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
  public void BasicFullLoad(String url) {

    try {
        driver.manage().window().maximize();
      driver.get(url);
      Log.info("-----   Test Case 1 with URL: "+ url + "-----" );
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

      try{
          driver.get(url);
          Log.info("-----   Test Case 2 with URL: "+ url + "-----" );
          assertTrue("Script is not valid on url"+ url,layout_120_page.isScriptValidHere());
          layout_120_page.WaitUntilLayoutIsLoaded().chekLayerisCorrect();

          // TODO: 08/06/2016  create loop
          layout_120_page.pressYesButton();
          
          /*layout_120_page.pressYesButton();
          softAssert.assertTrue(layout_120_page.CheckThatYesButtonExists(), "Yes button do not exist");
          Log.info("Assert is OK, button Yes exists");
          layout_120_page.pressNoButton();
          softAssert.assertTrue(layout_120_page.CheckThatYesButtonExists(), "No button do not exist");
          Log.info("Assert is OK, button No exists");*/

                  

      }catch(Exception e){
          Log.info(e);
      }

    //assertTrue("Yes button do not exist", layout_120_page.CheckThatYesButtonExists());
  }


    /*@AfterClass(alwaysRun = true)
  public void tearDown() {
    this.driver.quit();
  }*/
}

