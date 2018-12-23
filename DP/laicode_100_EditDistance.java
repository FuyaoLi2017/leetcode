/*

Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.

Assumptions

Both strings are not null
Examples

string one: “sigh”, string two : “asith”

the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).
*/
//my solution using 2D DP
public class Solution {
  public int editDistance(String one, String two) {
    if (one.length() == 0) return two.length();
    if (two.length() == 0) return one.length();

    char[] word1 = one.toCharArray();
    char[] word2 = two.toCharArray();

    int[][] map = new int[one.length() + 1][two.length() + 1];

    for (int i = 0; i < map.length; i++) {
      map[i][0] = i;
    }

    for (int j = 0; j < map[0].length; j++) {
      map[0][j] = j;
    }

    for (int i = 1; i < map.length; i++) {
      for (int j = 1; j < map[0].length; j++) {
        if (word1[i - 1] == word2[j - 1]) {
          map[i][j] = map[i - 1][j - 1];
        } else {
          int replace = map[i - 1][j - 1] + 1;
          int delete = map[i - 1][j] + 1;
          int insert = map[i][j - 1] + 1;
          int temp = Math.min(replace, delete);
          map[i][j] = Math.min(temp, insert);
        }
      }
    }
    return map[word1.length][word2.length];
  }
}
