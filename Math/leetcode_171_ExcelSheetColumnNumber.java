// my solution
class Solution {
    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int res = 0;
        for(int i = 0; i < length; i++){
          // m to the n th power, is Math.pow(m, n)
            res += (chars[i]-'A'+1) * Math.pow(26, length-i-1);
        }
        return res;
    }
}

// a concise solution
class Solution {
    public int titleToNumber(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); result = result * 26 + (s.charAt(i) - 'A' + 1), i++);
        return result;
    }
}
