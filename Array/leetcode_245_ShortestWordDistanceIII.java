/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “makes”, word2 = “coding”
Output: 1
Input: word1 = "makes", word2 = "makes"
Output: 3
Note:
You may assume word1 and word2 are both in the list.
*/
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        List<int[]> relevant = new ArrayList<>();
        boolean flag = word1.equals(word2) ? true : false;
        for(int i = 0; i < words.length; i++) {
            String current = words[i];
            if (current.equals(word1)) {
                relevant.add(new int[]{i, 1});
            }
            if (!flag && current.equals(word2)) {
                relevant.add(new int[]{i, 2});
            }
        }
        int previous1 = -1;
        int previous2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < relevant.size(); i++) {
            int[] pair = relevant.get(i);
            if (pair[1] == 1) {
                if (previous2 != -1 && !flag) {
                    res = Math.min(res, pair[0]-previous2);
                } else if (previous1 != -1 && flag) {
                    res = Math.min(res, pair[0]-previous1);
                }
                previous1 = pair[0];
            } else {
                previous2 = pair[0];
                if (previous1 != -1) {
                    res = Math.min(res, previous2-previous1);
                }
            }
        }
        return res;
    }
}

// another similar idea
public int shortestWordDistance(String[] words, String word1, String word2) {
    int min = Integer.MAX_VALUE;
    int p1 = -1;
    int p2 = -1;
    boolean same = word1.equals(word2);
    for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1)) {
            if (same) {
                p2 = p1;  // Deal with another pointer too
            }
            p1 = i;
        } else if (words[i].equals(word2)) {
            p2 = i;
        }

        if (p1 != -1 && p2 != -1) {
            min = Math.min(min, Math.abs(p1 - p2));
        }
    }
    return min;
}
