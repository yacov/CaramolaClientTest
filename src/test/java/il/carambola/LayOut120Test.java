package il.carambola;

import il.carambola.pages.Layout_120_Page;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertTrue;

public class LayOut120Test extends TestNgTestBase {
  private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
  //public WebDriver driver;
  public Layout_120_Page layout_120_page;
  private SoftAssert softAssert = new SoftAssert();


  @BeforeClass
  public void initbrowser() {
    // driver = new FirefoxDriver();
    driver.manage().window().maximize();
    layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);

  }

  @BeforeMethod
  public void initPageObjects() {


  }

  //This test runs several times, every iteration with new url. Urls are stored in resources/urlList.data file
// (one URL for row)
  @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
  public void PressN0andYesButton(String url) {
    try {
      driver.get(url);

      assertTrue("Script is not valid on url" + url, layout_120_page.isScriptValidHere());
      layout_120_page.WaitUntilLayoutIsLoaded();
      layout_120_page.chekLayerisCorrect();
      layout_120_page.pressYesButton();
      softAssert.assertTrue(layout_120_page.CheckThatYesButtonExists(), "Yes button do not exist");
      Log.info("Assert is OK, button Yes exists");
      layout_120_page.pressNoButton();
      softAssert.assertTrue(layout_120_page.CheckThatYesButtonExists(), "No button do not exist");
      Log.info("Assert is OK, button No exists");
      softAssert.assertTrue(layout_120_page.CheckThatCenterWrapperExists(), "Centerwrapper do not exist");
      Log.info("Assert is OK, centerwrapper exists");
    } catch (Exception e) {
      Log.info(e);
    }
  }

  //@Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
  public void PressNoButton() {

    layout_120_page
            .WaitUntilLayoutIsLoaded()
            .pressNoButton();

    assertTrue("Yes button do not exist", layout_120_page.CheckThatYesButtonExists());
  }
}

  /*@AfterClass(alwaysRun = true)
  public void tearDown() {
    this.driver.quit();
  }
}*/

