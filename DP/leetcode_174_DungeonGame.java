/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
*/
// DP solution
// 思考问题太的角度要对，从问题的结束到开始，这个题目只能bottom up
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] health = new int[m][n];

        health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

        for (int i = m - 2; i >= 0; i--) {
            health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }

        for (int j = n - 2; j >= 0; j--) {
            health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
                int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
                health[i][j] = Math.min(right, down);
            }
        }

        return health[0][0];
    }
}

// recusion with memorization
public class Solution {

    int[][] mem;

    private int helper(int[][] dungeon, int i, int j) {
        if(i>=dungeon.length || j>=dungeon[0].length)
            return Integer.MAX_VALUE;
        if(mem[i][j]>0)
            return mem[i][j];
        int health = Integer.MAX_VALUE;
        health = Math.min(health, helper(dungeon, i+1, j));
        health = Math.min(health, helper(dungeon, i, j+1));
        health = health==Integer.MAX_VALUE ? 1 : health; // this only happens with i, j is P already
        int ret = health>dungeon[i][j] ? (health-dungeon[i][j]) : 1;
        mem[i][j] = ret;
        return ret;
    }

    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length==0)
            return 0;
        mem = new int[dungeon.length][dungeon[0].length];
        return helper(dungeon, 0, 0);
    }
}
