/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
*/
// two kinds of solutions
// using sliding window

// https://leetcode.windliang.cc/leetCode-30-Substring-with-Concatenation-of-All-Words.html

// solution 1
// sliding window, use a hashmap to keep the words given. Use the sliding window to check every word matches all the records
// TC: length of String s is m, the number of words is n, O(m*n)
// SC: Two hashMap, m words, it should be O(m)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        // HashMap1 stores all words
        Map<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }

        // traverse all substrings
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            // HashMap2 stores words that is currently included in the sliding window
            HashMap<String, Integer> hasWords = new HashMap<>();
            int num = 0;
            while (num < wordNum) {
                // extract the word which needs to be inspected
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                // judge whether the word is in HashMap1
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                // judge whether the number of the words in the current sliding window is larger
                // than the number of occurance in HashMap1
                if (hasWords.get(word) > allWords.get(word)) {
                    break;
                }
            } else {
                break;
            }
            num++;
        }
        if (num == wordNum) {
            res.add(i);
        }
    }
        return res;
    }
}

// solution 2, sliding window
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> res = new ArrayList<Integer>();
    int wordNum = words.length;
    if (wordNum == 0) {
        return res;
    }
    int wordLen = words[0].length();
    HashMap<String, Integer> allWords = new HashMap<String, Integer>();
    for (String w : words) {
        int value = allWords.getOrDefault(w, 0);
        allWords.put(w, value + 1);
    }
    //将所有移动分成 wordLen 类情况
    //情况一：当子串完全匹配，移动到下一个子串的时候。
    //情况二：当判断过程中，出现不符合的单词。
    for (int j = 0; j < wordLen; j++) {
        HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
        int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
        //每次移动一个单词长度
        for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
            boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //出现情况三，遇到了符合的单词，但是次数超了
                    if (hasWords.get(word) > allWords.get(word)) {
                        // hasWords.put(word, value);
                        hasRemoved = true;
                        int removeNum = 0;
                        //一直移除单词，直到次数符合了
                        while (hasWords.get(word) > allWords.get(word)) {
                            String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                            int v = hasWords.get(firstWord);
                            hasWords.put(firstWord, v - 1);
                            removeNum++;
                        }
                        num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                        i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                        break;
                    }
                //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                //然后刚好就移动到了单词后边）
                } else {
                    hasWords.clear();
                    i = i + num * wordLen;
                    num = 0;
                    break;
                }
                num++;
            }
            if (num == wordNum) {
                res.add(i);

            }
            //出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
            if (num > 0 && !hasRemoved) {
                String firstWord = s.substring(i, i + wordLen);
                int v = hasWords.get(firstWord);
                hasWords.put(firstWord, v - 1);
                num = num - 1;
            }

        }

    }
    return res;
}

}
