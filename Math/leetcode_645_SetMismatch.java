/*

The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.
*/

// my solution
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] helper = new int[nums.length];
        Arrays.fill(helper, -1);
        int[] result = new int[]{-1, -1};
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (helper[cur-1] == -1) helper[cur-1] = cur-1;
            else result[0] = cur;
        }
        for (int i = 0; i < helper.length; i++) {
            if(helper[i] == -1) result[1] = i+1;
        }
        return result;
    }
}

// using negative number as a marker
public class Solution {
    public int[] findErrorNums(int[] nums) {
        int dup = -1, missing = 1;
        for (int n: nums) {
            if (nums[Math.abs(n) - 1] < 0)
                dup = Math.abs(n);
            else
                nums[Math.abs(n) - 1] *= -1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0)
                missing = i + 1;
        }
        return new int[]{dup, missing};
    }
}
