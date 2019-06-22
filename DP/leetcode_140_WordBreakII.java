/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
*/

// recusion with memorization
// tc: O(n^3), sc: O(n^3)
// DFS with memorization 的经典中的经典例题
public class Solution {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictionary = new HashSet<>(wordDict);
        return word_Break(s, wordDictionary, 0);
    }
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordDictionary, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDictionary.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDictionary, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }
}
