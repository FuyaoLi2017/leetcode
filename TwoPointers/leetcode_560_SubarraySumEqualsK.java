/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/
// my slow version
class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] map = new int[nums.length+1];
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            map[i+1] = sum;
        }
        map[0] = 0;
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                int diff = map[j+1]-map[i];
                if(diff==k)res++;
            }
        }
        return res;
    }
}

// answer
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
