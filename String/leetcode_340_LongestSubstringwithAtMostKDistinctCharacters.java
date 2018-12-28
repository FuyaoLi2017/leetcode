/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

*/
// my solution
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // we can use a sliding window
        // key is the character, value is the number of appearance
        int globalMax = 0;
        int curCount = 0;
        int leftBound = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            curCount++;
            if (map.size() <= k) {
                globalMax = Math.max(globalMax, curCount);
            } else {
                while(map.size() > k) {
                    char leftMost = s.charAt(leftBound);
                    if (map.get(leftMost) > 1) {
                        map.put(s.charAt(leftBound), map.get(s.charAt(leftBound)) - 1);
                    } else {
                        map.remove(leftMost);
                    }
                    curCount--;
                    leftBound++;
                }
            }
        }
        return globalMax;
    }
}

// a solution using treemap and HashMappublic class Solution
/*
Solving the problem with O(n) time is not enough, some interviewer may require this solution as a followup. Instead of recording each char's count, we keep track of char's last occurrence. If you consider k as constant, it is also a O(n) algorithm.

inWindow keeps track of each char in window and its last occurrence position

lastOccurrence is used to find the char in window with left most last occurrence. A better idea is to use a PriorityQueue, as it takes O(1) to getMin, However Java's PQ does not support O(logn) update a internal node, it takes O(n). TreeMap takes O(logn) to do both getMin and update.
Every time when the window is full of k distinct chars, we lookup TreeMap to find the one with leftmost last occurrence and set left bound j to be 1 + first to exclude the char to allow new char coming into window.
*/
        public int lengthOfLongestSubstringKDistinct(String str, int k) {
            if (str == null || str.isEmpty() || k == 0) {
                return 0;
            }
            TreeMap<Integer, Character> lastOccurrence = new TreeMap<>();
            Map<Character, Integer> inWindow = new HashMap<>();
            int j = 0;
            int max = 1;
            for (int i = 0; i < str.length(); i++) {
                char in = str.charAt(i);
                while (inWindow.size() == k && !inWindow.containsKey(in)) {
                    int first = lastOccurrence.firstKey();
                    char out = lastOccurrence.get(first);
                    inWindow.remove(out);
                    lastOccurrence.remove(first);
                    j = first + 1;
                }
                //update or add in's position in both maps
                if (inWindow.containsKey(in)) {
                    lastOccurrence.remove(inWindow.get(in));
                }
                inWindow.put(in, i);
                lastOccurrence.put(i, in);
                max = Math.max(max, i - j + 1);
            }
            return max;
        }
    }
