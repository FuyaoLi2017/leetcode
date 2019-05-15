/*
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
*/

// my solution
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int previous = nums[0];
        int currentCount = 1;
        int end = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == previous && currentCount < 2) {
                currentCount++;
                nums[end++] = nums[i];
            } else if (nums[i] == previous && currentCount >= 2) {
                continue;
            } else {
                currentCount = 1;
                nums[end++] = nums[i];
                previous = nums[i];
            }
        }
        return end;
    }
}

// a more concise solution
// remove duplicates from a sorted array
public int removeDuplicates(int[] nums) {
    int i = 0;
    for(int n : nums)
        if(i < 1 || n > nums[i - 1])
            nums[i++] = n;
    return i;
}

// Remove Duplicates from Sorted Array II (allow duplicates up to 2):
public int removeDuplicates(int[] nums) {
   int i = 0;
   for (int n : nums)
      if (i < 2 || n > nums[i - 2])
         nums[i++] = n;
   return i;
}
