
import java.util.Enumeration;
import java.util.Properties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author owner
 */
public class SystemProperties {

    public static void main(String[] args) {
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) p.get(key);
            System.out.println(key + ": " + value);
        }

        System.out.println("============================");
        String key = "os.arch";
        System.out.println(key + ": " + p.get(key));
        key = "os.name";
        System.out.println(key + ": " + p.get(key));
        key = "sun.arch.data.model";
        System.out.println(key + ": " + p.get(key));

    }
}
