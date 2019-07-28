class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }
}

/**
* not return type, use DFS in the backtracking manner
* https://blog.csdn.net/wonner_/article/details/80373871
* DFS的思想，先加上左边的括号，然后再加上右边的括号
* 因此先（opne < max）， 然后（close < open）
*/
