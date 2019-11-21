/*
Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input:
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
*/

// my solution
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int result = 0;
        int[][] next = new int[S.length()+1][26];

        // the last line is all -1, no match
        Arrays.fill(next[S.length()], -1);
        int[] prev = new int[26];
        Arrays.fill(prev, -1);
        for(int i = S.length()-1; i >= 0; i--){
            next[i] = Arrays.copyOfRange(prev, 0, prev.length);
            char cur = S.charAt(i);
            next[i][cur-'a'] = i;
            prev = Arrays.copyOfRange(next[i], 0, next[i].length);
        }

        for(String word : words){
            if(word.length() > S.length()) continue;
            int index = 0;
            boolean valid = true;
            for(char c : word.toCharArray()){
                if(next[index][c-'a'] == -1) {
                    valid = false;
                    break;
                }
                index = next[index][c-'a']+1;
            }
            if(valid) result++;
        }
        return result;
    }
}
