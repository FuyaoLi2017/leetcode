/*
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
*/

// DP solution
public class Solution {
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words, new Comparator<String>() {
            public int compare (String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for (int i = 0; i < words.length; i++) {
            if (canForm(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }

        return result;
    }

    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
	boolean[] dp = new boolean[word.length() + 1];
	dp[0] = true;
	for (int i = 1; i <= word.length(); i++) {
	    for (int j = 0; j < i; j++) {
		if (!dp[j]) continue;
		if (dict.contains(word.substring(j, i))) {
		    dp[i] = true;
		    break;
		}
	    }
	}
	return dp[word.length()];
    }
}

// Trie
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
            List<String> res = new ArrayList<String>();
            if (words == null || words.length == 0) {
                return res;
            }
            TrieNode root = new TrieNode();
            for (String word : words) { // construct Trie tree
                if (word.length() == 0) {
                    continue;
                }
                addWord(word, root);
            }
            for (String word : words) { // test word is a concatenated word or not
                if (word.length() == 0) {
                    continue;
                }
                if (testWord(word.toCharArray(), 0, root, 0)) {
                    res.add(word);
                }
            }
            return res;
        }
        public boolean testWord(char[] chars, int index, TrieNode root, int count) { // count means how many words during the search path
            TrieNode cur = root;
            int n = chars.length;
            for (int i = index; i < n; i++) {
                if (cur.sons[chars[i] - 'a'] == null) {
                    return false;
                }
                if (cur.sons[chars[i] - 'a'].isEnd) {
                    if (i == n - 1) { // no next word, so test count to get result. should already add 1 to count
                        return count >= 1;
                    }
                    if (testWord(chars, i + 1, root, count + 1)) {
                        return true;
                    }
                }
                cur = cur.sons[chars[i] - 'a'];
            }
            return false;
        }
        public void addWord(String str, TrieNode root) {
            char[] chars = str.toCharArray();
            TrieNode cur = root;
            for (char c : chars) {
                if (cur.sons[c - 'a'] == null) {
                    cur.sons[c - 'a'] = new TrieNode();
                }
                cur = cur.sons[c - 'a'];
            }
            cur.isEnd = true;
        }
    }
    class TrieNode {
        TrieNode[] sons;
        boolean isEnd;
        public TrieNode() {
            sons = new TrieNode[26];
        }
}
