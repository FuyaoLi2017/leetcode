// brute force solution
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] res = new int[findNums.length];
        int j;
        for (int i = 0; i < findNums.length; i++) {
            boolean found = false;
            for (j = 0; j < nums.length; j++) {
                if (found && nums[j] > findNums[i]) {
                    res[i] = nums[j];
                    break;
                }
                if (nums[j] == findNums[i]) {
                    found = true;
                }
            }
            if (j == nums.length) {
                res[i] = -1;
            }
        }
        return res;
    }
}

// stack Solution
