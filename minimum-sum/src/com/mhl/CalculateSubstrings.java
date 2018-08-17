package com.mhl;

public class CalculateSubstrings {

  public static void main(String[] args) {
    System.out.println("---------------- Result: " + calculateSubstrings("bbb"));
    System.out.println("---------------- Result: " +calculateSubstrings("baaaaa"));
    System.out.println("---------------- Result: " +calculateSubstrings("abcabc"));
    System.out.println("---------------- Result: " +calculateSubstrings("testtesttest"));
  }

  /**
   * 	as WYY,YWY or	YYW
   * @param x
   * @return
   */

  public static int calculateSubstrings(String x) {
    int length = x.length();
    int match = 0;

    for (int w = 1 ; w <= length - 1; w++) {
      if ((length - w) % 2 == 0) {
        int y = (length - w)/2;
        if (x.substring(w, w+y).equalsIgnoreCase(x.substring(w + y))) {
          match++;
        }
        if (x.substring(0, y).equalsIgnoreCase(x.substring(w + y))) {
          match++;
        }
        if (x.substring(0, y).equalsIgnoreCase(x.substring(y, y + y))){
          match++;
        }
      }
    }
    return match;
  }


}
