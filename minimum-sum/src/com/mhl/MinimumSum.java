package com.mhl;

/**
 * Created by minghuiliu on 28/7/18.
 */
public class MinimumSum {

    private static long minimumSum(Range[] ranges) {
      long total = 0;
      Range r = ranges[0];
      for (int i = 0; i < ranges.length - 1; i++) {
        int gap;
        Range r2 = ranges[i + 1];
        if (r2.from > r.to) {
          gap = Math.abs(r2.from - r.to);
          r = new Range(r2.from, r2.from);
        } else if (r.from > r2.to) {
          gap = Math.abs(r.from - r2.to);
          r = new Range(r2.to, r2.to);
        } else {
          gap = 0;
          r = new Range(Math.max(r.from, r2.from), Math.min(r.to, r2.to));
        }
        total = total + gap;
        print(r);
      }
      return total;
    }

    public static void main(String[] args) {

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(1, 10), new Range(1, 10), new Range(1, 10) }));

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(1, 3), new Range(3, 7), new Range(-2, 0) }));

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(1, 3), new Range(1, 4), new Range(4, 7) }));

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(1, 3), new Range(1, 3), new Range(4, 8), new Range(1, 3), new Range(1, 3), new Range(4, 8) }));

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(-10, -4), new Range(-20, -11), new Range(-2, -1) }));

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(1, 300), new Range(1150, 2000), new Range(4, 8), new Range(1, 3), new Range(1, 3), new Range(4, 8) }));

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(1, 2), new Range(2, 6), new Range(6, 10), new Range(11, 12), new Range(12, 29), new Range(29, 40) }));

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(3,7), new Range(1,3), new Range(-2, 0), new Range(-8, -3)}));

      System.out.printf("The minimum total is: %d\n\n",
          minimumSum(new Range[] { new Range(1,100), new Range(20,40), new Range(30,50), new Range(20,50), new Range(21,25), new Range(42,80), new Range(20,80), new Range(30,51)}));

    }

    private static void print(Range r) {
      System.out.printf("Eligible: ");
      for (int i = r.from; i <= r.to; i++) System.out.printf("%d ", i);
      System.out.printf("\n");
    }

    private static class Range {
      int from;
      int to;

      Range(int from, int to) {
        this.from = from;
        this.to = to;
      }
    }

}
