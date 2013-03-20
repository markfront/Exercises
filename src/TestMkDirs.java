/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author owner
 */

import java.io.File;

public class TestMkDirs {
   public static void main(String[] args)
   {
      Thread A = new Thread() {
         public void run()
         {
            File f = new File("C:\\temp\\test\\a");
            boolean success = f.mkdirs();
            System.out.println("Thread A: success=" + success);
         }
      };
      Thread B = new Thread() {
         public void run()
         {
            File f = new File("C:\\temp\\test\\b");
            boolean success = f.mkdirs();
            System.out.println("Thread B: success=" + success);
         }
      };

      A.start();
      B.start();
   }
}

