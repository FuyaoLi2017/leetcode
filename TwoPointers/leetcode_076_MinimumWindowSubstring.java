/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/
// 经典的sliding window
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        int slow = 0, fast = 0, index = 0, minLen = Integer.MAX_VALUE, matchCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            Integer count = map.get(ch);
            if (count == null) map.put(ch, 1);
            else map.put(ch, count + 1);
        }
        for (; fast < s.length(); fast++) {
            // update the map and matchCount
            Integer count = map.get(s.charAt(fast));
            if (count == null) {
                continue;
            }
            map.put(s.charAt(fast), count - 1);
            if (count == 1) matchCount++;

            // find the the possible minlength
            while (matchCount == map.size()) {
                // update the minLength
                if (fast - slow + 1 < minLen) {
                    minLen = fast - slow + 1;
                    index = slow;
                }

                Integer leftmost = map.get(s.charAt(slow++));
                if (leftmost == null) {
                    // slow pointer has moved to the next
                    continue;
                } else {
                    map.put(s.charAt(slow - 1), leftmost + 1);
                }
                // break from the loop
                if (leftmost == 0) matchCount--;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(index, index + minLen);
    }
}

// 答案2, more neat and clear
public class Solution {
public String minWindow(String s, String t) {
    if(s == null || s.length() < t.length() || s.length() == 0){
        return "";
    }
    HashMap<Character,Integer> map = new HashMap<Character,Integer>();
    for(char c : t.toCharArray()){
        if(map.containsKey(c)){
            map.put(c,map.get(c)+1);
        }else{
            map.put(c,1);
        }
    }
    int left = 0;
    int minLeft = 0;
    int minLen = s.length()+1;
    int count = 0;
    // outside loop traverse all possiblities of String s with start value at index 0
    // count is used to ensure all the letters are included in the range of the chosen string
    for(int right = 0; right < s.length(); right++){
        if(map.containsKey(s.charAt(right))){
            map.put(s.charAt(right),map.get(s.charAt(right))-1);
            if(map.get(s.charAt(right)) >= 0){
                count ++;
            }
            // in the while loop, the inner if part shift the left edge of the string
            // if the string still contains enough letters the hold all string t letters, it will stay in the while and undate the minlen
            // or, the code might jump out of the while loop to increase the right edge to find a potential smaller minlen
            while(count == t.length()){
                if(right-left+1 < minLen){
                    minLeft = left;
                    minLen = right-left+1;
                }
                if(map.containsKey(s.charAt(left))){
                    map.put(s.charAt(left),map.get(s.charAt(left))+1);
                    if(map.get(s.charAt(left)) > 0){
                        count --;
                    }
                }
                left ++ ;
            }
        }
    }
    if(minLen>s.length())
    {
        return "";
    }
    // return the index of the desired substring
    return s.substring(minLeft,minLeft+minLen);
}
}
