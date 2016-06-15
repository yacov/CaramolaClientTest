package il.carambola.pages;

import il.carambola.Consts;
import il.carambola.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
//import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {
  private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
  //public static String baseUrl = TestNgTestBase.baseUrl;
  public String PAGE_URL;
  public String PAGE_TITLE;
  public WebDriver driver;

  /*
   * Constructor injecting the WebDriver interface
   *
   * @param webDriver
   */
  public Page(WebDriver driver) {
    this.driver = driver;
  }

  public static boolean CheckCenterWrapper(Integer layoutNumber, WebDriver driver, List<String> errors, String browserName, Integer i) throws InterruptedException {

    Thread.sleep(1500);

    boolean result = false;

    switch (layoutNumber) {
      case 100:
        break;
      case 110:
        break;
      case 120:
        WebElement cbolaCenterWrapper = driver.findElement(By.id(Consts.CENTER_WRAPPER_ID + 0));
        if (cbolaCenterWrapper != null && cbolaCenterWrapper.isDisplayed()) {
          System.out.println("YEAH!- cbola centerWrapper was loaded succefuly inside the HTML");
          result = true;

        } else {
          errors.add("Browser: " + browserName + " URL: " + (i + 1) + " SHAYSE- cbola centerWrapper WASNT DISPLAYED");
          System.out.println("SHAYSE- cbola centerWrapper WASNT DISPLAYED");
          if (cbolaCenterWrapper == null) {
            System.out.println("SHAYSE- cbola centerWrapper WASNT LOADED");
          }
          result = false;

        }
        break;
      case 130:
        break;
      case 140:
        break;
    }
    return result;


  }

  public void goBackBrowserButton() { driver.navigate().back(); }

  public void goForwardBrowserButton() {  driver.navigate().forward(); }

  public void reloadPage() {
    driver.navigate().refresh();
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public String getPageUrl() { return PAGE_URL; }

  public String getPageTitle() {
    return PAGE_TITLE;
  }

  public void loadPage() {
    driver.get(getPageUrl());
//  Assert.assertEquals(getTitle(), getPageTitle());
  }

  public boolean IsScriptValid(WebElement script, List<String> errors, String browserName, Integer i) {

    boolean isScriptValid = true;
    try {
      boolean LayerStatus = script.isEnabled();
      if (LayerStatus) {
        System.out.println("YEAH!- cbola SCRIPT was loaded succefuly inside the HTML");
      } else {
        errors.add("Browser: " + browserName + " URL: " + (i + 1) + " SHAYSE- cbola SCRIPT WASNT LOADED...");
        System.out.println("SHAYSE- cbola SCRIPT WASNT LOADED...");
        isScriptValid = false;
      }
    } catch (Exception e) {
      System.out.println("SHAYSE- unexpected error: " + e.getMessage().substring(0,100));
      isScriptValid = false;
    }
    return isScriptValid;

  }

  public boolean IsScriptValid1(WebElement script) {

    boolean isScriptValid = true;
    try {
      boolean LayerStatus = script.isEnabled();
      if (LayerStatus) {
        Log.info("YEAH!- cbola SCRIPT was loaded succefuly inside the HTML");
      } else {
        Log.info("SHAYSE- cbola SCRIPT WASNT LOADED...");
        //errors.add("Browser: " + browserName + " URL: " + (i + 1) + " ");
        isScriptValid = false;
      }
    } catch (Exception e) {
      Log.info("SHAYSE- unexpected error: " + e.getMessage().substring(0, 100));
      isScriptValid = false;
    }
    return isScriptValid;

  }

  // Step 4.1 - check if cbola board was displayed (the actual game)

  public boolean IsBoardExists(WebElement CbolaBoardStatus) {
    //WebElement CbolaBoardStatus = driver.findElement(By.className(Consts.BOARD_CLASS));
    boolean isBoardValid = true;
    if (CbolaBoardStatus != null && CbolaBoardStatus.isDisplayed()) {
      Log.info("V - cbola board displayed");
      System.out.println("YEAH!- cbola board was displayed");
    } else {
      Log.info("X - cbola board WASNT displayed");
      //errors.add("\nBrowser: " + browserName + " URL: " + (i + 1) + " SHAYSE- cbola borad is NOT displayed");
      //testRunSuccessfull = false;
      isBoardValid = false;
      if (CbolaBoardStatus == null) {
        Log.info("X - cbola board WASNT loaded");
        //testRunSuccessfull = false;  // NEED TO CONSIDER if it fails..

      }
    }
    return isBoardValid;
  }

  public void setElementText(WebElement element, String text) {
    element.click();
    element.clear();
    //Log.info("entering text '" + text + "' into element " + element);
    element.sendKeys(text);
    // Assert.assertEquals(element.getAttribute("value"), text);
  }


  public void clickElement(WebElement element) {
    // Log.info("clicking on element " + element + "");
    element.click();
  }

  public void waitUntilIsLoadedCustomTime(WebElement element, int time) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void waitUntilIsLoaded(WebElement element) {
    try {
      new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    } catch (Exception e) {
      // Log.info("---------------------------------");
      // Log.info("element " + element + " can not be found by ExpectedConditions.visibilityOf(element)");
      //  Log.info("---------------------------------");
      e.printStackTrace();
    }
  }

  // public void selectValueInDropdown(WebElement dropdown, String value) {
  //   Select select = new Select(dropdown);
  //  select.selectByValue(value);
  // }

  // Returns label that we chose
  public String selectValueInDropdown(WebElement dropdown, String value) {
    Select select = new Select(dropdown);
    select.selectByValue(value);
    WebElement option = select.getFirstSelectedOption(); // Chooses label that fits the value
    return option.getText();
  }

  public void selectValueInDropdownbyText(WebElement dropdown, String value) {
    Select select = new Select(dropdown);
    select.selectByVisibleText(value);

  }

  public boolean verifyElementIsPresent(WebElement element) {
    try {
      element.getTagName();
      return true;
    } catch (NoSuchElementException e) {
      //  Log.info("---------------------------------");
      //  Log.info("element " + element + " can not be found by  element.getTagName()");
      //   Log.info("---------------------------------");
      return false;
    }
  }

  public void verifyText(WebElement element, String text) {
    try {
      Assert.assertEquals(text, element.getText());
    } catch (Error e) {

    }
  }

  public boolean verifyTextBoolean(WebElement element, String text) {
    //  Log.info("verifying that text from element " + element + " - ('" + element.getText() + "') - is equal to text '" + text + "'");
    return text.equals(element.getText());
  }

  // Verifies that we chose the label that we wanted.
  public boolean verifyTextBooleanInDropDown(String label, String chosenOption) {
    return chosenOption.equals(label);
  }

  public boolean exists(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (org.openqa.selenium.NoSuchElementException ignored) {
      return false;
    }
  }

  public void checkLayerNumber(Integer LayerNum) {
    String layerTypeAttribute = driver.findElement(By.className(Consts.CENTER_WRAPPER_ID)).getAttribute("layertype");
    Integer layerNumber = Integer.parseInt(layerTypeAttribute);
    Assert.assertEquals(layerNumber, LayerNum, "This is not right Layer Number, so test stops here. Actual Layer nimber is " + layerNumber);
    Log.info("Layer check is OK, layer number here is " + layerNumber);
    System.out.println(layerTypeAttribute);
  }

  public void waitUntilElementIsLoaded(WebElement element) throws IOException, InterruptedException {
    new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
  }

  public void waitUntilElementIsDisappeared(String id) throws IOException, InterruptedException {
    new WebDriverWait(driver, 25).until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
  }

  public void moveMouseOverElement(WebElement element) {
    String javaScript = "var evObj = document.createEvent('MouseEvents');" +
            "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
            "arguments[0].dispatchEvent(evObj);";


    ((JavascriptExecutor) driver).executeScript(javaScript, element);
  }

  public void waitForElement(WebDriverWait wait, String element) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
  }

  protected boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      //  Log.info("----------ALERT-----------------");
      //  Log.info("element " + by + " can not be found by ExpectedConditions.visibilityOf(element)");
      //  Log.info("---------ALERT------------------");
      return false;
    }
  }

  // Pay attention: Works Only for first cell
  public boolean IsCellGreenAfterClick(WebElement locator) {
    clickElement(locator);
    // Is it Green?
    return "#008000".equals(Color.fromString(locator.getCssValue("background-color")).asHex());
  }

  public boolean IsCellColorChangedAfterClick(WebElement cell) {
    String cellColorBeforeClick = Color.fromString(cell.getCssValue("background-color")).asHex();
    clickElement(cell);
    String cellColorAfterClick = Color.fromString(cell.getCssValue("background-color")).asHex();
    return !cellColorBeforeClick.equals(cellColorAfterClick);
  }
}
