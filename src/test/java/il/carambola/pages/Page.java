package il.carambola.pages;

import il.carambola.Consts;
import il.carambola.GeneralUtils;
import il.carambola.LogLog4j;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.File;
import java.io.IOException;
import java.util.*;

//import java.time.LocalDateTime; ---------- why cant use it?
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
  public List<String> itemsTemp = new ArrayList<String>();
  public List<String> itemsSave = new ArrayList<String>();

  @FindBy(id = Consts.BUNNER_STRUCTURE_ID) WebElement bannerStructure;
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
        WebElement cbolaCenterWrapper = driver.findElement(By.xpath(Consts.CBOLA_LAYER_XPATH));
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
  @Step("Check if script exists. severity: Trivial")
  @Severity(SeverityLevel.TRIVIAL)
  public boolean IsScriptValid1(WebElement script) {

    boolean isScriptValid = true;
    try {
      boolean LayerStatus = script.isEnabled();
      if (LayerStatus) {
        Log.info("YEAH!- cbola SCRIPT was loaded successfuly inside the HTML");
      } else {
        Log.error("SHAYSE- cbola SCRIPT WASNT LOADED...");
        //errors.add("Browser: " + browserName + " URL: " + (i + 1) + " ");
        isScriptValid = false;
      }
    } catch (Exception e) {
      Log.error("SHAYSE- unexpected error: " + e.getMessage().substring(0, 100));
      isScriptValid = false;
    }
    return isScriptValid;

  }

  public void checkLayerNumber(Integer LayerNum) {
    String layerTypeAttribute = driver.findElement(By.xpath(Consts.CBOLA_LAYER_XPATH)).getAttribute("layertype");
    Integer layerNumber = Integer.parseInt(layerTypeAttribute);
    Assert.assertEquals(layerNumber, LayerNum, "This is not right Layer Number, so test stops here. Actual Layer number is " + layerNumber);
    Log.info("Layer number is OK: " + layerNumber);
    //System.out.println(layerTypeAttribute);
  }

  @Severity(SeverityLevel.CRITICAL)
  @Step("Find Board")
  // Step 4.1 - check if cbola board was displayed (the actual game)
  public boolean IsBoardExists(WebElement CbolaBoardStatus) {
    //WebElement CbolaBoardStatus = driver.findElement(By.className(Consts.BOARD_CLASS));
    boolean isBoardValid = true;
    if (CbolaBoardStatus != null && CbolaBoardStatus.isDisplayed()) {
      Log.info("V - cbola board displayed");
      System.out.println("YEAH!- cbola board was displayed");
    } else {
      Log.error("X - cbola board WASNT displayed");
      //errors.add("\nBrowser: " + browserName + " URL: " + (i + 1) + " SHAYSE- cbola borad is NOT displayed");
      //testRunSuccessfull = false;
      isBoardValid = false;
      if (CbolaBoardStatus == null) {
        Log.error("X - cbola board WASNT loaded");
        //testRunSuccessfull = false;  // NEED TO CONSIDER if it fails..

      }
    }
    return isBoardValid;
  }
  public void sizeOfWrapper(){
    // get size of wrapper
    WebElement wrapper = driver.findElement(By.xpath(Consts.CBOLA_LAYER_XPATH));
    Dimension dimWrapper = wrapper.getSize();
    System.out.println("height is: "+ dimWrapper.getHeight() + ". width is: " + dimWrapper.getWidth());


    // convert element to string
    //String dimWrapperString = dimWrapper.toString();
    //System.out.println("this is the dim element converted to string: " + dimWrapperString);
  }
  @Step("Find image")
  public boolean isImageExists(Integer imageNo , WebElement img){
    boolean CbolaFirstImg = img.isDisplayed();
    if(CbolaFirstImg) {
      Log.info("V - Image "+ imageNo +" was displayed");
      System.out.println("YEAH!- we can see image element no "+ imageNo +":"); // the element will get printed in method "printImage"

    } else {
      Log.error("X - Image "+ imageNo +" WASNT displayed");
      System.out.println("SHAYSE- img element no "+ imageNo +" WASNT displayed");
      CbolaFirstImg = false;
    }
    return CbolaFirstImg;
  }
  // check if the text of the item exists, AND print it
  public boolean findText(WebElement Item, Integer itemNo) throws IOException {

    boolean isText = Item.isDisplayed();
    if (isText) {
      String TextString = Item.getText();
      Log.info("V - Item " + itemNo + " was displayed: " + TextString);
      itemsTemp.add(TextString);

      // ~~~ creating file MAY make the test slower ~~~
     // File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     // FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "screenshot" + GeneralUtils.sdf.format(new Date()) + ".png")); //+ browserName +"_url_"+ i +"_1st text WASNT displayed.png"));
    } else {
      Log.info("X - Item " + itemNo + " WASN'T displayed");
      scrollUnit();
     // File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     // FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "screenshot" + GeneralUtils.sdf.format(new Date()) + ".png")); //+ browserName +"_url_"+ i +"_1st text WASNT displayed.png"));
    }
    itemsSave = itemsTemp;

    return isText;
  }

  // check if the text of the Score Title exists, AND print it
  public boolean findScoreTitle(WebElement scoreTilte) throws IOException {

    boolean isScoreTitle = scoreTilte.isDisplayed();
    if (isScoreTitle) {
      Log.info("V - Score Title Class was displayed");
      String TextString = scoreTilte.getText();
      if(new String(Consts.TEXT_QUESTION).equals(TextString)){
        Log.info("V - Text of Question is Correct: "+ TextString);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "text_question_title_is_correct_" + GeneralUtils.sdf.format(new Date()) + ".png")); //+ browserName +"_url_"+ i +"_1st text WASNT displayed.png"));
        System.out.println("Screenshot taken");
      }else{
        Log.error("X- Text of Question is NOT correct: "+ TextString);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "text_question_title_is_NOT_correct_" + GeneralUtils.sdf.format(new Date()) + ".png")); //+ browserName +"_url_"+ i +"_1st text WASNT displayed.png"));
        System.out.println("Screenshot taken");
      }
    } else {
      Log.error("X - score title class WASN'T displayed");
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "text_question_title_WASNT_displayed_" + GeneralUtils.sdf.format(new Date()) + ".png")); //+ browserName +"_url_"+ i +"_1st text WASNT displayed.png"));
      System.out.println("Screenshot taken");
    }
    return isScoreTitle;
  }

  public void isScoreUnit(WebElement ScoreUnitElement) throws IOException {
    //String ScoreUnit = ScoreUnitElement.findElement(By.tagName("span")).getText();
    String ScoreUnit = ScoreUnitElement.getText();
    System.out.println("ref text is: " + Consts.TEXT_YOUR_SCORE);
    System.out.println("text on screen is: " + ScoreUnit);
    // score unit- "your score"
    if (new String(Consts.TEXT_YOUR_SCORE).equals(ScoreUnit)) {
      Log.info("V - Text check- score unit match");
    } else {
      Log.error("X - Text check- score unit DOESNT match");
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "NO_match_between_the_unit_score_texts" + GeneralUtils.sdf.format(new Date()) + ".png"));
    }
  }

  // Find share btns
  public void findShareBtn(String firm, WebElement shareBtn)throws IOException{
    //select test fb or twitter
    if(firm.equals("FB")){
      boolean ShareFBb = shareBtn.isDisplayed();
      if (ShareFBb) {
        Log.info("V - FB share btn was displayed");
      } else {
        Log.error("X - FB share btn WASNT displayed");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "FaceBook_btn_WANST_displayed" + GeneralUtils.sdf.format(new Date()) + ".png"));
      }
    }else if (firm.equals("Twitter")){
      boolean shareTwitterBtn = shareBtn.isDisplayed();
      if (shareTwitterBtn) {
        Log.info("V - Twitter share btn was displayed");
      } else {
        Log.error("X - Twitter share btn WASNT displayed");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "Twitter_btn_WANST_displayed" + GeneralUtils.sdf.format(new Date()) + ".png"));
      }
    }else{Log.info("!!! err !!! share btn name is not correct. please adjust argument");}
  }

  public void findUnitTitle(WebElement unitTitle) throws IOException {
    // Step 5.4- verify items Title
    boolean isTitle = unitTitle.isDisplayed();
    if (isTitle) {
      String title = unitTitle.getText();
      Log.info("V - Text check- Title was displayed: " + title);
    } else {
      Log.error("X - Text check- Title WASNT displayed");
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "Title_WASNT_displayed" + GeneralUtils.sdf.format(new Date()) + ".png"));
    }

  }
  @Severity(SeverityLevel.TRIVIAL)
   public void printItemsList(Integer max) {
     //print items List
     System.out.println("\n  List of items:");
     for (Integer j = 0; j < max; j++) {
       System.out.println(itemsSave.get(j));
     }
   }
  @Severity(SeverityLevel.MINOR)
  @Step("check for duplicate items")
  public boolean checkDuplicateItems() throws IOException {
    Integer countSimilar = 0;
    boolean isDup = false;
    for(Integer j = 0; j < itemsTemp.size(); j++){
      for(Integer k = j+1; k < itemsTemp.size(); k++){
        if(itemsTemp.get(k) != null && itemsTemp.get(j).equals(itemsTemp.get(k))){
          System.out.println("xx SHAYSE xx item no." + j + " and item no." + k + " are similar");
          countSimilar++;
          File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "screenshot_item no."+j+" and item no."+k+" are similar.png"));
        }else{
          System.out.println("-- YEAHH -- item no."+j+" and item no."+k+" are NOT similar");
          //System.out.println(itemsTemp.get(j) + itemsTemp.get(k));  // test
        }
      }
    }
    if (countSimilar > 0){isDup = true;}
    return isDup;
  }

  public boolean findEndingScreenMsg(WebElement element){
    boolean isMsgTitle = element.isDisplayed();
    if(isMsgTitle){
      String scoreUnit = element.getText();
      Log.info("V - Ending Screen Msg title was displayed: " + scoreUnit);
    }else{
      Log.error("X - Ending Screen Msg title WASNT displayed");
      isMsgTitle = false;
    }
    return isMsgTitle;
  }

  @Step("Find Ad appearance")
  public boolean isAdAppearance(Integer trigger, Integer waitTime_ms) throws InterruptedException {
      boolean isAppearance = false;
      try {
          WebElement bannerStructure = driver.findElement(By.id(Consts.BUNNER_STRUCTURE_ID + trigger));
          Thread.sleep(waitTime_ms * 1000);
          if (bannerStructure != null && bannerStructure.isDisplayed()) {
              Log.info("AD Trigger "+ trigger +" was loaded and displayed on screen after waiting: " + waitTime_ms + " seconds");
              //return true;
              isAppearance = true;
          } else {
           Log.info("AD Trigger " + trigger + " is in the page, BUT WASN'T displayed on sec " + GeneralUtils.sdf.format(new Date()));
            isAppearance = false;
          }
       } catch (Exception e) {
        //bannerStructure = null;
        System.out.println("SHAYSE - " + Consts.BUNNER_STRUCTURE_ID + trigger + " wasn't found! either: \n" +
                "(1) Ad closed on Timer - the page loaded slowly and the test started too late\n" +
                "(2) Ad closed on Viewability - the ad was shown and deleted cause the unit was above the fold\n" +
                "(3) No such appearance in GetAds. \n " +
                "Error: " + e.getMessage().substring(0,80));
        Log.info("Banner structure: " + Consts.BUNNER_STRUCTURE_ID + trigger + " was NOT found in page");
      }
      return isAppearance;
    }
   public boolean isAdCloseBtnExists(Integer trigger, Boolean click){
     Boolean isXBtn = false;
     WebElement xBtn = driver.findElement(By.xpath(Consts.AD_CLOSE_BTN_XPATH));
     Integer triggerInPage = Integer.valueOf(xBtn.getAttribute("apperance"));
     if(trigger.equals(triggerInPage)){
       Log.info("x btn exists for trigger " + trigger);
       if(click){
         xBtn.click();
         Log.info("x btn clicked for trigger " + trigger);
         // check if banner got closed
       }
       isXBtn = true;
     }else{Log.info("SHAYSE - input trigger is not correct. input is " + trigger + " but trigger in page is " + triggerInPage);}

     return isXBtn;
   }
  public boolean isPoweredByExists(){
    //WebElement poweredBy = driver.findElement(By.className(Consts.POWERED_BY_CLASS + 0));
    WebElement poweredBy = driver.findElement(By.xpath(Consts.POWERED_BY_XPATH));
    boolean isPoweredBy = poweredBy.isDisplayed();
    if(isPoweredBy){
      Log.info("V - Powered By Carambola btn was displayed");
    }else{
      Log.info("X - Powered By Carambola btn WASNT displayed");
    }
    return isPoweredBy;
  }
  public void closePopupWindow(String mwh, String mTitle) {
    String newWindow = null;
    Set<String> handlers = driver.getWindowHandles();
    //this method will gives you the handles of all opened windows
    // the newer form of a FOR LOOP
    for (String window : handlers) {
      if (!window.equals(mwh)) {
        newWindow = window;
      }
    }
    // the focus is on the main page. need to switchTo new page and close it
    driver.switchTo().window(newWindow);
    String newTitle = driver.getTitle();
    System.out.println(newTitle);
    if (!newTitle.equals(mTitle)) {
      System.out.println("The focus now is on the NEW window");
      if (newTitle.equals("Carambola")) {
        Log.info("V - The window that was opened by click is CORRECT. its title is: " + newTitle);
      } else {
        Log.info("X - The window that was opened by click is NOT CORRECT. its title is: " + newTitle);
      }
      driver.close();
      driver.switchTo().window(mwh);
    }
  }
  public void scrollUnit(){
    Integer screenHeight = driver.manage().window().getSize().getHeight();
    System.out.println(screenHeight);

    Integer scroll = (screenHeight - 150) / 2 ; // was 200 and was ok, but a bit low in the screen
    System.out.println(scroll);
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("document.getElementsByClassName('cbola-d-trivia')[0].scrollIntoView(true);");
    jse.executeScript("window.scrollBy(0,-" + scroll + ")");
  }

    //** didnt worked for me...:
    //Iterator ite = s.iterator();
   /* while(ite.hasNext())
    {
      String popupHandle = ite.next().toString();
      if(!popupHandle.contains(mwh))
      {
        //driver.switchTo().window(popupHandle);
        //here you can perform operation in pop-up window**
        //After finished your operation in pop-up just select the main window again
        driver.switchTo().window(mwh);
        driver.close();
      }
    }*/



  // ----------------------------------------------------   Methods not in use   ------------------------------------------------------------------------------------



  public void setElementText(WebElement element, String text) {
    element.click();
    element.clear();
    //Log.info("entering text '" + text + "' into element " + element);
    element.sendKeys(text);
    // Assert.assertEquals(element.getAttribute("value"), text);
  }

  public void clickElement(WebElement element) {
    // Log.info("clicking on element " + element + "");
    try{
      element.click();
    }catch(Exception e) {
      if (driver.getCurrentUrl().startsWith("http://www.favecrafts.com/")) {
        WebElement popupWindowCloseBtn = driver.findElement(By.id("newsletterSignUpDivAnimeCloseLink")); // OR: .//*[@id='newsletterSignUpDivAnimeCloseLink']/a
        popupWindowCloseBtn.click();
        System.out.print("pop up clicked");
      } else if (driver.getCurrentUrl().startsWith("http://www.todby.com")) {
        WebElement popupWindowCloseBtn = driver.findElement(By.xpath(".//*[@id='newsletterSignUpDivAnimeCloseLink']/a"));
        popupWindowCloseBtn.click();
        System.out.print("pop up clicked");
      } else if (driver.getCurrentUrl().startsWith("http://www.mrfood.com")) {
        WebElement popupWindowCloseBtn = driver.findElement(By.xpath(".//*[@id='newsletterSignUpDivAnimeCloseLink']/a"));
        popupWindowCloseBtn.click();
        System.out.print("pop up clicked");
      } else if (driver.getCurrentUrl().startsWith("http://www.allfreeknitting.com")) {
        WebElement popupWindowCloseBtn = driver.findElement(By.xpath(".//*[@id='newsletterSignUpDivAnimeCloseLink']/a"));
        popupWindowCloseBtn.click();
        System.out.print("pop up clicked");
        element.click();
      }
    }
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
      // was 10 and changed into 2
      new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(element));
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
