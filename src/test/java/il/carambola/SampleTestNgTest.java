package il.carambola;

import il.carambola.pages.HomePage;
import il.carambola.pages.Layout_120_Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class SampleTestNgTest {
  public WebDriver driver;
  public Layout_120_Page layout_120_page;
  private HomePage homepage;

  @BeforeMethod
  public void initPageObjects() {
    driver = new FirefoxDriver();
    homepage = PageFactory.initElements(driver, HomePage.class);
    layout_120_page = PageFactory.initElements(driver, Layout_120_Page.class);

  }

  @Test
  public void PressYesButton() {
    driver.get("http://www.sportingcharts.com/nfl/stats/team-rushing-statistics/2015/");
    layout_120_page
            .WaitUntilLayoutIsLoaded()
            .pressYesButton();

    assertTrue("Yes button do not exist", layout_120_page.CheckThatYesButtonExists());
  }
}
