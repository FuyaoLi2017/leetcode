/*
Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.

Assumptions

The given array is not null and has length of at least 1.
Examples

{2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5

{-2, -1, -3}, the largest subarray sum is -1
*/
// prefix 解法, jitao的解法
public class Solution {
  public int largestSum(int[] array) {
    int ans = Integer.MIN_VALUE;
    int minPrefix = 0;
    int sum = 0;
    for (int num : array) {
      sum += num;
      ans = Math.max(sum - minPrefix, ans);
      minPrefix = Math.min(minPrefix, sum);
    }
    return ans;
   }
}

// 更新当前值, laioffer answer
public class Solution {
  public int largestSum(int[] array) {
     int result = array[0];
     int cur = array[0];
     /*
     dp[i] means the largest sum of subarray ending at index i
     dp[i] = array[i]                 if dp[i - 1] <= 0
           = dp[i - 1] + array[i]     if dp[i - 1] > 0
     so that we can reduce the space cnsumption by recording the latest largest sum
     */
     for (int i = 1; i < array.length; i++) {
         cur = Math.max(cur + array[i], array[i]);
         result = Math.max(result, cur);
     }
     return result;
   }
}


// DP solution
public class Solution {
  public int largestSum(int[] array) {
    int[] dp = new int[array.length];
    dp[0] = array[0];
    int globalMax = array[0];
    for (int i = 1; i < array.length; i++) {
        dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
        globalMax = Math.max(globalMax, dp[i]);
    }
    return globalMax;
  }
}

// 返回3个数值，加上index
public class Solution {
  public int largestSum(int[] array) {
     int result = array[0];
     int cur = array[0];
    int resLeft = 0;
    int resRight = 0;
     int left = 0;
    int right = 0;
     /*
     dp[i] means the largest sum of subarray ending at index i
     dp[i] = array[i]                 if dp[i - 1] <= 0
           = dp[i - 1] + array[i]     if dp[i - 1] > 0
     so that we can reduce the space cnsumption by recording the latest largest sum
     */
     for (int i = 1; i < array.length; i++) {
       if (cur > 0) {
         right = i;
         cur = cur + array[i];
       } else {
         left = i;
         right = i;
         cur = array[i];
       }
       if (sum > max) {
         resLeft = left;
         resRight = right;
       }
       result = Math.max(result, cur);
     }
     return new int[]{result, resLeft, resRight};
   }
}
