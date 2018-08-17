package com.mhl;

public class CalculateSubstrings {

  public static void main(String[] args) {
    System.out.println("Result: " + calculateSubstrings("a"));
    System.out.println("Result: " + calculateSubstrings("bbb"));
    System.out.println("Result: " + calculateSubstrings("aaaaaaaaaaaa"));
    System.out.println("Result: " + calculateSubstrings("baaaaa"));
    System.out.println("Result: " + calculateSubstrings("abcabc"));
    System.out.println("Result: " + calculateSubstrings("testtesttest"));
    System.out.println("Result: " + calculateSubstrings("abcabcabc"));
    System.out.println("Result: " + calculateSubstrings("abaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaaba"));
  }

  /**
   * WYY,YWY or	YYW
   */
  public static int calculateSubstrings(String x) {
    int length = x.length();
    int match = 0;
    for (int y = 1; y <= (length - 1)/2; y++) {
        int w = length - 2 * y;
        if (x.substring(w, w + y).equals(x.substring(w + y))) {
          match++;
        }
        if (x.substring(0, y).equals(x.substring(w + y))) {
          match++;
        }
        if (x.substring(0, y).equals(x.substring(y, y + y))){
          match++;
        }
      }
      return match;
  }
}
