package il.carambola.pages;

import il.carambola.Consts;
import il.carambola.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Iakov Volf
 */
public class Layout_120_Page extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    private Integer sumOfClicks = 0;
    private String testUrl = null;


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
    @FindBy(className = Consts.SHARE_FB_CLASS)
    WebElement shareFB;
    @FindBy(xpath = ".//*[@id='InContent-container-centerWrapper0']/div/div/div[1]/div[2]/div[3]/div[5]/i[1]")
    WebElement shareFBEndingScreen;
    @FindBy(className = Consts.SHARE_TWITTER_CLASS)
    WebElement shareTwitter;
    @FindBy(xpath = ".//*[@id='InContent-container-centerWrapper0']/div/div/div[1]/div[2]/div[3]/div[5]/i[2]")
    WebElement shareTwitterEndingScreen;
    @FindBy(className = Consts.SCORE_UNIT_CLASS)
    WebElement scoreUnit;
    @FindBy(className = Consts.TITLE_CLASS)
    WebElement unitTitle;
    @FindBy(xpath = Consts.TITLE_XPATH)
    WebElement unitTitleX;
    @FindBy(className = Consts.ENDING_SCREEN_MSG_TITLE_CLASS)
    WebElement endingScreenMsgTitle;
    @FindBy(className = Consts.ENDING_SCREEN_MSG_NAME_CLASS)
    WebElement endingScreenMsgName;
    @FindBy(className = Consts.ENDING_SCREEN_MSG_BTN_CLASS)
    WebElement endingScreenMsgBtn;
    @FindBy(className = Consts.ENDING_SCREEN_SCORE_UNIT_CLASS)
    WebElement endingScreenScoreUnitText;
    @FindBy(className = Consts.ENDING_SCREEN_SHARE_TITLE_CLASS)
    WebElement endingScreenShareTitle;
    @FindBy(className = Consts.ENDING_SCREEN_SHARE_TITLE2_CLASS)
    WebElement endingScreenShareTitle2;
    @FindBy(className = Consts.ENDING_SCREEN_START_BTN_CLASS)
    WebElement startBtn;
    //public ProfilePage profilePag

    public Layout_120_Page(WebDriver driver) {
        super(driver);

        //this.PAGE_URL = baseUrl + "/SitePages/createUser.aspx?ReturnUrl=HomePage";
        PageFactory.initElements(driver, this);
    }

    @Step("Find text of item")
    public boolean isTextExists(Integer itemNo) throws IOException {
        // must declare the WebElement here so we can use the "itemNo" (its a dynamic class)
        WebElement Item = driver.findElement(By.className(Consts.FIRST_ITEM_CLASS + itemNo));
        return findText(Item, itemNo);
    }

    @Step("Find the word 'Question'")
    public boolean isScoreTitleExists() throws IOException {
        // must declare the WebElement here so we can use the "itemNo" (its a dynamic class)
        WebElement scoreTitle = driver.findElement(By.className(Consts.SCORE_TITLE_CLASS));
        return findScoreTitle(scoreTitle);
    }

    @Step("Click True")
    public Layout_120_Page pressTrueButton(Integer noOfClicks) throws InterruptedException {
        Integer i = 0;
        while ( i < noOfClicks){
            clickElement(TrueButton);
            Log.info("V- click number: " + (i+1) + " on element 'TrueButton'");
            Thread.sleep(1500);
            i++;
        }
        return this;
    }
    @Step("Click False")
    public Layout_120_Page pressFalseButton(Integer noOfClicks) throws InterruptedException {
        Integer i = 0;
        while ( i < noOfClicks){
            clickElement(FalseButton);
            Log.info("V- click number: " + (i+1) + " on element 'FalseButton'");
            Thread.sleep(1500);
            i++;
        }
        return this;
    }

    @Step("Click Btn and check its content")
    public Layout_120_Page pressButtonAndCheckContent(boolean tf, Integer noOfClicks) throws InterruptedException, IOException {
        //Integer i = 0; // made a global parameter at the top of the Class
        // check if url was changed. if it did, init sum of clicks to 0
        String a = getTitle();
        if(! a.equals(testUrl)){
            sumOfClicks = 0;
            testUrl = getTitle();
        } // if a test reached 10 clicks, we need to init the parameter IN the next Test URL

        Integer maxClicks = sumOfClicks;
        if (tf){
            while ( (sumOfClicks < noOfClicks + maxClicks) && sumOfClicks < 10){
                Log.info("Item " + sumOfClicks + " is: ");
                // first item
                isTextExists(sumOfClicks);
                // first image
                isImageExists(sumOfClicks);  // also prints the src. no need for printImage()

                //click to next
                clickElement(TrueButton);
                Log.info("V- click number: " + (sumOfClicks + 1) + " on element 'TrueButton'");
                Thread.sleep(1500);
                sumOfClicks++;
            }
        }else{
            while ( (sumOfClicks < noOfClicks + maxClicks) && sumOfClicks < 10){
                Log.info("Item " + sumOfClicks + " is: ");
                // first item
                isTextExists(sumOfClicks);
                // first image
                isImageExists(sumOfClicks);  // also prints the src. no need for printImage()

                //click to next
                clickElement(FalseButton);
                Log.info("V- click number: " + (sumOfClicks + 1) + " on element 'FalseButton'");
                Thread.sleep(1500);
                sumOfClicks++;
            }
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
        waitUntilIsLoadedCustomTime(CbolaBoard, 160);

        return this;  // ?? why like this?
    }

    public boolean CheckThatYesButtonExists() {
        return exists(TrueButton);

    }

    public boolean CheckThatCenterWrapperExists() {
        return exists(centerWrapper);

    }
    public boolean isScriptValidHere(){
        return IsScriptValid1(ContentScript);
    }

    public boolean isScriptValidHere2() {
//        waitUntilIsLoadedCustomTime(ContentScript, 30);
        boolean isScript = false;
        if(IsScriptValid1(ContentScript)){
            isScript = true;
        }else{ //search iFrame- maybe we are inside one
            // find all iframes in page
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            Integer maxFrames = iframes.size();

            System.out.println(maxFrames);
            System.out.println();

            for(int i = 0; i < maxFrames -1 ; i++) {
                //   String frameId = iframes.get(i).getAttribute("id");
                // System.out.println(frameId);
                System.out.println("switched to frame" + i);
                driver.switchTo().frame(i);

                try {
                    WebElement scriptTagTemp = driver.findElement(By.id(Consts.SCRIPT_ID));

                    String b = scriptTagTemp.toString();
                    System.out.println(b);

                    isScript = true;
                    System.out.println(" YES - Carambola WAS found in iFrame: " + i);

                    //   break;
                } catch (Exception e) {
                    System.out.println("Carambola WASN'T found in iFrame: " + i);
                }
                if (isScript) {
                    //continue; not good
                    i = maxFrames - 1;
                }else{driver.switchTo().defaultContent();}

            }
        }
        return isScript;
    }

    @Step("Check if board exists")
    public boolean IsBoardExist(){
        boolean isBoardValid = true;
        if (CbolaBoard != null && CbolaBoard.isDisplayed()) {
            Log.info("V - cbola board displayed");
            System.out.println("YEAH!- cbola board was displayed");
        } else {
            Log.error("X - cbola board WASNT displayed");
            //errors.add("\nBrowser: " + browserName + " URL: " + (i + 1) + " SHAYSE- cbola borad is NOT displayed");
            //testRunSuccessfull = false;
            isBoardValid = false;
            if (CbolaBoard == null) {
                Log.error("X - cbola board WASNT loaded");
            }
        }
        return isBoardValid;
    }

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
    @Step("Check if Image Exists")
    public boolean isImageExists(Integer imgNo){
        WebElement imgElement = driver.findElement(By.className(Consts.FIRST_IMG_CLASS + imgNo));
        boolean isCbolaImg = imgElement.isDisplayed();
        if(isCbolaImg) {
            // (same as print img method)
          //  String srcOfImage = imgElement.getAttribute("src");
            Log.info("V - Image " + imgNo + " was displayed: " + printImage(imgNo));
            // System.out.println("From Page class: YEAH!- we can see 1st image element:");

        } else {
            Log.error("X - Image " + imgNo + " WASN'T displayed");
            //  System.out.println("From Page class: SHAYSE - 1st img element WASNT displayed");
            isCbolaImg = false;
        }
        return isCbolaImg;
    }
    // get the src of the img and print it
    @Step("Print Image src url")
    public String printImage(Integer imgNo){
        WebElement imgSrc = driver.findElement(By.className(Consts.FIRST_IMG_CLASS + imgNo));
        String srcOfImage = imgSrc.getAttribute("src");
        //Log.info("V - Image " + imgNo + " src is: " + srcOfImage);
        return srcOfImage;
    }
    // check Share btns. passes the specific layer's elements to Method in PAGE
    @Step("Is share btns exists")
    public void isShareBtnExists(String firm) throws IOException {
        if(firm.equals("FB")){
            findShareBtn(firm, shareFB);
        }else if(firm.equals("Twitter")){
            findShareBtn(firm, shareTwitter);
        }
    }
    @Step("Is share btns Ending Screen exists")
    public void isShareBtnEndingScreenExists(String firm) throws IOException {
        if(firm.equals("FB")){
            findShareBtn(firm, shareFBEndingScreen);
        }else if(firm.equals("Twitter")){
            findShareBtn(firm, shareTwitterEndingScreen);
        }
    }
    @Step("Is the score unit match the Consts")
    public void isScoreUnitExists() throws IOException {
        isScoreUnit(scoreUnit);
    }
    @Step("Find Unit Title")
    public void isUnitTitleExists()throws IOException{
        findUnitTitle(unitTitleX); // trying the Xpath. if it works, possible to delete findBy class
    }
    @Step("Find Ending Screen msg- title")
    public boolean isEndingScreenMsgTitleExists(){
        return findEndingScreenMsg(endingScreenMsgTitle);
    }

    // find the name of the next game (ONLY GOOD for Trivia layouts- 120, 140, 1400 and therefor the method will be in this class
    @Step("Find Ending Screen msg- name of next game")
    public boolean findEndingScreenMsgName(){
        boolean isEndingScreenMsgName = endingScreenMsgName.isDisplayed();
        if(isEndingScreenMsgName){
            String nextGameName = endingScreenMsgName.getText();
            Log.info("V- Ending Screen Msg name exists. next game is: " + nextGameName);
        }else{
            Log.error("X- Ending Screen Msg name DID NOT exist in page");
        }
        return isEndingScreenMsgName;
    }
    @Step("Find 'Start' btn")
    public boolean isStartNextGameBtnExists(){
        boolean isStartBtn = endingScreenMsgBtn.isDisplayed();
        if(isStartBtn){
            String startBtnText = endingScreenMsgBtn.getText();
            Log.info("V- Start button exists and it says: " + startBtnText);
        }else{
            Log.error("X- Start button DOESN'T exists");
        }
        return isStartBtn;
    }
    @Step("You scored a...")
    public boolean isScoreUnitTitle(){
        boolean isScoreTitle = endingScreenScoreUnitText.isDisplayed();
        if(isScoreTitle){
            String scoreText = endingScreenScoreUnitText.getText();
            Log.info("V- score title was displayed: " + scoreText);
            isScoreTitleCorrect(scoreText);
        }else{
            Log.error("X- score title WASNT displayed");
        }
        return isScoreTitle;
    }

    public void isScoreTitleCorrect(String unitTitle){
        Integer isScoreNumber = Integer.parseInt(driver.findElement(By.className(Consts.ENDING_SCREEN_SCORE_NUMBER_CLASS)).getText());

        switch(isScoreNumber){
            case 1:
            case 2:
                if(Consts.SCORE_1_2.equals(unitTitle)){
                    Log.info("V - score unit title is correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_1_2);
                }else{
                    System.out.println("score unit title is NOT Correct");
                    Log.error("X - score unit title is NOT correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_1_2);
                }break;

            case 3:
            case 4:
                if(Consts.SCORE_3_4.equals(unitTitle)){

                    Log.info("V - score unit title is correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_3_4);
                }else{
                    System.out.println("score unit title is NOT Correct");
                    Log.error("X - score unit title is NOT correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_3_4);
                }break;

            case 5:
            case 6:
                if(Consts.SCORE_5_6.equals(unitTitle)){
                    Log.info("V - score unit title is correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_5_6);
                }else{
                    System.out.println("score unit title is NOT Correct");
                    Log.error("X - score unit title is NOT correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_5_6);
                }break;

            case 7:
            case 8:
                if(Consts.SCORE_7_8.equals(unitTitle)){
                    Log.info("V - score unit title is correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_7_8);
                }else{
                    System.out.println("score unit title is NOT Correct");
                    Log.error("X - score unit title is NOT correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_7_8);
                }break;

            case 9:
                if(Consts.SCORE_9.equals(unitTitle)){
                    Log.info("V - score unit title is correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_9);
                }else{
                    System.out.println("score unit title is NOT Correct");
                    Log.error("X - score unit title is NOT correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_9);
                }break;
            case 10:
                if(Consts.SCORE_10.equals(unitTitle)){
                    Log.info("V - score unit title is correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_10);
                }else{
                    System.out.println("score unit title is NOT Correct");
                    Log.error("X - score unit title is NOT correct. CABA scored: " + isScoreNumber + " and got this title: " + Consts.SCORE_10);
                }break;
        }

    }
    @Step("Find ES text- CHALLENGE")
    public boolean isEndingScreenShareTitleExists(){
        boolean isEndingScreenShareTitle = endingScreenShareTitle.isDisplayed();
        if(isEndingScreenShareTitle){
            String shareTitle = endingScreenShareTitle.getText();
            Log.info("V- ending screen share title exists: " + shareTitle);
        }else{
            Log.error("X- ending screen share title DOESNT exists");
        }
        return isEndingScreenShareTitle;
    }
    @Step("Find ES text- YOUR FRIENDS")
    public boolean isEndingScreenShareTitle2Exists(){
        boolean isEndingScreenShareTitle2 = endingScreenShareTitle2.isDisplayed();
        if(isEndingScreenShareTitle2){
            String shareTitle = endingScreenShareTitle2.getText();
            Log.info("V- ending screen share title 2 exists: " + shareTitle);
        }else{
            Log.error("X- ending screen share title 2 DOESNT exists");
        }
        return isEndingScreenShareTitle2;
    }
    @Step("Click START")
    public void pressStartBtn(){
        clickElement(startBtn);
    }
}
