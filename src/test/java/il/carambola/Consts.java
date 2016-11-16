package il.carambola;

/**
 * Created by Yair on 19/05/2016.
 */
public class Consts {
//** use FINAL if the string WONT change anywhere in the project when it is used. indicator that the variable is a constant.
    // General
    public static final String inputUrlsTxtFile = "C:\\Projects\\AutomatedRuns\\testing_120\\src\\automationFramework\\URLs4test_layout120.txt";
    public static final String outputLogFile = "C:\\Users\\Yair\\Documents\\yair\\QA\\TestAutomation\\Selenium\\output\\output_Logs_TC";
    public static final String outputErrorsFile = "\\src\\test\\resources\\output\\output_Errors_TC";
    public static final String outputDirectory = "\\src\\test\\resources\\output";


    //120

    // TRIVIA BASIC DIV
    //public static final String SCRIPT_ID = "InContentScript0"; old version
    public static final String SCRIPT_CLASS = "carambola_InContent";
    public static final String CBOLA_LAYER_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]";
    //public static final String BOARD_CLASS = "cbola_board cbola_board0 unselectDrag"; old version. NO BOARD
    public static final String BOARD_CLASS = "cbola-d-trivia";
    public static final String COVERAGE_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div[1]";

    // TRIVIA BODY
    public static final String SHARE_FB_CLASS = "cbola-d-trivia__fb";
    public static final String SHARE_FB_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div[1]/div[1]/div[1]/div[1]/i";
    public static final String SHARE_TWITTER_CLASS = "cbola-d-trivia__tw";
    public static final String SHARE_TWITTER_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div[1]/div[1]/div[1]/div[2]/i";
    //public static final String ACTIVE_IMG_CLASS = "cbola-d-trivia__item-image cbola-d-trivia__item-image--active";
    public static final String ACTIVE_IMG_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div/div/div[2]/div[2]";
    //public static final String SCOREBOARD_PROGRESS_TITLE_CLASS = "cbola-d-trivia__progress-text"; //"Question"
    public static final String SCOREBOARD_PROGRESS_TITLE_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div/div[1]/div[4]/div[1]/div[1]"; //"Question"
    public static final String SCOREBOARD_PROGRESS_COUNT_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div/div[1]/div[4]/div[1]/div[2]"; //"1/10"
    public static final String SCOREBOARD_SCORE_TEXT_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div/div[1]/div[4]/div[2]/div[1]"; //"your score"
    public static final String SCOREBOARD_SCORE_COUNT_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div/div[1]/div[4]/div[2]/div[2]"; //"0"
    //public static final String SCOREBOARD_SCORE_UNIT_CLASS = "cbola-d-trivia__score-text"; // your score

    public static final String TITLE_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div[1]/div[2]/div[2]"; // The title= the main question
    //public static final String ACTIVE_ITEM_CLASS = "cbola-d-trivia__item-desc cbola-d-trivia__item-desc--active";
    public static final String ACTIVE_ITEM_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div/div[2]/div[3]/div[1]";
    public static final String TRUE_BTN_ID = "cbolaContent-leftButton0";
    public static final String TRUE_BTN_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[2]/div[4]/div[1]";
    public static final String FALSE_BTN_ID = "cbolaContent-rightButton0";
    public static final String FALSE_BTN_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[2]/div[4]/div[2]";

    // TRIVIA FOOTER
   public static final String POWERED_BY_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div[2]/div[1]"; //"html/body/center/div/div/div[1]/div[2]/div[1]";

    // TRIVIA ENDING SCREEN
    public static final String ENDING_SCREEN_MSG_TITLE_CLASS = "cbolaContent-finalGameMessageTitle";
    public static final String ENDING_SCREEN_TITLE_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[2]/div[1]/div[1]"; //You scored a...
    public static final String ENDING_SCREEN_SCORE_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[2]/div[1]/div[2]"; // 5/10
    public static final String ENDING_SCREEN_CHALLENGE_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[2]/div[1]/div[3]"; // Challenge
    public static final String ENDING_SCREEN_YOUR_FRIENDS_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[2]/div[1]/div[4]"; // Your friends
    public static final String ENDING_SCREEN_SHARE_FB_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div[1]/div[2]/div[1]/div[5]/div[1]/i";
    public static final String ENDING_SCREEN_SHARE_TWITTER_XPATH = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div/div[1]/div[2]/div[1]/div[5]/div[2]/i";
    public static final String ENDING_SCREEN_NEXT_HEADER = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[1]/div[3]/div[1]"; // Next quiz
    public static final String ENDING_SCREEN_NEXT_TITLE = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[1]/div[3]/div[2]"; // The title of next game
    public static final String ENDING_SCREEN_START_BTN = ".//*[contains(@class,'cbola-layer in-content-container-wrapper')]/div/div[1]/div[1]/div[1]/div[3]/div[3]"; // START

    public static final String ENDING_SCREEN_MSG_NAME_CLASS = "cbolaContent-finalGameMessageName";
    public static final String ENDING_SCREEN_MSG_BTN_CLASS = "cbolaContent-finalGameMessageButton";
    public static final String ENDING_SCREEN_SCORE_UNIT_CLASS = "cbolaContent-scoreUnitTitle";
    public static final String ENDING_SCREEN_SCORE_NUMBER_CLASS = "cbola-d-trivia__score-count";
    public static final String ENDING_SCREEN_SHARE_TITLE_CLASS = "cbolaContent-scoreUnitShareTitle";
    public static final String ENDING_SCREEN_SHARE_TITLE2_CLASS = "cbolaContent-scoreUnitShareTitle2";
    //public static final String ENDING_SCREEN_SHARE_ICONS_CLASS = "cbolaContent-scoreUnitShareIcons";

    //public static final String ENDING_SCREEN_START_BTN_CLASS = "cbolaContent-finalGameMessageButton";

    //TRIVIA ADS
    public static final String AD_CLOSE_BTN = "hideAdButton";
    public static final String AD_CLOSE_BTN_XPATH = ".//*[@id='cbolaBannerStructure-1_4']/div[2]";
    public static final String BUNNER_STRUCTURE_ID = "cbolaBannerStructure-1_"; // ORIGINAL: cbolaBannerStructure0_1

    //TRIVIA TEXTS
    public static final String TEXT_QUESTION = "Question";
    public static final String TEXT_YOUR_SCORE = "Your score";
    public static final String SCORE_1_2 = "You Scored a Ridiculous";
    public static final String SCORE_3_4 = "You Scored a Lame";
    public static final String SCORE_5_6 = "You Scored a Fair";
    public static final String SCORE_7_8 = "You Scored a Mighty";
    public static final String SCORE_9 = "You Scored an Awesome";
    public static final String SCORE_10 = "you Scored a Perfect";


}
