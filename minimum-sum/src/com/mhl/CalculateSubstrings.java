package com.mhl;

public class CalculateSubstrings {


  public static void main(String[] args) {
    System.out.println("---------------- Result: " + calculateSubstrings("bbb"));
    System.out.println("---------------- Result: " +calculateSubstrings("baaaaa"));
    System.out.println("---------------- Result: " +calculateSubstrings("abcabc"));
  }

  /**
   * 	as WYY,YWY or	YYW
   * @param x
   * @return
   */

  public static int calculateSubstrings(String x) {
    int length = x.length();
    System.out.println("length:" + length);
    int match = 0;

    for (int w = 1 ; w <= length - 1; w++) {
      System.out.println("--------");
      System.out.println("w:" + w);
      if ((length - w) % 2 == 0) {
        int y = (length - w)/2;
        System.out.println("y:" + y);
        System.out.println("WYY - Y1 " + x.substring(w, w+y));
        System.out.println("WYY - Y2 " + x.substring(w + y));
        System.out.println("YWY - Y1 " + x.substring(0, y));
        System.out.println("YWY - Y2 " + x.substring(w + y));
        System.out.println("YYW - Y2 " + x.substring(y, y + y));

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
