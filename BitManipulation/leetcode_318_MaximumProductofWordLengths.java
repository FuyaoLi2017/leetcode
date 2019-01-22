/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
*/
class Solution {
    public static int maxProduct(String[] words) {
	if (words == null || words.length == 0)
		return 0;
	int len = words.length;
	int[] value = new int[len];
	for (int i = 0; i < len; i++) {
		String tmp = words[i];
		value[i] = 0;
		for (int j = 0; j < tmp.length(); j++) {
            // 用value这个int值的后26个数位代表每个位置的字母是不是出现过
			value[i] |= 1 << (tmp.charAt(j) - 'a');
		}
	}
	int maxProduct = 0;
	for (int i = 0; i < len; i++)
		for (int j = i + 1; j < len; j++) {
			if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct))
				maxProduct = words[i].length() * words[j].length();
		}
	return maxProduct;
}
}

// my solution, use a very large matrix
class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length < 2)
            return 0;
        int[][] matrix = new int[words.length][26];
        // construct the matrix
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                matrix[i][c - 'a']++;
            }
        }
        // compare the strings
        int global = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                boolean flag = true;
                for (int k = 0; k < 26; k++) {
                    if (matrix[i][k] == 0 || matrix[j][k] == 0) continue;
                    else {
                        flag = false;
                        break;
                    }
                }
                if (flag) global = Math.max(global, words[i].length() * words[j].length());
            }
        }
        return global;
    }
}
