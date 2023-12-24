/**
 * You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.

The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.

Return the minimum number of operations needed to make s alternating.
 */

 // my solution, check 0 and 1 at the beginning
 class Solution {
    public int minOperations(String s) {
        int count0 = 0;
        int count1 = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (i % 2 == 0) {
                    count1++;
                } else {
                    count0++;
                }
            } else {
                if (i % 2 == 0) {
                    count0++;
                } else {
                    count1++;
                }
            }
        }

        return Math.min(count0, count1);
    }
}

// improved solution
/**
 * Let n be the length of s. There are n indices. Notice that an index i only needs to be fixed for either start0 or start1, but never both. In the above image, if we create an alternating string that starts with 0, we need to perform operations at indices 0, 1, 4, 5, 6. This means that indices 0, 1, 4, 5, 6 are already correct for the alternating string that starts with 1.

If we create an alternating string that starts with 0, indices 2, 3 are already correct. Thus, when considering the alternating string that starts with 1, we would need to fix indices 2, 3.

What does this mean? For a given s, if we need start0 operations to create the alternating string that starts with 0, we will need exactly n - start0 operations to create the alternating string that starts with 1.

Thus, we only need to calculate either start0 or start1 (it doesn't matter which one, we'll calculate start0 in this article). We can then obtain the other value by subtracting from n.
 */
class Solution {
    public int minOperations(String s) {
        int start0 = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) == '1') {
                    start0++;
                }
            } else {
                if (s.charAt(i) == '0') {
                    start0++;
                }
            }
        }
        
        return Math.min(start0, s.length() - start0);
    }
}