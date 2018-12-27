/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
*/
public class Solution {
    public List<String> findStrobogrammatic(int n) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        List<String> result = new ArrayList<String>();
        char[] buffer = new char[n];
        dfs(n, 0, buffer, result, map);
        return result;
    }

    private void dfs(int n, int index, char[] buffer, List<String> result, Map<Character, Character> map) {
        if (n == 0) {
            return;
        }
        if (index == (n + 1) / 2) {
            result.add(String.valueOf(buffer));
            return;
        }
        for (Character c : map.keySet()) {
            if (index == 0 && n > 1 && c == '0') {  // first digit cannot be '0' when n > 1
                continue;
            }
            if (index == n / 2 && (c == '6' || c == '9')) {   // mid digit cannot be '6' or '9' when n is odd
                continue;
            }
            buffer[index] = c;
            buffer[n - 1 - index] = map.get(c);
            dfs(n, index + 1, buffer, result, map);
        }
    }
}
