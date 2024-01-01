/**
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.

Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
 */

 // editorial solution, using two pointers
 class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int contentChildren = 0;
        int cookieIndex = 0;
        while (cookieIndex < s.length && contentChildren < g.length) {
            if (s[cookieIndex] >= g[contentChildren]) {
                contentChildren++;
            }
            cookieIndex++;
        }
        return contentChildren;
    }

}


// my solution, slightly slower, avoid repetitive operations
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gCur = 0;
        int sCur = 0;
        int res = 0;
        while (gCur < g.length && sCur < s.length) {
            int greedFactor = g[gCur];
            int cookieSize = s[sCur];

            if (greedFactor > cookieSize) {
                sCur++;
            } else {
                res++;
                gCur++;
                sCur++;
            }
        }
        return res;
    }
}