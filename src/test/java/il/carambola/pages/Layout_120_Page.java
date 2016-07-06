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

/**
 * Created by Iakov Volf
 */
public class Layout_120_Page extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(id = Consts.SCRIPT_ID)
    WebElement ContentScript;

    @FindBy(id = Consts.CENTER_WRAPPER_ID)
    WebElement centerWrapper;

    @FindBy(id = Consts.BOARD_CLASS)
    WebElement CbolaBoard;

    //@FindBy(xpath = "//*[@id='InContent-container-centerWrapper0']//div[@class='cbola_board cbola_board0 unselectDrag']")
    //WebElement CbolaBoard;

    @FindBy(id = Consts.TRUE_BTN_ID)
    WebElement TrueButton;

    @FindBy(id = Consts.FIRST_IMG_CLASS+0)
    WebElement img0;

    //public ProfilePage profilePag

    public Layout_120_Page(WebDriver driver) {
        super(driver);

        //this.PAGE_URL = baseUrl + "/SitePages/createUser.aspx?ReturnUrl=HomePage";
        PageFactory.initElements(driver, this);
    }


//Fill the fileds


//methods that generate test data

    /*public String generateFirstName() {
        Random rn = new Random();
        int num = rn.nextInt(1000) + 1;
        String name = "First" + Integer.toString(num);
        return name;
    }*/

    /*public String generateLastName() {
        Random rn = new Random();
        int num = rn.nextInt(1000) + 1;
        String name = "Last" + num;
        return name;
    }*/


    public Layout_120_Page pressYesButton() {
        clickElement(TrueButton);
        Log.info("From Page class: element 'TrueButton' is clicked");
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
        Integer waitTime = 2;
        waitUntilIsLoadedCustomTime(CbolaBoard, waitTime);
        System.out.println("waited " + waitTime + " sec after cbola board");

        return this;  // ?? why like this?
    }

    public boolean CheckThatYesButtonExists() {

        // "exists()" is a method in Page.java that the element is.Displayed()
        return exists(TrueButton);

    }

    public boolean CheckThatCenterWrapperExists() {
        return exists(centerWrapper);

    }

    public boolean isScriptValidHere() {
       // waitUntilIsLoadedCustomTime(ContentScript, 30);
        return IsScriptValid1(ContentScript);
    }

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

    public void chekLayerIsCorrect() {
        checkLayerNumber(120);
    }

    // Step 4.2 - Verify 1st Image -cbolaContent-imageLoader

    public boolean isFirstImageExists() {
        boolean CbolaFirstImg = driver.findElement(By.className(Consts.FIRST_ITEM_CLASS + 0)).isDisplayed();
        if (CbolaFirstImg) {
            Log.info("From Page class: V - Image 1 was displayed");
            System.out.println("From Page class: YEAH!- we can see 1st image element:");
        } else {
            Log.info("From Page class: X - Image 1 WASNT displayed");
            System.out.println("From Page class: SHAYSE - 1st img element WASNT displayed");
            CbolaFirstImg = false;
        }
        return CbolaFirstImg;
    }
    public boolean checkImageIsCorrect(Integer imageNo){
        WebElement imageElement = driver.findElement(By.className(Consts.FIRST_ITEM_CLASS + imageNo));
        return isImageExists(imageNo, imageElement);
    }
    // get the src of the img and print it
    public void printImage() {
        String src0 = img0.getAttribute("src");
        //System.out.println(src0);
        Log.info("From Page class: V - Image 1 src is: " + src0);
    }
    public void printImage(Integer imageNo){
        // couldnt use the @FindBy with the operator so I am looking here for the element
        WebElement imgClass = driver.findElement(By.className(Consts.FIRST_IMG_CLASS + imageNo));
        String imgSrc = imgClass.getAttribute("src");
        System.out.println(imgSrc);
        Log.info("V - Image 1 src is: " + imgSrc);
    }

    public void isItemTextExists(Integer itemNo) {
        boolean isText0 = driver.findElement(By.className(Consts.FIRST_ITEM_CLASS + itemNo)).isDisplayed();

        if (isText0) {
            Log.info("V - Item 1 was displayed");
            System.out.println("YEAH!- 1st text was diplayed, and it says: ");
            String Text0s = driver.findElement(By.className(Consts.FIRST_ITEM_CLASS + itemNo)).getText();
            System.out.println(Text0s);
        } else {
            Log.info("X - Item 1 WASNT displayed");
            System.out.println("SHAYSE- 1st text WASNT displayed");
            //File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //FileUtils.copyFile(scrFile, new File(Consts.outputDirectory + "screenshot_"+ browserName +"_url_"+ i +"_1st text WASNT displayed.png"));
        }
    }
}