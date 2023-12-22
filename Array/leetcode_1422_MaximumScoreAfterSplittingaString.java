/**
 * Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).

The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
 */

 // My solution: https://leetcode.com/problems/maximum-score-after-splitting-a-string/solutions/4438458/java-solution-cache-zeros-and-ones-using-1-pass-then-get-result-using-second-pass/
//  Intuition
// Use one pass to traverse the array to get cache for zeros and ones.
// Then scan the cached array to get the final number.

// Approach
// In the first pass:
// For zeros, scan from the left side.
// For ones, scan from the right side.
// populate the zeros/ones if cut after index i.

// In the second pass
// Scan the cached array and find max by suming up the two array with the same index.

// The editoral solution is maintaining the single variable of ones max count and increase/decrease the zero/one count. This makes the space complexity to be O(1), which is better.

class Solution {
    public int maxScore(String s) {
        int len = s.length();
        int[] countZero = new int[len-1];
        int[] countOne = new int[len-1];

        for (int i = 0; i < len - 1; i++) {
            char valLeft = s.charAt(i);
            int prevZero = (i == 0) ? 0 : countZero[i-1];
            if (valLeft == '0') {
                countZero[i] = prevZero + 1;
            } else {
                countZero[i] = prevZero;
            }

            char valRight = s.charAt(s.length() - 1 - i);
            int prevOne = (i == 0) ? 0 : countOne[len - i - 1];
            if (valRight == '1') {
                countOne[len - i - 2] = prevOne + 1;
            } else {
                countOne[len - i - 2] = prevOne;
            }
        }

        int res = 0;
        for (int i = 0; i < len-1; i++) {
            res = Math.max(res, countZero[i] + countOne[i]);
        }
        return res;
    }
}

// standard solution
class Solution {
    public int maxScore(String s) {
        int ones = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones++;
            }
        }
        
        int ans = 0;
        int zeros = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '1') {
                ones--;
            } else {
                zeros++;
            }
            
            ans = Math.max(ans, zeros + ones);
        }
        
        return ans;
    }
}