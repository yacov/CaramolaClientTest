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


    @FindBy(xpath = "//*[@id='InContent-container-centerWrapper0']//div[@class='cbola_board cbola_board0 unselectDrag']")
    WebElement CbolaBoard;

    @FindBy(xpath = "//*[@id='cbolaContent-leftButton0']/i")
    WebElement TrueButton;

    @FindBy(id = "InContentScript0")
    WebElement ContentScript;


    @FindBy(id = Consts.CENTER_WRAPPER_ID)
    WebElement centerWrapper;


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
}