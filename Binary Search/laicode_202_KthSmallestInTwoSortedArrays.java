/*
Given two sorted arrays of integers, find the Kth smallest number.

Assumptions

The two given arrays are not null and at least one of them is not empty

K >= 1, K <= total lengths of the two sorted arrays

Examples

A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.

A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
*/
// this question is similar to leetcode 004, which use part of this problem's answer
public class Solution {
  public int kth(int[] a, int[] b, int k) {
    // Assumptions: a, b is not null, at least one of them
    // is not empty, k <= a.length + b.length, k >= 1
    return kth(a, 0, b, 0, k);
  }

  private int kth(int[] a, int aleft, int[] b, int bleft, int k) {
    // a have reached the end
    if (aleft >= a.length) {
      return b[bleft + k - 1];
    }
    // b have reached the end
    if (bleft >= b.length) {
      return a[aleft + k - 1];
    }
    if (k == 1) {
      return Math.min(a[aleft], b[bleft]);
    }
    int amid = aleft + k / 2 - 1;
    int bmid = bleft + k / 2 - 1;
    int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
    int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
    if (aval <= bval) {
      return kth(a, amid + 1, b, bleft, k - k / 2);
    } else {
      return kth(a, aleft, b, bmid + 1, k - k / 2);
    }
  }
}
