public class Solution {
    public int[] singleNumber(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit(the bit with 1)
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }
}

/*
A Corner Case:
When diff == numeric_limits<int>::min(), -diff is also numeric_limits<int>::min().
Therefore, the value of diff after executing diff &= -diff is still numeric_limits<int>::min().
The answer is still correct.
explanation:
test case{0, Integer.MIN_VALUE}
0 XOR Integer.MIN_VALUE = Integer.MIN_VALUE, there is still 1 bit different
Integer.MIN_VALUE in binary format is 1 following with 31 "0"s
We can still divide the nums[] into two different groups containing these 2 numbers respectively
*/
