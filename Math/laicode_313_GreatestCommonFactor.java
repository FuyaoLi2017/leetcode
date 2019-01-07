/*
Find the greatest common factor of two positive integers.

Examples:

a = 12, b = 18, the greatest common factor is 6, since 12 = 6 * 2, 18 = 6 * 3.
a = 5, b = 16, the greatest common factor is 1.
*/
// 辗转相除法，Euclidean algorithm
public class Solution {
  public int gcf(int a, int b) {
    if (a < b) {
      return gcf(a, b - a);
    } else if (a > b) {
      return gcf(b, a - b);
    } else {
      return a;
    }
  }
}
