/*
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] == nums[slow]){
                continue;
            }else{
                ++slow;
                nums[slow] = nums[fast];
            }
        }
        return slow+1;
    }
}


// later on, my solution
class Solution {
    public int removeDuplicates(int[] nums) {
        // use two pointers
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                nums[end] = nums[i];
                end++;
            }
        }
        return end;
    }
}
