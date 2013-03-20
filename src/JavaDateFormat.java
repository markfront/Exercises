
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author owner
 */
public class JavaDateFormat {

    public static void main(String[] args) {
        Date today = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("today = " + df.format(today));

        try {
           Date x = df.parse("2012-04-09 22:56:44");
           System.out.println("x = " + x);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
