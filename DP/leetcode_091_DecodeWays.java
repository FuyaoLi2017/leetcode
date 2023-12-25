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

// editoral DFS with memorization
class Solution {

    Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodings(String s) {
        return recursiveWithMemo(0, s);
    }
    
    private int recursiveWithMemo(int index, String str) {
        // Have we already seen this substring?
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        
        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length()) {
            return 1;
        }

        // If the string starts with a zero, it can't be decoded
        if (str.charAt(index) == '0') {
            return 0;
        }

        if (index == str.length() - 1) {
            return 1;
        }


        int ans = recursiveWithMemo(index + 1, str);
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
             ans += recursiveWithMemo(index + 2, str);
         }

        // Save for memoization
        memo.put(index, ans);

        return ans;
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

// a direct DP solution - iterative DP editorial solution
class Solution {

    public int numDecodings(String s) {
        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        
        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i < dp.length; i++) {
            // Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
               dp[i] = dp[i - 1];  
            }
            
            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[s.length()];
    }
}

// optimize space for the solution above
class Solution {
    public int numDecodings(String s) {  
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int twoBack = 1;
        int oneBack = 1;
        for (int i = 1; i < n; i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current = oneBack;
            }
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += twoBack;
            }
           
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
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


// my failed solution
class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length()+2];
        // dp[1] = 1;
        dp[2] = 1;

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            char prev = s.charAt(i - 1);

            int curNum = c - '0';
            int prevNum = prev - '0';
            if (curNum == 0) {
                if (prevNum < 1 || prevNum > 2) {
                    return 0;
                } else {
                    if (i == 1) {
                        dp[i+2] = 1;
                    } else {
                        dp[i+2] = dp[i];
                    }
                }
            } else { // c is not '0'
                if ((prevNum == 1) || (prevNum == 2 && curNum < 7)) {
                    dp[i+2] = dp[i] + dp [i+1];
                } else {
                    dp[i+2] = dp[i+1];
                }
            }
        }

        System.out.print(dp.toString());

        return dp[s.length() + 1];
    }
}