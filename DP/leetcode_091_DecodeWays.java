/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/
// my naive Solution using recusion
// 对corner case的处理
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = {0};
        char[] array = s.toCharArray();
        helper(array, 0, count);
        return count[0];
    }

    private void helper(char[] array, int index, int[] count) {
        if (index >= array.length) {
            count[0] += 1;
            return;
        }
        if (array[index] == '0') {
                return;
        }
        if (index < array.length) {
            helper(array, index + 1, count);
        }
        if (index < array.length - 1) {
            if ((array[index] - '0') * 10 + (array[index + 1] - '0') <= 26) {
                helper(array, index + 2, count);
            }
        }
    }
}

/*USE char to access the dfs*/
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        return decoding(0, s);
    }

    private int decoding(int index, String s) {
        int n = s.length();
        // base case: this marks the ending
        if (index == n) return 1;
        if (s.charAt(index) == '0') return 0;
        int res = decoding(index+1, s);
        if (index < n-1 && (s.charAt(index) == '1' || s.charAt(index) == '2' && s.charAt(index+1) <= '6')) {
            res += decoding(index+2, s);
        }
        return res;
    }
}

// a direct DP solution
public class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
               dp[i] += dp[i-1];
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}

// a cleverer short solution
public class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n+1];
        //  0 element case
        memo[n]  = 1;
        // 1 element case
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--)    // from higher digits to lower digits
            if (s.charAt(i) == '0') continue;
            /*
            * if the substring is less than 26, it can have two possiblities,memo[i+1], memo[i+2]
            * if the substring is more than 26, it can only have one possibities, memo[i+1]
            */
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];

        return memo[0];
    }
}
