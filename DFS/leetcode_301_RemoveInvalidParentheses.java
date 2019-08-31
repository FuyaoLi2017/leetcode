/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
*/

// my DFS solution
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        // traverse the string to find the misplace parentheses
        char[] chs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int misplaceLeft = 0;
        int misplaceRight = 0;

        for (int i = 0; i < chs.length; i++) {
            char cur = chs[i];
            if (cur == '(') {
                stack.push(cur);
            }
            if (cur == ')') {
                if (stack.size() > 0) {
                    stack.pop();
                } else {
                    misplaceRight++;
                }
            }
        }
        misplaceLeft = stack.size();

        // result and visited array function
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(result, sb, s, 0, 0, misplaceLeft, misplaceRight , 0);
        return result;
    }

    private void dfs(List<String> result, StringBuilder sb, String s,
                     int left, int right, int misplaceLeft, int misplaceRight, int index) {
        if (left < right) return;
        if (index == s.length()){
            if (misplaceLeft == 0 && misplaceRight == 0) {
                String curResult = sb.toString();
                if (!result.contains(curResult)) result.add(curResult);
            }
            return;
        }

        char cur = s.charAt(index);
        // if it is not a Parentheses
        if (cur != '(' && cur != ')') {
            sb.append(cur);
            dfs(result, sb, s, left, right, misplaceLeft, misplaceRight, index+1);
            sb.deleteCharAt(sb.length() - 1);
        }
        // if it is a left Parentheses
        if (cur == '(') {
            // ignore the left Parentheses
            if (misplaceLeft > 0) {
                dfs(result, sb, s, left, right, misplaceLeft-1, misplaceRight, index+1);
            }
            // count this left Parentheses
            sb.append('(');
            dfs(result, sb, s, left+1, right, misplaceLeft, misplaceRight, index+1);
            sb.deleteCharAt(sb.length() - 1);
        }

        // if it is a right Parentheses
        if (cur == ')') {
            // ignore the right Parentheses
            if (misplaceRight > 0) {
                dfs(result, sb, s, left, right, misplaceLeft, misplaceRight-1, index+1);
            }
            // count this right Parentheses
            sb.append(')');
            dfs(result, sb, s, left, right+1, misplaceLeft, misplaceRight, index+1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}


// a better solution
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }
}
