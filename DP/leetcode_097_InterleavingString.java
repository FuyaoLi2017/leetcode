/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
*/
// https://leetcode.com/problems/interleaving-string/
// https://leetcode.com/problems/interleaving-string/solution/

// recursion
public class Solution {
    public boolean is_Interleave(String s1,int i,String s2,int j,String res,String s3)
    {
        if(res.equals(s3) && i==s1.length() && j==s2.length())
            return true;
        boolean ans=false;
        if(i<s1.length())
            ans|=is_Interleave(s1,i+1,s2,j,res+s1.charAt(i),s3);
        if(j<s2.length())
            ans|=is_Interleave(s1,i,s2,j+1,res+s2.charAt(j),s3);
        return ans;

    }
    public boolean isInterleave(String s1, String s2, String s3) {
        return is_Interleave(s1,0,s2,0,"",s3);
    }
}
// TC: O(2^(m+n)), SC: O(m+n) --> depth of call stack

// recusion with memorization, just remember the value stored
public class Solution {
   public boolean is_Interleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
       if (i == s1.length()) {
           return s2.substring(j).equals(s3.substring(k));
       }
       if (j == s2.length()) {
           return s1.substring(i).equals(s3.substring(k));
       }
       if (memo[i][j] >= 0) {
           return memo[i][j] == 1 ? true : false;
       }
       boolean ans = false;
       if (s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i + 1, s2, j, s3, k + 1, memo)
               || s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j + 1, s3, k + 1, memo)) {
           ans = true;
       }
       memo[i][j] = ans ? 1 : 0;
       return ans;
   }
   public boolean isInterleave(String s1, String s2, String s3) {
       int memo[][] = new int[s1.length()][s2.length()];
       for (int i = 0; i < s1.length(); i++) {
           for (int j = 0; j < s2.length(); j++) {
               memo[i][j] = -1;
           }
       }
       return is_Interleave(s1, 0, s2, 0, s3, 0, memo);
   }
}
// TC: O(2^(m+n)), SC: O(m+n) --> depth of call stack


// 2D - DP
// In this array dp[i][j] implies if it is possible to obtain a substring of length (i+j+2)
// which is a prefix of s3 by some interleaving of prefixes of strings s1 and s2 having lengths (i+1) and (j+1) respectively.
// the dp[0][0] means empty string accordingly
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}




// 1D - DP
// This approach is the same as the previous approach except that we have used only 1D dp array to
// store the results of the prefixes of the strings processed so far.
// Instead of maintaining a 2D array, we can maintain a 1D array only and update the array's element dp[i]
 // when needed using dp[i-1] and the previous value of dp[i]dp[i].
 public class Solution {
     public boolean isInterleave(String s1, String s2, String s3) {
         if (s3.length() != s1.length() + s2.length()) {
             return false;
         }
         boolean dp[] = new boolean[s2.length() + 1];
         for (int i = 0; i <= s1.length(); i++) {
             for (int j = 0; j <= s2.length(); j++) {
                 if (i == 0 && j == 0) {
                     dp[j] = true;
                 } else if (i == 0) {
                     dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                 } else if (j == 0) {
                     dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                 } else {
                     dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                 }
             }
         }
         return dp[s2.length()];
     }
 }
