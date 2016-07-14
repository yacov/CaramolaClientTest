package il.carambola.pages;

import il.carambola.Consts;
import il.carambola.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Iakov Volf
 */
public class Layout_120_Page extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(id = Consts.SCRIPT_ID)
    WebElement ContentScript;

    @FindBy(id = Consts.CENTER_WRAPPER_ID)
    WebElement centerWrapper;

    @FindBy(xpath = "//div[@class='" + Consts.BOARD_CLASS + "']")
    WebElement CbolaBoard;

    //@FindBy(xpath = "//*[@id='InContent-container-centerWrapper0']//div[@class='cbola_board cbola_board0 unselectDrag']")
    //WebElement CbolaBoard;

    @FindBy(id = Consts.TRUE_BTN_ID)
    WebElement TrueButton;
    @FindBy(id = Consts.FALSE_BTN_ID)
    WebElement FalseButton;

    @FindBy(xpath = "//img[@class='cbolaContent-itemPicture cbolaContent-itemPicture0']")
    WebElement img0;

    //public ProfilePage profilePag

    public Layout_120_Page(WebDriver driver) {
        super(driver);

        //this.PAGE_URL = baseUrl + "/SitePages/createUser.aspx?ReturnUrl=HomePage";
        PageFactory.initElements(driver, this);
    }

    public boolean isTextExists(Integer itemNo) throws IOException {
        // must declare the WebElement here so we can use the "itemNo" (its a dynamic class)
        WebElement Item = driver.findElement(By.className(Consts.FIRST_ITEM_CLASS + itemNo));
        return findText(Item, itemNo);
    }

    public boolean isScoreTitleExists() throws IOException {
        // must declare the WebElement here so we can use the "itemNo" (its a dynamic class)
        WebElement scoreTitle = driver.findElement(By.className(Consts.SCORE_TITLE_CLASS));
        return findScoreTitle(scoreTitle);
    }

    public Layout_120_Page pressTrueButton(Integer noOfClicks) throws InterruptedException {
        Integer i = 0;
        while ( i < noOfClicks){
            clickElement(TrueButton);
            Log.info("From Page class: element 'TrueButton' is clicked");
            Thread.sleep(1500);
            i++;
        }
        return this;
    }
    public Layout_120_Page pressFalseButton(Integer noOfClicks) throws InterruptedException {
        Integer i = 0;
        while ( i < noOfClicks){
            clickElement(FalseButton);
            Log.info("From Page class: element 'FalseButton' is clicked");
            Thread.sleep(1500);
            i++;
        }
        return this;
    }

    //Check
    public void waitUntilPopupClosed() throws IOException, InterruptedException {
        waitUntilElementIsDisappeared("popup");
    }

    public boolean isPopUpClosed() {
        return !exists(driver.findElement(By.id("popup")));
    }


    public Layout_120_Page WaitUntilLayoutIsLoaded() {
        waitUntilIsLoadedCustomTime(CbolaBoard, 30);

        return this;  // ?? why like this?
    }

    public boolean CheckThatYesButtonExists() {
        return exists(TrueButton);

    }

    public boolean CheckThatCenterWrapperExists() {
        return exists(centerWrapper);

    }

    public boolean isScriptValidHere() {
//        waitUntilIsLoadedCustomTime(ContentScript, 30);
        return IsScriptValid1(ContentScript);
    }

    @Step("Check if board exists")
    public boolean IsBoardExist(){
        boolean isBoardValid = true;
        if (CbolaBoard != null && CbolaBoard.isDisplayed()) {
            Log.info("V - cbola board displayed");
            System.out.println("YEAH!- cbola board was displayed");
        } else {
            Log.info("X - cbola board WASNT displayed");
            //errors.add("\nBrowser: " + browserName + " URL: " + (i + 1) + " SHAYSE- cbola borad is NOT displayed");
            //testRunSuccessfull = false;
            isBoardValid = false;
            if (CbolaBoard == null) {
                Log.info("X - cbola board WASNT loaded");
            }
        }
        return isBoardValid;
    }

    //ToDo - create element locator and write method
    public Layout_120_Page pressNoButton() {
        return this;
    }

    //public boolean isOnPatientPage() {
    //return exists(questMenu);
    // }

    //check if the layout is 120. if not - end
    @Step("Check if Layer is correct")
    public void chekLayerisCorrect() {
        checkLayerNumber(120);
    }

    // Step 4.2 - Verify 1st Image -cbolaContent-imageLoader
    @Step("Check if First Image Exists")
    public boolean isFirstImageExists(Integer imgNo){
        boolean CbolaFirstImg = driver.findElement(By.className(Consts.FIRST_ITEM_CLASS + imgNo)).isDisplayed();
        if(CbolaFirstImg) {
            Log.info("From Page class: V - Image " + imgNo + " was displayed");
            // System.out.println("From Page class: YEAH!- we can see 1st image element:");
        } else {
            Log.info("From Page class: SHAYSE X - Image " + imgNo + " WASNT displayed");
            //  System.out.println("From Page class: SHAYSE - 1st img element WASNT displayed");
            CbolaFirstImg = false;
        }
        return CbolaFirstImg;
    }
    // get the src of the img and print it
    @Step("Print Image")
    public void printImage(Integer imgNo){
        WebElement src0El = driver.findElement(By.className(Consts.FIRST_IMG_CLASS + imgNo));
        String src0 = src0El.getAttribute("src");
        //System.out.println(src0);
        Log.info("From Page class: V - Image " + imgNo + " src is: " + src0);
    }
}