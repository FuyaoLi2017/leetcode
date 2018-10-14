/*
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
*/
// jitao and my solution
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() <= 2) return s == null ? 0 : s.length();
        int maxLength = 1;
        int i = 0, j = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(i), 1);
        while (j < s.length()) {
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
            if (map.size() <= 2) {
                maxLength = Math.max(maxLength, j - i + 1);
            } else {
                while (i < j && map.size() > 2) {
                    int num = map.get(s.charAt(i));
                    if (num == 1) {
                        map.remove(s.charAt(i));
                    } else {
                        map.put(s.charAt(i), num - 1);
                    }
                    i++;
                }
            }
            j++;
        }
        return maxLength;
    }
}

// one high vote answer
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() < 1) return 0;
        HashMap<Character,Integer> index = new HashMap<Character,Integer>();
        int lo = 0;
        int hi = 0;
        int maxLength = 0;
        while(hi < s.length()) {
            if(index.size() <= 2) {  // push the index to the string
                char c = s.charAt(hi);
                index.put(c, hi);
                hi++;    // here increase the hi once more, therefore, we can update maxLength without plusing one at the end (hi - lo)
            }
            if(index.size() == 3) {
                int leftMost = s.length();
                for(int i : index.values()) {
                    leftMost = Math.min(leftMost,i);
                }
                char c = s.charAt(leftMost);
                index.remove(c);
                lo = leftMost+1;
            }
            maxLength = Math.max(maxLength, hi-lo);
        }
        return maxLength;
    }
}
