package il.carambola;

import il.carambola.pages.Layout_120_Page;
import il.carambola.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;

import java.util.concurrent.TimeUnit;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {
  protected static String gridHubUrl;
  protected static String baseUrl;
  protected static Capabilities capabilities;
  private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
  public Layout_120_Page layout_120_page;

  public WebDriver driver;

  @Parameters({"browser_name"})
  @BeforeTest(alwaysRun = true)
  public void setuptestNg(@Optional("Firefox") String browser) throws Exception {
    baseUrl = PropertyLoader.loadProperty("site.url");

    WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
    //Check if parameter passed from TestNG is 'firefox'
    try {
      if (browser.equalsIgnoreCase("Firefox")) {

        //create firefox instance

        driver = new FirefoxDriver();

      }

      //Check if parameter passed as 'chrome'

      else if (browser.equalsIgnoreCase("Chrome")) {

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

      // driver = WebDriverFactory.getDriver(capabilities);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);
    } catch (Exception e) {
      Log.info(e);
    }
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverFactory.dismissAll();
  }
}
