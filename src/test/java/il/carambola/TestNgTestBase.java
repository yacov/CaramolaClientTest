package il.carambola;

import il.carambola.pages.Layout_120_Page;
import il.carambola.util.PropertyLoader;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;
import ru.yandex.qatools.allure.annotations.Parameter;


import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.util.Date;
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
  public String startTime = GeneralUtils.sdf.format(new Date());
  public Long startTimeMls = System.currentTimeMillis();
  public Integer counterSuccess = 0;
  public Integer counterFails = 0;



  @Parameters({"browser_name"})
  @BeforeClass(alwaysRun = true)
  public void setuptestNg(@Optional("Firefox") @Parameter("Browser") String browser) throws Exception {

    //baseUrl = PropertyLoader.loadProperty("site.url");  ?? DO WE NEED IT ??

    layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);


    String pwd = PropertyLoader.loadAuthProperty("auth.pass");
    if (pwd ==null)
    {
      //take the urls from file
    }
    else
    {
      //take the urls from DB
      Connection conn = null;
      conn = GeneralUtils.connectToDatabaseOrDie();
      GeneralUtils.queryAndPrint(conn,"prod_10_urls_120");
      conn.close();
    }


    WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
    //Check if parameter passed from TestNG is 'firefox'
    try {
      if (browser.equalsIgnoreCase("Firefox")) {

        //create firefox instance
        //WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "src\\drvv\\wires.exe");
        driver = new FirefoxDriver();
        //driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE"); doesnt work...

        Log.info("\n\n*** Starting FireFox Browser ***\n");

      }
      //Check if parameter passed as 'chrome'
      else if (browser.equalsIgnoreCase("Chrome")) {

        String exePathChromeDriver = "src\\drvv\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePathChromeDriver);

        driver = new ChromeDriver();
        Log.info("\n\n*** Starting Chrome Browser ***\n");

      }else if(browser.equalsIgnoreCase("Headless")){
        //unitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
        //unitDriver.setJavascriptEnabled(true);
        System.setProperty("phantomjs.binary.path", "src\\drvv\\phantomjs.exe");
        driver = new PhantomJSDriver();
        Log.info("\n\n*** Starting headless PhantomJS Browser ***\n");

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
       //layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);
     }else{
       //layout_120_page = PageFactory.initElements(unitDriver, Layout_120_Page.class);
     }

    } catch (Exception e) {
      Log.info(e);
    }
  }

  @AfterMethod
  public void afterMethod(ITestResult result)
  {
    try
    {
      if(result.getStatus() == ITestResult.SUCCESS)
      {
        counterSuccess++;
        //Do something here
        System.out.println("passed **********\n");
      }

      else if(result.getStatus() == ITestResult.FAILURE)
      {
        counterFails++;
        //Do something here
        System.out.println("Failed ***********\n");

      }

      else if(result.getStatus() == ITestResult.SKIP ){

        System.out.println("Skiped***********\n");

      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }
  // tearDown after test
  @AfterTest(alwaysRun = true)
  public void tearDown() throws EmailException, IOException {
    String endTime = GeneralUtils.sdf.format(new Date());
    this.driver.quit();

    String hostname = "Unknown";
    InetAddress addr;
    addr = InetAddress.getLocalHost();
    hostname = addr.getHostName();

    double testTimeMin = ((System.currentTimeMillis()) - startTimeMls) / 1000.d / 60;
    GeneralUtils.emailer("Holly Shmoly! a mother FU@#$ing Automated TEST was just FINISHED !!" +
            "\nClass name:  "+ this.getClass().getName() +"" +
            "\nStarted at:  "+ startTime +"" +
            "\nFinished at: "+ endTime +"" +
            "\nOn Hostname: "+ hostname +"" +
            "\nTotal time:  " + testTimeMin +"" +
            "\nTotal success: "+ counterSuccess + "" +
            "\nTotal Failures: " + counterFails);
  }
}
