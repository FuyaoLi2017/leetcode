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

// huahua 的更general的做法
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> results = new LinkedList<>();
        if (s == null) {
            return results;
        }
        int n = s.length();
        int l = 0;
        int r = 0;
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') {
                l++;
            }
            if (s.charAt(i) == ')') {
                if (l > 0) {
                    l--;
                } else {
                    r++;
                }
            }
        }
        System.out.println(l + " " + r);
        helper(new StringBuilder(s), 0, l, r, results);
        return results;
    }

    private void helper(StringBuilder sb, int index, int l, int r, List<String> results){
        if (l == 0 && r == 0){
            if (isValid(sb)) {
                results.add(sb.toString());
            }
            return;
        }

        for (int i = index; i < sb.length(); i++) {
            if (i > index && sb.charAt(i) == sb.charAt(i - 1)) {
                continue;
            }

            if (sb.charAt(i) == '(' && l > 0) {
                sb.deleteCharAt(i);
                helper(sb, i, l - 1, r, results);
                sb.insert(i, '(');
            }

            if (sb.charAt(i) == ')' && r > 0) {
                sb.deleteCharAt(i);
                helper(sb, i, l, r - 1, results);
                sb.insert(i, ')');
            }
        }
    }

    private boolean isValid(StringBuilder sb){
        int left = 0, right = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                left++;
            }
            if (sb.charAt(i) == ')') {
                right++;
            }

            if (i < sb.length() - 1 && left < right) {
                return false;
            }
        }
        return left == right;
    }
}
