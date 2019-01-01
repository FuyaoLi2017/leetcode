/*
Given an integer number n, find its integer square root.

Assumption:

n is guaranteed to be >= 0.
Example:

Input: 18, Return: 4

Input: 4, Return: 2
*/
// 如果相乘的话很容易overflow，但是用除法就可以避免
public class Solution {
  public int sqrt(int x) {
    if (x < 2)
      return x;
    int left = 0;
    int right = x;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (x / mid == mid) {
        return mid;
      } else if (x / mid < mid) {
        right = mid;
      } else {
        left = mid;
      }
    }
    return left;
  }
}
