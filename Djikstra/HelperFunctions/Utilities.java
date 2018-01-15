package HelperFunctions;

public class Utilities {

   public static String ReverseString(String param) {
      
      int len = param.length();
      String output = "";
      for (int i=len-1; i>=0; i--) {

          output += param.charAt(i);

      }
      return output;
   }
   /*
   public static void main(String[] args) {

      String text = "testing";
      System.out.println(ReverseString(text));

   }
   */

}
