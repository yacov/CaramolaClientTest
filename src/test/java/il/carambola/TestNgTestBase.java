package il.carambola;

import il.carambola.pages.Layout_120_Page;
import il.carambola.util.PropertyLoader;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;
import ru.yandex.qatools.allure.annotations.Parameter;

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
  public void setuptestNg(@Optional("Firefox") @Parameter("Browser") String browser) throws Exception {
    baseUrl = PropertyLoader.loadProperty("site.url");

    WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
    //Check if parameter passed from TestNG is 'firefox'
    try {
      if (browser.equalsIgnoreCase("Firefox")) {

        //create firefox instance

        driver = new FirefoxDriver();


        Log.info("\n\n*** Starting FireFox Browser ***\n");

      }

      //Check if parameter passed as 'chrome'

      else if (browser.equalsIgnoreCase("Chrome")) {

        String exePathChromeDriver = "C:\\Users\\Yair\\Documents\\yair\\QA\\TestAutomation\\Selenium\\chrome_driver2_0\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePathChromeDriver);

        driver = new ChromeDriver();
        Log.info("\n\n*** Starting Chrome Browser ***\n");

      } else if (browser.equalsIgnoreCase("ie")) {

        //String exeServiceIEdriver = "C:\\Users\\Yair\\Documents\\yair\\QA\\TestAutomation\\Selenium\\IEdriver2_48\\IEDriverServer.exe";
        //System.setProperty("webdriver.ie.driver", exeServiceIEdriver);
        //InternetExplorerDriver driverIE = new InternetExplorerDriver();
        driver = new InternetExplorerDriver();
        Log.info("\n*** Starting IE Browser ***");
      } else {

        //If no browser passed throw exception

        throw new Exception("Browser is not correct");

      }

      // driver = WebDriverFactory.getDriver(capabilities);
      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);
    } catch (Exception e) {
      Log.info(e);
    }
  }


  @AfterTest(alwaysRun = true)
  public void tearDown() throws EmailException {
    this.driver.quit();
    GeneralUtils.emailer("TEST FINISHED you moth@# fuc!@#%");
  }
}
