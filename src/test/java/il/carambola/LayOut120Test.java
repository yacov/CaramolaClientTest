package il.carambola;

import il.carambola.pages.HomePage;
import il.carambola.pages.Layout_120_Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class LayOut120Test {
  public WebDriver driver;
  public Layout_120_Page layout_120_page;
  private HomePage homepage;

  @BeforeClass
  public void initbrowser() {
    driver = new FirefoxDriver();
    homepage = PageFactory.initElements(driver, HomePage.class);
    layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);

  }

  @BeforeMethod
  public void initPageObjects() {


  }

  //This test runs several times, every iteration with new url. Urls are stored in resources/urlList.data file
// (one URL for row)
  @Test(dataProviderClass = DataProviders.class, dataProvider = "Urls")
  public void PressYesButton(String url) {
    driver.get(url);
    layout_120_page
            .WaitUntilLayoutIsLoaded()
            .pressYesButton();

    assertTrue("Yes button do not exist", layout_120_page.CheckThatYesButtonExists());
  }

  @Test
  public void PressNoButton() {

    layout_120_page
            .WaitUntilLayoutIsLoaded()
            .pressNoButton();

    assertTrue("Yes button do not exist", layout_120_page.CheckThatYesButtonExists());
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    this.driver.quit();
  }
}

