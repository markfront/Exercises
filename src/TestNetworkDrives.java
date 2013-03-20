
import java.io.BufferedReader;
import java.io.File;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author owner
 */
public class TestNetworkDrives {

    public static void main(String[] args) {
        listDrives();
    }

    public static void listDrives() {
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            System.out.println("root " + i + " = " + roots[i]);
            System.out.println("isNetworkDrive = " + isNetworkDrive(getDriveName(roots[i].getAbsolutePath())));
        }
    }

    public static String getDriveName(String path) {
        String result = path;
        if (path.endsWith(":\\")) {
            result = path.substring(0, path.length() - 1);
        }

        return result;
    }

    public static boolean isNetworkDrive(String drive) {
        boolean result = false;

        String command = "cmd /c net use " + drive;
        System.out.println("command = " + command);
        try {
            Process p = Runtime.getRuntime().exec(command);
            InputStream stderr = p.getErrorStream();
            InputStream stdout = p.getInputStream();

            StringBuffer consoleErrors = new StringBuffer();
            StringBuffer consoleOutput = new StringBuffer();
            
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(stdout));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                consoleOutput.append(line);
            }
            in.close();
            
            in = new BufferedReader(new InputStreamReader(stderr));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                consoleErrors.append(line);
            }
            in.close();
            
            String mark = "The network connection could not be found.";
            if (consoleErrors.toString().contains(mark)) {
                result = false;
            } else {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return result;
    }
}
