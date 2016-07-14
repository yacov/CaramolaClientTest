package il.carambola;


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
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY_HH-mm"); //   dd/MM/yyyy HH:mm:ss

    public static long totalTestingTime = 0;
    public static long totalPagesLoadTime = 0;
    // public static Integer totalURLs = null;  TO DO
    public static long urlRunId = 10000;

    // writes 2 files AFTER EACH TC



}
