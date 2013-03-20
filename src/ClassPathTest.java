/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bgu
 */
public class ClassPathTest {
    
    public static void main(String[] args) {
        String fullClassName = "";
        
        if (args.length>0) fullClassName = args[0];
        
        fullClassName = "java.lang.String";
        boolean result = testClassPath(fullClassName);
        System.out.println("fullClassName is on class path = " + result);
        
        fullClassName = "java.lang.StringParser";
        result = testClassPath(fullClassName);
        System.out.println("fullClassName is on class path = " + result);        
    }

   /**
    * this method checks if the given class is on class path
    * @return true if the given class is on Java classpath
    */
   public static boolean testClassPath(String fullClassName) {
      boolean result = false;
      try {
         Class.forName(fullClassName);
         result = true;
      } catch (Throwable e) {
         e.printStackTrace();
      }

      System.out.println("testClassPath() - result = " + result);
      return result;
   }    
}
