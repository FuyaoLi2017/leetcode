/*
Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
*/

// brute force, using bucket sort
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int[] bucket = new int[nums[nums.length - 1] + 1];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                ++bucket[nums[i] - nums[j]];
            }
        }
        int count = 0;
        for (int i = 0; i < bucket.length; i++) {
            k -= bucket[i];
            if (k <= 0) return i;
        }
        return 0;
    }
}

// binary search ,dp
// use linear scan to obtain the result
// 看看花花酱的视频，这个题目理解起来颇有难度
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0;
        int r = nums[nums.length - 1] - nums[0];
        // the value in the binary search means the the total numbers that is smaller than
        // the value
        while (l <= r) {
            int count = 0;
            int j = 0;
            int m = l + (r - l) / 2;
            for (int i = 0; i < n; ++i) {
                while (j < n && nums[j] - nums[i] <= m) ++j;
                count += j - i - 1;
            }
            if (count >= k) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
