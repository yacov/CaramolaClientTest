package il.carambola.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Iakov Volf
 */
public class Layout_120_Page extends Page {
    //private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(xpath = "//*[@id='InContent-container-centerWrapper0']//div[@class='cbola_board cbola_board0 unselectDrag']")
    WebElement CbolaBoard;

    @FindBy(xpath = "//*[@id='cbolaContent-leftButton0']/i")
    WebElement TrueButton;


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

    public String generateParentEmail() {
        Random rn = new Random();
        int num = rn.nextInt(1000) + 1;
        String ParentEmail = "hore" + num + "@yopmail.com";
        //Log.info("Parent email generated is <" + ParentEmail + ">");
        return ParentEmail;
    }

    public String generateTeacherEmail() {
        Random rn = new Random();
        int num = rn.nextInt(1000) + 1;
        String Email = "more" + num + "@yopmail.com";
        // Log.info("TeacherEmail generated is <" + Email + ">");
        return Email;
    }


    public String createMeetingDate() {

        Random rn = new Random();
        int day = rn.nextInt(27) + 1;
        int month = rn.nextInt(11) + 1;
        int year = rn.nextInt(3) + 2016;
        String meetingDate = day + "/" + month + "/" + year + " 08:00";
        System.out.println(meetingDate);
        //Log.info("MeetingDate generated is <" + meetingDate + ">");
        return meetingDate;
    }

    public String generateBirthDate() {
        Random rn = new Random();
        int day = rn.nextInt(27) + 1;
        int month = rn.nextInt(11) + 1;
        int year = rn.nextInt(3) + 2010;
        return day + "/" + month + "/" + year;
    }


    public Layout_120_Page pressYesButton() {
        clickElement(TrueButton);
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
        waitUntilIsLoaded(CbolaBoard);
        return this;
    }

    public boolean CheckThatYesButtonExists() {
        return exists(TrueButton);

    }

    //ToDo - create element locator and write method
    public Layout_120_Page pressNoButton() {
        return this;
    }

    //public boolean isOnPatientPage() {
    //return exists(questMenu);
    // }
}