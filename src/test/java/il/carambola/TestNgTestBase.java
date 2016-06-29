package il.carambola;

import il.carambola.util.PropertyLoader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
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


  public WebDriver driver;


  // @BeforeTest(alwaysRun = true)
  public void setup(String browser) throws Exception {
    baseUrl = PropertyLoader.loadProperty("site.url");

    WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
    //Check if parameter passed from TestNG is 'firefox'


    //driver = WebDriverFactory.getDriver(capabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverFactory.dismissAll();
  }
}
