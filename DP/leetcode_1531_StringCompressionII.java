/**
 * Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

Notice that in this problem, we are not adding '1' after single characters.

Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.

Find the minimum length of the run-length encoded version of s after deleting at most k characters.
 */

 class Solution {
      private Map<Integer, Integer> memo = new HashMap<>();
      Set<Integer> add = Set.of(1, 9, 99);
      public int getLengthOfOptimalCompression(String s, int k) {
            return dp(s, 0, (char) ('a' + 26), 0, k);
      }
      
      private int dp(String s, int idx, char lastChar, int lastCharCount, int k) {
            if (k < 0) {
                  return Integer.MAX_VALUE / 2;
            }
            
            if (idx == s.length()) {
                  return 0;
            }
            
            int key = idx * 101 * 27 * 101 + (lastChar - 'a') * 101 * 101 + lastCharCount * 101 + k;
            
            if (memo.containsKey(key)) {
                  return memo.get(key);
            }
            
            int keepChar;
            int deleteChar = dp(s, idx + 1, lastChar, lastCharCount, k - 1);
            if (s.charAt(idx) == lastChar) {
                  keepChar = dp(s, idx + 1, lastChar, lastCharCount + 1, k) + (add.contains(lastCharCount) ? 1 : 0);
            } else {
                  keepChar = dp(s, idx + 1, s.charAt(idx), 1, k) + 1;
            }
            int res = Math.min(keepChar, deleteChar);
            memo.put(key, res);
            
            return res;
      }
}


// python 3
// using @lru_cache to cache result https://docs.python.org/3/library/functools.html
class Solution:
    def getLengthOfOptimalCompression(self, s: str, k: int) -> int:
        @lru_cache(None)
        def dp(idx, last_char, last_char_count, k):
            if k < 0: 
                return float('inf')
            if idx == n: 
                return 0
                
            delete_char = dp(idx + 1, last_char, last_char_count, k - 1)
            if s[idx] == last_char:
                keep_char = dp(idx + 1, last_char, last_char_count + 1, k) + (last_char_count in [1, 9, 99])
            else:
                keep_char = dp(idx + 1, s[idx], 1, k) + 1
            
            return min(keep_char, delete_char)
        
        n = len(s)
        return dp(0, "", 0, k)