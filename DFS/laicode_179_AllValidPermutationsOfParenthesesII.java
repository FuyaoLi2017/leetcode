/*
Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.

Assumptions

l, m, n >= 0
Examples

l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]
*/
// my Solutionpublic class Solution {
  public List<String> validParentheses(int l, int m, int n) {
    Deque<String> stack = new ArrayDeque<>();
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int[][] count = new int[3][2];
    count[0][0] = l;
    count[0][1] = l;
    count[1][0] = m;
    count[1][1] = m;
    count[2][0] = n;
    count[2][1] = n;
    int length = (l + m + n) * 2;
    helper(res, stack, sb, count, length);
    return res;
  }

  private void helper(List<String> res, Deque<String> stack, StringBuilder sb, int[][] count, int length) {
    if (sb.length() == length) {
      res.add(sb.toString());
      return;
    }

    if (count[0][0] > 0) {
      count[0][0]--;
      stack.push("(");
      helper(res, stack, sb.append("("), count, length);
      sb.deleteCharAt(sb.length() - 1);
      stack.pop();
      count[0][0]++;
    }

    if (count[1][0] > 0) {
      count[1][0]--;
      stack.push("<");
      helper(res, stack, sb.append("<"), count, length);
      sb.deleteCharAt(sb.length() - 1);
      stack.pop();
      count[1][0]++;
    }

    if (count[2][0] > 0) {
      count[2][0]--;
      stack.push("{");
      helper(res, stack, sb.append("{"), count, length);
      sb.deleteCharAt(sb.length() - 1);
      stack.pop();
      count[2][0]++;
    }

    if (count[0][1] > count[0][0] && stack.peek().equals("(")) {
      count[0][1]--;
      String s = stack.pop();
      helper(res, stack, sb.append(")"), count, length);
      sb.deleteCharAt(sb.length() - 1);
      stack.push(s);
      count[0][1]++;
    }

    if (count[1][1] > count[1][0] && stack.peek().equals("<")) {
      count[1][1]--;
      String s = stack.pop();
      helper(res, stack, sb.append(">"), count, length);
      sb.deleteCharAt(sb.length() - 1);
      stack.push(s);
      count[1][1]++;
    }

    if (count[2][1] > count[2][0] && stack.peek().equals("{")) {
      count[2][1]--;
      String s = stack.pop();
      helper(res, stack, sb.append("}"), count, length);
      sb.deleteCharAt(sb.length() - 1);
      stack.push(s);
      count[2][1]++;
    }
  }
}

// laicode answer, a more concise version
public class Solution {
  private static final char[] PS = new char[] {'(', ')', '<', '>', '{', '}'};
  public List<String> validParentheses(int l, int m, int n) {
    int[] remain = new int[] {l, l, m, m, n, n};
    int targetLen = 2 * (l + m + n);
    StringBuilder cur = new StringBuilder();
    Deque<Character> stack = new LinkedList<>();
    List<String> result = new ArrayList<>();
    helper(cur, stack, remain, targetLen, result);
    return result;
  }

  private void helper(StringBuilder cur, Deque<Character> stack, int[] remain, int targetLen, List<String> result) {
    if (cur.length() == targetLen) {
      result.add(cur.toString());
      return;
    }
    for (int i = 0; i < remain.length; i++) {
      if (i % 2 == 0) {
        if (remain[i] > 0) {
          cur.append(PS[i]);
          stack.offerFirst(PS[i]);
          remain[i]--;
          helper(cur, stack, remain, targetLen, result);
          cur.deleteCharAt(cur.length() - 1);
          stack.pollFirst();
          remain[i]++;
        }
      } else {
        if (!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
          cur.append(PS[i]);
          stack.pollFirst();
          remain[i]--;
          helper(cur, stack, remain, targetLen, result);
          cur.deleteCharAt(cur.length() - 1);
          stack.offerFirst(PS[i - 1]);
          remain[i]++;
        }
      }
    }
  }
}
