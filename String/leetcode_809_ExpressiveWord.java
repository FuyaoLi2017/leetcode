/*
Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy.



Example:
Input:
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation:
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.


Notes:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters
*/
class Solution {
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String W : words) if (check(S, W)) res++;
        return res;
    }

    public boolean check(String S, String W) {
        int n = S.length(), m = W.length(), i = 0, j = 0;
        for (int i2 = 0, j2 = 0; i < n && j < m; i = i2, j = j2) {
            if (S.charAt(i) != W.charAt(j)) return false;
            // 复杂文件简单化， 脱去过程看结果， 用2对pointer跟踪变化
            while (i2 < n && S.charAt(i2) == S.charAt(i)) i2++;
            while (j2 < m && W.charAt(j2) == W.charAt(j)) j2++;
            if (i2 - i != j2 - j && i2 - i < Math.max(3, j2 - j)) return false;
        }
        return i == n && j == m;
    }
}
