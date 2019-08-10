/*
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();


        int[][] map = new int[chs1.length+1][chs2.length+1];

        for (int i = 0; i < map.length; i++) {
            map[i][0] = i;
        }

        for (int j = 0; j < map[0].length; j++) {
            map[0][j] = j;
        }

        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                if (chs1[i-1] == chs2[j-1]) {
                    map[i][j] = map[i-1][j-1];
                } else {
                    int replace = map[i-1][j-1] + 1;
                    int delete = map[i-1][j] + 1;
                    int insert = map[i][j-1] + 1;
                    map[i][j] = Math.min(replace, Math.min(delete, insert));
                }
            }
        }
        return map[chs1.length][chs2.length];
    }
}
