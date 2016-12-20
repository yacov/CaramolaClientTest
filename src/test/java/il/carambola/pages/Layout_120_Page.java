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

    @FindBy(className = Consts.SCRIPT_CLASS)
    WebElement ContentScript;

    @FindBy(xpath = Consts.CBOLA_LAYER_XPATH)
    WebElement cbolaLayer; //old = Wrapper

    //@FindBy(xpath = "//div[@class='" + Consts.BOARD_CLASS + "']")
    //WebElement CbolaBoard;
    @FindBy(xpath = Consts.COVERAGE_XPATH)
    WebElement CbolaCoverage; // old = Board

    //@FindBy(xpath = "//*[@id='InContent-container-centerWrapper0']//div[@class='cbola_board cbola_board0 unselectDrag']")
    //WebElement CbolaBoard;

    @FindBy(xpath = Consts.TRUE_BTN_XPATH)
    WebElement TrueButton;
    @FindBy(xpath = Consts.FALSE_BTN_XPATH)
    WebElement FalseButton;

    @FindBy(xpath = Consts.SHARE_FB_XPATH)
    WebElement shareFB;
    @FindBy(xpath = Consts.ENDING_SCREEN2_SHARE_FB_XPATH)
    WebElement shareFBEndingScreen;
    @FindBy(xpath = Consts.SHARE_TWITTER_XPATH)
    WebElement shareTwitter;
    @FindBy(xpath = Consts.ENDING_SCREEN2_SHARE_TWITTER_XPATH)
    WebElement shareTwitterEndingScreen;
    @FindBy(xpath = Consts.SCOREBOARD_SCORE_TEXT_XPATH)
    WebElement scoreUnit;
    @FindBy(xpath = Consts.TITLE_XPATH)
    WebElement unitTitle;
    @FindBy(xpath = Consts.ENDING_SCREEN2_NEXT_HEADER)
    WebElement endingScreenNextHeader;
    @FindBy(xpath = Consts.ENDING_SCREEN2_NEXT_TITLE)
    WebElement endingScreenNextGameTitle;
    //@FindBy(className = Consts.ENDING_SCREEN_MSG_BTN_CLASS)
    //WebElement endingScreenMsgBtn;
    @FindBy(xpath = Consts.ENDING_SCREEN_TITLE_XPATH)
    WebElement endingScreenTextYouScored;
    @FindBy(xpath = Consts.ENDING_SCREEN2_CHALLENGE_XPATH)
    WebElement endingScreenTextChallenge;
    @FindBy(xpath = Consts.ENDING_SCREEN2_YOUR_FRIENDS_XPATH)
    WebElement endingScreenTextYourFriends;
    @FindBy(xpath = Consts.ENDING_SCREEN2_START_BTN)
    WebElement startBtn;
    //public ProfilePage profilePag

    public Layout_120_Page(WebDriver driver) {
        super(driver);

        //this.PAGE_URL = baseUrl + "/SitePages/createUser.aspx?ReturnUrl=HomePage";
        PageFactory.initElements(driver, this);
    }

    @Step("Find text of item")
    public boolean isTextExists(Integer currentItem) throws IOException {

        // must declare the WebElement here so we can use the "itemNo" (its a dynamic class)
        WebElement Item = driver.findElement(By.xpath(Consts.ACTIVE_ITEM_XPATH));
        return findText(Item, currentItem);
    }

    @Step("Find the word 'Question'")
    public boolean isScoreTitleExists() throws IOException {
        // must declare the WebElement here so we can use the "itemNo" (its a dynamic class)
        WebElement scoreTitle = driver.findElement(By.xpath(Consts.SCOREBOARD_PROGRESS_TITLE_XPATH));
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
        waitUntilIsLoadedCustomTime(CbolaCoverage, 160);

        return this;  // ?? why like this?
    }

    public boolean CheckThatYesButtonExists() {
        return exists(TrueButton);

    }

    public boolean CheckThatCenterWrapperExists() {
        return exists(cbolaLayer);

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
                    WebElement scriptTagTemp = driver.findElement(By.className(Consts.SCRIPT_CLASS));

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
        if (CbolaCoverage != null && CbolaCoverage.isDisplayed()) {
            Log.info("V - cbola board displayed");
            System.out.println("YEAH!- cbola board was displayed");
        } else {
            Log.error("X - cbola board WASNT displayed");
            //errors.add("\nBrowser: " + browserName + " URL: " + (i + 1) + " SHAYSE- cbola borad is NOT displayed");
            //testRunSuccessfull = false;
            isBoardValid = false;
            if (CbolaCoverage == null) {
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
    public boolean isImageExists(Integer currentImg){
        Integer imgNo = currentImg;
        WebElement imgElement = driver.findElement(By.xpath(Consts.ACTIVE_IMG_XPATH));
        boolean isCbolaImg = imgElement.isDisplayed();
        if(isCbolaImg) {
            // (same as print img method)
            //String srcOfImage = imgElement.getAttribute("style").substring(24,100);
            Log.info("V - Image " + imgNo + " was displayed: " + printImage());
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
    public String printImage( ){
        //Integer imgNo = currentImg;
        WebElement imgSrc = driver.findElement(By.xpath(Consts.ACTIVE_IMG_XPATH));
        String srcOfImage = imgSrc.getAttribute("style").substring(24,75);;
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
        findUnitTitle(unitTitle);
    }
    @Step("Find Ending Screen msg- title")
    public boolean isEndingScreenMsgTitleExists(){
        return findEndingScreenMsg(endingScreenNextHeader);
    }

    // find the name of the next game (ONLY GOOD for Trivia layouts- 120, 140, 1400 and therefor the method will be in this class
    @Step("Find Ending Screen msg- name of next game")
    public boolean findEndingScreenMsgName(){
        boolean isEndingScreenMsgName = endingScreenNextGameTitle.isDisplayed();
        if(isEndingScreenMsgName){
            String nextGameName = endingScreenNextGameTitle.getText();
            Log.info("V- Ending Screen Msg name exists. next game is: " + nextGameName);
        }else{
            Log.error("X- Ending Screen Msg name DID NOT exist in page");
        }
        return isEndingScreenMsgName;
    }
    @Step("Find 'Start' btn")
    public boolean isStartNextGameBtnExists(){
        boolean isStartBtn = startBtn.isDisplayed();
        if(isStartBtn){
            String startBtnText = startBtn.getText();
            Log.info("V- Start button exists and it says: " + startBtnText);
        }else{
            Log.error("X- Start button DOESN'T exists");
        }
        return isStartBtn;
    }
    @Step("You scored a...")
    public boolean isScoreUnitTitle(){
        boolean isScoreTitle = endingScreenTextYouScored.isDisplayed();
        if(isScoreTitle){
            String scoreText = endingScreenTextYouScored.getText();
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
        boolean isEndingScreenShareTitle = endingScreenTextChallenge.isDisplayed();
        if(isEndingScreenShareTitle){
            String shareTitle = endingScreenTextChallenge.getText();
            Log.info("V- ending screen share title exists: " + shareTitle);
        }else{
            Log.error("X- ending screen share title DOESNT exists");
        }
        return isEndingScreenShareTitle;
    }
    @Step("Find ES text- YOUR FRIENDS")
    public boolean isEndingScreenShareTitle2Exists(){
        boolean isEndingScreenShareTitle2 = endingScreenTextYourFriends.isDisplayed();
        if(isEndingScreenShareTitle2){
            String shareTitle = endingScreenTextYourFriends.getText();
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
