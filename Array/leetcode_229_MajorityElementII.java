/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
*/

// https://gregable.com/2013/10/majority-vote-algorithm-find-majority.htmls
// Boyer-Moore Majority Vote algorithm
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums.length == 0)
            return res;

        // just to make the number count different, changing the start value doesn't matter
        // int num1 = 0; int num2 = 1; int count1 = 0; int count2 = 0 ; will also work
        int num1 = nums[0]; int num2 = nums[0]; int count1 = 1; int count2 = 0 ;

        for (int val : nums) {
            if(val == num1)
                count1++;
            else if (val == num2)
                count2++;
            else if (count1 == 0) {
                num1 = val;
                count1++;
                }
            else if (count2 == 0) {
                num2 = val;
                count2++;
            }
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for(int val : nums) {
            if(val == num1)
                count1++;
            else if(val == num2)
                count2++;
        }
        if(count1 > nums.length/3)
            res.add(num1);
        if(count2 > nums.length/3)
            res.add(num2);
        return res;
    }
}


// my sorting algorithm
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) return result;
        if(nums.length == 1) return Arrays.asList(nums[0]);
        if(nums.length == 2) return nums[0] == nums[1] ? Arrays.asList(nums[0]) : Arrays.asList(nums[0], nums[1]);
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i <= len*2/3;){
            int cur = nums[i];
            int nextVal = len/3;
            if(i+nextVal < len && nums[i] == nums[i+nextVal] && !result.contains(nums[i])) {
                result.add(nums[i]);
                i += nextVal;
            } else {
                i++;
            }
        }

        return result;
    }
}
