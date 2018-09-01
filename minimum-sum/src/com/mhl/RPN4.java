package com.mhl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RPN4 {

  private static List<String[]> rpn4S (String n1, String n2, String n3, String n4) {
    String arr[] = {n1, n2, n3, n4};
    int n = arr.length;
    int N = (int) Math.pow(2d, n);
    List<String[]> list = new ArrayList<>();
    for (int i = 1; i < N - 1; i++) {
      String code = Integer.toBinaryString(N|i).substring(1);
      String[] s1 = new String[3], s2 = new String[3];
      int s1i  = 0, s2i = 0;
      for (int j = 0; j < n; j++) {
        if (code.charAt(j) == '1') {
          s1[s1i++] = arr[j];
        }
      }
      for (int j = 0; j < n; j++) {
        if (code.charAt(j) == '0') {
          s2[s2i++] = arr[j];
        }
      }
      for (String op : new String[] { "+", "-", "*", "/" }) {
        if (s1[1] == null) {
          for (String[] a : rpn3S(s2[0], s2[1], s2[2])) {
            list.add(new String[] { s1[0], a[0], a[1], a[2], a[3], a[4], op });
            list.add(new String[] { a[0], a[1], a[2], a[3], a[4],  s1[0], op });
          }
        } else if (s1[2] == null) {
            for (String[] a : rpn2S(s1[0], s1[1])) {
              for (String[] b : rpn2S(s2[0], s2[1])) {
                list.add(new String[] { a[0], a[1], a[2], b[0], b[1], b[2], op });
                list.add(new String[] { b[0], b[1], b[2], a[0], a[1], a[2], op});
              }
            }

        } else {
          for (String[] a : rpn3S(s1[0], s1[1], s1[2])) {
            list.add(new String[] { a[0], a[1], a[2], a[3], a[4], s2[0], op });
            list.add(new String[] { s2[0], a[0], a[1], a[2], a[3], a[4], op });
          }
        }
      }
    }
    return list;
  }

  private static List<String[]> rpn3S(String n1, String n2, String n3) {
    String arr[] = {n1, n2, n3};
    int n = arr.length;
    int N = (int) Math.pow(2d, n);
    List<String[]> list = new ArrayList<>();
    for (int i = 1; i < N - 1; i++) {
      String code = Integer.toBinaryString(N|i).substring(1);
      String[] s1 = new String[2], s2 = new String[2];
      int s1i  = 0, s2i = 0;
      for (int j = 0; j < n; j++) {
        if (code.charAt(j) == '1') {
          s1[s1i++] = arr[j];
        }
      }
      for (int j = 0; j < n; j++) {
        if (code.charAt(j) == '0') {
          s2[s2i++] = arr[j];
        }
      }
      for (String op : new String[] { "+", "-", "*", "/" }) {
        if (s1[1] == null) {
          for (String[] a : rpn2S(s2[0], s2[1])) {
            list.add(new String[] { s1[0], a[0], a[1], a[2], op });
            list.add(new String[] { a[0], a[1], a[2], s1[0], op });
          }
        } else {
          for (String[] a : rpn2S(s1[0], s1[1])) {
            list.add(new String[] { a[0], a[1], a[2], s2[0], op });
            list.add(new String[] { s2[0], a[0], a[1], a[2], op });
          }
        }
      }
    }
    return list;
  }

  private static List<String[]> rpn2S(String n1, String n2) {
    String[] ops = new String[] { "+", "-", "*", "/" };
    List<String[]> list = new ArrayList<>();
    for (String op : ops) {
      list.add(new String[] {n1, n2, op});
      list.add(new String[] {n2, n1, op});
    }
    return list;
  }

  private static void rpn4(String n1, String n2, String n3, String n4, int result) {
    System.out.printf("\n\nNumbers: [%s,%s,%s,%s], Expected:%d\n", n1,n2,n3,n4,result);
    boolean found = false;
    for (String[] token: rpn4S(n1,n2,n3, n4)) {
      try {
        if (eval(token).compareTo(new BigDecimal(result)) == 0) {
          found = true;
          System.out.printf("Solution :\t%s = %d\n", convert(toString(token)), result);
          //TODO If want to print all possible solutions, comment below
          break;
        }
      } catch (Exception e) {
        // Ignored
      }
    }
    if (!found) System.out.println("No solution!");
  }

  private static String toString(String[] arr) {
    StringBuilder builder = new StringBuilder();
    for(String s : arr) {
      builder.append(s);
      builder.append(" ");
    }
    return builder.toString();
  }

  private static BigDecimal eval(String[] tokens) {
    String operators = "+-*/";
    Stack<String> stack = new Stack<>();

    for(String t : tokens){
      if(!operators.contains(t)){
        stack.push(t);
      } else {
        BigDecimal a = new BigDecimal(stack.pop());
        BigDecimal b = new BigDecimal(stack.pop());
        int index = operators.indexOf(t);
        switch(index){
        case 0:
          stack.push(a.add(b).toString());
          break;
        case 1:
          stack.push(b.subtract(a).toString());
          break;
        case 2:
          stack.push(a.multiply(b).toString());
          break;
        case 3:
            stack.push(String.valueOf(b.divide(a).toString()));
            break;
        }
      }
    }
    return new BigDecimal(stack.pop());
  }

  private static String convert(final String rpn) {
    class Formula {
      private final static String ops = "-+/*";
      private String ex;
      private int prec = 3;
      private Formula(String e) {
        ex = e;
      }
      private Formula(String e1, String e2, String o) {
        ex = String.format("%s %s %s", e1, o, e2);
        prec = ops.indexOf(o) / 2;
      }

      @Override
      public String toString() {
        return ex;
      }
    }
    Stack<Formula> expr = new Stack<>();
    for (String token : rpn.split("\\s+")) {
      char c = token.charAt(0);
      int idx = Formula.ops.indexOf(c);
      if (idx != -1 && token.length() == 1) {

        Formula r = expr.pop();
        Formula l = expr.pop();

        int opPrec = idx / 2;

        if (l.prec < opPrec || (l.prec == opPrec && c == '^'))
          l.ex = '(' + l.ex + ')';

        if (r.prec < opPrec || (r.prec == opPrec && c != '^'))
          r.ex = '(' + r.ex + ')';

        expr.push(new Formula(l.ex, r.ex, token));
      } else {
        expr.push(new Formula(token));
      }
    }
    return expr.peek().ex;
  }


  public static void main(String[] args) {
    /**
     * Testing...
     */
    long start = System.currentTimeMillis();

    rpn4("7","8","5","5", 1);
    rpn4("8","2","8","5", 2);
    rpn4("2","1","5","5", 3);
    rpn4("1","9","6","4", 4);
    rpn4("7","3","1","5", 5);
    rpn4("8", "5", "7", "5", 6);
    rpn4("2", "9", "2", "7", 7);
    rpn4("3", "7", "3", "9", 8);
    rpn4("7", "7", "2", "9", 9);
    rpn4("2", "9", "8", "3", 10);
    rpn4("2", "6", "9", "9",  11);
    rpn4("6", "3", "5", "4",  12);
    rpn4("6", "8", "6", "6",  13);
    rpn4("5", "9", "4", "1",  14);
    rpn4("4", "4", "7", "9",  15);
    rpn4("3", "6", "1", "4",  16);
    rpn4("9", "3", "6", "3",  17);
    rpn4("2", "3", "4", "2",  18);
    rpn4("5", "8", "8", "4",  19);
    rpn4("5", "2", "5", "4",  20);
    rpn4("7", "6", "7", "3",  21);
    rpn4("9", "7", "1", "6",  22);
    rpn4("8", "6", "5", "5",  23);
    rpn4("4", "1", "9", "5",  24);
    rpn4("5", "4", "1", "5",  25);
    rpn4("8", "9", "5", "1",  26);
    rpn4("3", "6", "1", "5",  27);
    rpn4("4", "5", "1", "3",  28);
    rpn4("1", "3", "4", "9",  29);
    rpn4("9", "1", "2", "2",  30);
    rpn4("5", "1", "9", "2",  31);
    rpn4("5", "6", "2", "6",  32);
    rpn4("4", "6", "3", "2",  33);
    rpn4("6", "1", "3", "7",  34);
    rpn4("5", "1", "5", "8",  35);
    rpn4("4", "9", "4", "5",  36);
    rpn4("5", "4", "3", "8",  37);
    rpn4("8", "4", "9", "2",  38);
    rpn4("9", "2", "7", "7",  39);
    rpn4("5", "4", "3", "9",  40);
    rpn4("7", "1", "1", "5",  41);
    rpn4("2", "2", "2", "4",  42);
    rpn4("8", "9", "3", "5",  43);
    rpn4("2", "4", "1", "4",  44);
    rpn4("2", "4", "9", "3",  45);
    rpn4("4", "8", "5", "1",  46);
    rpn4("5", "3", "7", "8",  47);
    rpn4("1", "3", "7", "9",  48);
    rpn4("7", "7", "7", "3",  49);
    rpn4("8", "1", "8", "3",  50);
    rpn4("8", "3", "2", "8",  51);
    rpn4("7", "4", "7", "3",  52);
    rpn4("4", "7", "2", "6",  53);
    rpn4("4", "3", "9", "8",  54);
    rpn4("8", "5", "5", "1",  55);
    rpn4("8", "2", "8", "8",  56);
    rpn4("8", "5", "1", "4",  57);
    rpn4("6", "9", "9", "3",  58);
    rpn4("7", "7", "2", "5",  59);
    rpn4("6", "7", "8", "1",  60);
    rpn4("7", "5", "5", "9",  61);
    rpn4("2", "6", "8", "2",  62);
    rpn4("3", "7", "7", "6",  63);
    rpn4("5", "5", "3", "6",  64);
    rpn4("3", "4", "2", "9",  65);
    rpn4("6", "3", "1", "4",  66);
    rpn4("3", "5", "3", "4",  67);
    rpn4("4", "4", "6", "3",  68);
    rpn4("6", "9", "1", "8",  69);
    rpn4("1", "8", "8", "2",  70);
    rpn4("9", "4", "2", "7",  71);
    rpn4("2", "7", "7", "4",  72);
    rpn4("8", "9", "2", "2",  73);
    rpn4("3", "1", "3", "8",  74);
    rpn4("3", "9", "1", "3",  75);
    rpn4("7", "4", "2", "1",  76);
    rpn4("1", "5", "9", "1",  77);
    rpn4("6", "5", "2", "9",  78);
    rpn4("4", "1", "7", "5",  79);
    rpn4("3", "9", "4", "5",  80);
    rpn4("9", "1", "9", "9",  81);
    rpn4("5", "6", "3", "2",  82);
    rpn4("3", "2", "6", "7",  83);
    rpn4("3", "5", "3", "6",  84);
    rpn4("1", "9", "5", "4",  85);
    rpn4("1", "8", "8", "6",  86);
    rpn4("3", "2", "6", "7",  87);
    rpn4("4", "7", "2", "5",  88);
    rpn4("6", "7", "1", "8",  89);
    rpn4("1", "1", "5", "4",  90);
    rpn4("1", "5", "5", "6",  91);
    rpn4("5", "3", "2", "7",  92);
    rpn4("2", "4", "4", "6",  93);
    rpn4("4", "4", "8", "1",  94);
    rpn4("6", "2", "8", "5",  95);
    rpn4("2", "6", "2", "2",  96);
    rpn4("3", "3", "5", "3",  97);
    rpn4("5", "4", "1", "9",  98);
    rpn4("6", "1", "4", "8",  99);
    rpn4("5", "5", "5", "1",  24);
    long end = System.currentTimeMillis();
    System.out.printf("\nTotal time: %d", end-start);

  }

}
