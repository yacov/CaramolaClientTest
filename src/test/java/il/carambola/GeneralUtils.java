package il.carambola;


import il.carambola.util.PropertyLoader;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;
import java.text.SimpleDateFormat;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.sql.*;
import java.util.Date;

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
    public static List<Object[]> urlList = new ArrayList<Object[]>();

    //** method for sending emails
    public static void emailer(String msg) throws EmailException, IOException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(PropertyLoader.loadAuthProperty("auth.mailUID"), PropertyLoader.loadAuthProperty("auth.mailPWD")));
        email.setSSLOnConnect(true);
        email.setFrom("");
        email.setSubject("Kava's tests Emailer");
        email.setMsg(msg);
        //email.sendMimeMessage();  // makes ERROR in Tear Down (@after class)
        email.addTo("");
        //email.addTo("");

        email.send();
    }

    // writes 2 files AFTER EACH TC
    public static Connection connectToDatabaseOrDie() throws ClassNotFoundException, IOException {
        Connection conn = null;
        try {
            // init the driver. //Dynamically load driver at runtime.
            //Class.forName("org.postgresql.Driver");
            Class.forName("com.amazon.redshift.jdbc4.Driver");
            String url = PropertyLoader.loadAuthProperty("auth.redshiftURL");
            Properties props = new Properties();
            props.setProperty("UID", PropertyLoader.loadAuthProperty("auth.redshiftUID"));
            props.setProperty("PWD", PropertyLoader.loadAuthProperty("auth.redshiftPWD"));
            conn = DriverManager.getConnection(url, props);

            System.out.println("Connection established successfully");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }

    public static void queryAndPrint(Connection conn, String query) throws SQLException {
        ResultSet rs = null;
        Statement st = conn.createStatement();
        if(query.equalsIgnoreCase("qa_urls_120")){
            rs = st.executeQuery("select count(v.page_requests)as pr \n" +
                    "from main.fact_visits v\n" +
                    "where v.visit_start_date > datetime'2016-11-27 00:00';\n");
        }else if(query.equalsIgnoreCase("prod_10_urls_120")){
            rs = st.executeQuery("select p.page_url as urls\n" +
                    "from\n" +
                    "(\n" +
                    "SELECT A.domain, \n" +
                    "  A.layout,\n" +
                    "  A.page, \n" +
                    "  A.pwl,\n" +
                    "  rank() over(partition by layout order by pwl desc) as rank2\n" +
                    "FROM\n" +
                    "(\n" +
                    "select e.domain_id as domain,\n" +
                    "      e.page_id as page,\n" +
                    "  e.layout_type as layout,\n" +
                    "  count(distinct page_view_id) as pwl,\n" +
                    "  rank() over(partition by domain_id, e.layout_type order by count(distinct page_view_id) desc) as rank\n" +
                    "from   main.fact_events e\n" +
                    "where  e.event_id = 401\n" +
                    "and    e.event_date > date_add('day', -1, current_date)\n" +
                    "and    e.is_mobile = 0\n" +
                    "and    e.is_tablet = 0\n" +
                    "and    e.layout_type = 120\n" +
                    "and    e.domain_id != 461\n" +
                    "group by domain, page, layout_type\n" +
                    "order by pwl desc \n" +
                    ") A\n" +
                    "where a.rank = 1\n" +
                    ") a\n" +
                    "JOIN main.dim_pages p ON a.page = p.page_id\n" +
                    "where a.rank2 <= 10\n" +
                    ";");
        }else if(query.equalsIgnoreCase("")){

        }

        while (rs.next()) {
            urlList.add(new String[]{rs.getString("urls")});
        }

        for(int i = 0; i < urlList.size(); i++){
            System.out.println(urlList.get(i));
        }

        rs.close();
        st.close();
    }

}
