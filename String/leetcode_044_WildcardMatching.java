/*

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
*/

class Solution {

    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                // 可以不断的让p回到starIdx的后面一位的位置上
                // 这样就实现了pointer的回退
                p = starIdx + 1;
                match++;
                s = match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
}
}


// dp method
// https://www.youtube.com/watch?v=3ZDZ-N0EPV0
// DP array的右下角代表match一个empty string
/*
T[i][j] = T[i-1][j-1] ? ==  (1)
        = T[i][j-1] || T[i-1][j]   *  (2)
        False

这个解法是从右边到左边去match，也可以从左边到右边，最后做post processing
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        // process the tail
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                        match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                        match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
    }
}
