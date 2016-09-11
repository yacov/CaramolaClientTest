package il.carambola;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.testng.reporters.EmailableReporter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Vector;
/**
 * Created by Yair on 19/05/2016.
 */
public class GeneralUtils {
   /* public static DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("dd").appendLiteral("/")
            .appendPattern("MM").appendLiteral("/")
            .appendPattern("yyyy").appendLiteral(" ")
            .appendPattern("HH").appendLiteral(":")
            .appendPattern("mm").appendLiteral(":")
            .appendPattern("ss").appendLiteral(":")
            .appendPattern("SSS  - ")
            .toFormatter();
*/
    public static Date date = new Date();
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss"); //   dd/MM/yyyy HH:mm:ss

    public static long totalTestingTime = 0;
    public static long totalPagesLoadTime = 0;
    // public static Integer totalURLs = null;  TO DO
    public static long urlRunId = 10000;

    //** method for sending emails
    public static void emailer(String msg) throws EmailException {
       Email email = new SimpleEmail();
       email.setHostName("smtp.googlemail.com");
       email.setSmtpPort(465);
       email.setAuthenticator(new DefaultAuthenticator("",""));
       email.setSSLOnConnect(true);
       email.setFrom("");
       email.setSubject("Kava's Emailer");
       email.setMsg(msg);
       email.addTo("yair@carambo.la");
       //email.addTo("liran@carambo.la");



       email.send();


    }
    // writes 2 files AFTER EACH TC



}
