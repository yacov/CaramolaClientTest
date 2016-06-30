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
        Log.info("element 'TrueButton' is clicked");
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
        waitUntilIsLoadedCustomTime(CbolaBoard, 15);

        return this;  // ?? why like this?
    }

    public boolean CheckThatYesButtonExists() {
        return exists(TrueButton);

    }

    public boolean CheckThatCenterWrapperExists() {
        return exists(centerWrapper);

    }

    public boolean isScriptValidHere() {
        waitUntilIsLoadedCustomTime(ContentScript, 10);
        return IsScriptValid1(ContentScript);
    }

    public boolean IsBoardExist(){
        return IsBoardExists(CbolaBoard);
    }

    //ToDo - create element locator and write method
    public Layout_120_Page pressNoButton() {
        return this;
    }

    //public boolean isOnPatientPage() {
    //return exists(questMenu);
    // }

    //check if the layout is 120. if not - end

    public void chekLayerisCorrect() {
        checkLayerNumber(120);
    }

    // Step 4.2 - Verify 1st Image -cbolaContent-imageLoader

    public boolean isFirstImageExists(){
        boolean CbolaFirstImg = driver.findElement(By.className(Consts.FIRST_ITEM_CLASS+0)).isDisplayed();
        if(CbolaFirstImg) {
            Log.info("V - Image 1 was displayed");
            System.out.println("YEAH!- we can see 1st image element:");
        } else {
            Log.info("X - Image 1 WASNT displayed");
            System.out.println("SHAYSE- 1st img element WASNT displayed");
            CbolaFirstImg = false;
        }
        return CbolaFirstImg;
    }
    // get the src of the img and print it
    public void printImage(){
        String src0 = img0.getAttribute("src");
        System.out.println(src0);
        Log.info("V - Image 1 src is: " + src0);
    }
}