public class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i)
            for (int j = 1; Math.max(j, i) <= n - i - j; ++j)
                if (isValid(i, j, num)) return true;
        return false;
    }
    private boolean isValid(int i, int j, String num) {
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        String sum;
        Long x1 = Long.parseLong(num.substring(0, i));
        Long x2 = Long.parseLong(num.substring(i, i + j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 = x2 + x1;
            x1 = x2 - x1;
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
}

// DFS
public class Solution {
    public boolean isAdditiveNumber(String s) {
        int n = s.length();
        for (int i=1; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                long a = parse(s.substring(0, i));
                long b = parse(s.substring(i, j));
                if (a == -1 || b == -1) continue;
                if (dfs(s.substring(j), a, b))   return true;
            }
        }
        return false;
    }

    boolean dfs(String s, long a, long b) {
        if (s.length() == 0)    return true;

        for (int i=1; i<=s.length(); i++) {
            long c = parse(s.substring(0, i));
            if (c == -1)    continue;
            if (c-a == b && dfs(s.substring(i), b, c)) {
                return true;
            }
        }
        return false;
    }

    long parse(String s) {
        if (!s.equals("0") && s.startsWith("0"))    return -1;
        long result = 0;
        try {
            result = Long.parseLong(s);
        } catch (NumberFormatException e) {
            return -1;
        }
        return result;
    }
}
