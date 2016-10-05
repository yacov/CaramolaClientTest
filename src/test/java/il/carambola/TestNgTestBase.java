package il.carambola;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import il.carambola.pages.Layout_120_Page;
import il.carambola.util.PropertyLoader;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;
import ru.yandex.qatools.allure.annotations.Parameter;

import java.net.InetAddress;
import java.rmi.UnknownHostException;
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
  public HtmlUnitDriver unitDriver;

  @Parameters({"browser_name"})
  @BeforeTest(alwaysRun = true)
  public void setuptestNg(@Optional("Chrome") @Parameter("Browser") String browser) throws Exception {
    baseUrl = PropertyLoader.loadProperty("site.url");

    WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
    //Check if parameter passed from TestNG is 'firefox'
    try {
      if (browser.equalsIgnoreCase("Firefox")) {

        //create firefox instance
        driver = new FirefoxDriver();
        //driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE"); doesnt work...

        Log.info("\n\n*** Starting FireFox Browser ***\n");

      }
      //Check if parameter passed as 'chrome'
      else if (browser.equalsIgnoreCase("Chrome")) {

        String exePathChromeDriver = "C:\\Users\\Yair\\Documents\\yair\\QA\\TestAutomation\\Selenium\\chrome_driver2_0\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePathChromeDriver);

        driver = new ChromeDriver();
        Log.info("\n\n*** Starting Chrome Browser ***\n");

      }else if(browser.equalsIgnoreCase("Headless")){
        unitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
        unitDriver.setJavascriptEnabled(true);

      }
      else if (browser.equalsIgnoreCase("ie")) {

        //String exeServiceIEdriver = "C:\\Users\\Yair\\Documents\\yair\\QA\\TestAutomation\\Selenium\\IEdriver2_48\\IEDriverServer.exe";
        //System.setProperty("webdriver.ie.driver", exeServiceIEdriver);
        //InternetExplorerDriver driverIE = new InternetExplorerDriver();
        driver = new InternetExplorerDriver();
        Log.info("\n*** Starting IE Browser ***");

      } else {
        //If no browser passed throw exception
        throw new Exception("Browser is not correct");

      }

     if(!browser.equalsIgnoreCase("Headless")) {
       // driver = WebDriverFactory.getDriver(capabilities);
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       System.out.println("from: TestNgTestBase - implicit wait 5 sec finished");
       layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);
     }else{
       layout_120_page = PageFactory.initElements(unitDriver, Layout_120_Page.class);
     }

    } catch (Exception e) {
      Log.info(e);
    }
  }

  @AfterTest(alwaysRun = true)
  public void tearDown() throws EmailException, java.net.UnknownHostException {
    this.driver.quit();
    String hostname = "Unknown";

    InetAddress addr;
    addr = InetAddress.getLocalHost();
    hostname = addr.getHostName();
    GeneralUtils.emailer(" Holly Shmoly! a HUGE mother FU@#$ing Automated TEST was just FINISHED !! on hostname: " + hostname);
  }
}
