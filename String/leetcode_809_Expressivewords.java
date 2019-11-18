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
        if (S == null || words == null) {
            return 0;
        }
        int count = 0;
        for (String word : words) {
            if (stretchy(S, word)) {
                count++;
            }
        }
        return count;
    }

    public boolean stretchy(String S, String word) {
        if (word == null) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < S.length() && j < word.length()) {
            if (S.charAt(i) == word.charAt(j)) {
                int len1 = getRepeatedLength(S, i);
                int len2 = getRepeatedLength(word, j);
                if (len1 < 3 && len1 != len2 || len1 >= 3 && len1 < len2) {
                    return false;
                }
                i += len1;
                j += len2;
            } else {
                return false;
            }
        }
        return i == S.length() && j == word.length();
    }

    public int getRepeatedLength(String str, int i) {
        int j = i;
        while (j < str.length() && str.charAt(j) == str.charAt(i)) {
            j++;
        }
        return j - i;
    }
}


// my solution of processing the whole string
class Solution {
    public int expressiveWords(String S, String[] words) {
        // handle empty string
        List<int[]> sList = process(S);
        int result = 0;
        for(String word : words){
            List<int[]> curWordList = process(word);
            if(compareWord(sList, curWordList)){
                result++;
            }
        }
        return result;
    }

    private List<int[]> process(String  word){
        List<int[]> list = new ArrayList<>();

        // use the index of letters and letter count to present a string
        int index = 0;
        while(index < word.length()){
            int start = index;
            char cur = word.charAt(start);
            while(index < word.length() && word.charAt(index) == cur){
                index++;
            }
            list.add(new int[]{cur-'a', index-start});
        }
        return list;
    }

    private boolean compareWord(List<int[]> source, List<int[]> compare){
        if(source.size() != compare.size()) return false;
        for(int i = 0; i < source.size(); i++){
            int[] sourceInfo = source.get(i);
            int[] compareInfo = compare.get(i);
            if(sourceInfo[0] != compareInfo[0]) return false;
            if(sourceInfo[1] == compareInfo[1]
               || (sourceInfo[1] >= 3 && sourceInfo[1] > compareInfo[1])){
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
