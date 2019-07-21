/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

// my solution
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        // we need to take care of the duplicates
        int pos1 = -1;
        int pos2 = -1;
        int shortestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                pos1 = i;
                if (pos2 != -1) shortestDistance = Math.min(shortestDistance, pos1 - pos2);
            }
            if (word.equals(word2)) {
                pos2 = i;
                if (pos1 != -1) shortestDistance = Math.min(shortestDistance, pos2 - pos1);
            }
        }
        return shortestDistance;
    }
}
