/*
Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
Note: N is an integer in the range [0, 10^9].
*/
class Solution {
    public int monotoneIncreasingDigits(int N) {
        char[] S = String.valueOf(N).toCharArray();
        int i = 1;
        // first try to advance the previous pointer
        while (i < S.length && S[i-1] <= S[i]) i++;
        // find a cliff, go back and decrease it by 1, until the transformation is stable
        while (0 < i && i < S.length && S[i-1] > S[i]) S[--i]--;
        // change the preceding digits to 9
        for (int j = i+1; j < S.length; ++j) S[j] = '9';

        // parse the digit and resolve the answer
        return Integer.parseInt(String.valueOf(S));
    }
}
