/*
Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string
by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
*/

// 2D DP
// https://leetcode.com/problems/distinct-subsequences/discuss/37327/Easy-to-understand-DP-in-Java
// where mem[i+1][j+1] means that S[0..j] contains T[0..i] that many times as distinct subsequences.
// 1. if the current character in S doesn't equal to current character T, then we have the same number of distinct subsequences as we had without the new character.
// 2. if the current character in S equal to the current character T, then the distinct number of subsequences: the number we had before plus the distinct number of subsequences we had with less longer T and less longer S.
public int numDistinct(String S, String T) {
    // array creation
    int[][] mem = new int[T.length()+1][S.length()+1];

    // filling the first row: with 1s
    for(int j=0; j<=S.length(); j++) {
        mem[0][j] = 1;
    }
    // the first column is 0 by default in every other rows but the first, which we need.
    for(int i=0; i<T.length(); i++) {
        for(int j=0; j<S.length(); j++) {
            if(T.charAt(i) == S.charAt(j)) {
                mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
            } else {
                mem[i+1][j+1] = mem[i+1][j];
            }
        }
    }

    return mem[T.length()][S.length()];
}


// 1D-DP
public int numDistinct(String S, String T) {
        int m = S.length();
        int n = T.length();
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int old = 0;
        int now = 0;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = n; j >= 1; --j) {
                if (s[i - 1] == t[j - 1]) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n];
    }
