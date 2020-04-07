/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.
*/
//simple solution without hashmap
public class Solution {
    public boolean isIsomorphic(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            // +1 represents every time the answer passes the above if sentence the value will change once(even in the first character position)
            // or there will be mistake in "ab" "aa" case
            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i + 1;
        }
        return true;
    }
}

// my solution HashMap & HashSet
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s == null | t == null || s.length() != t.length()) return false;
        // Key: character for the first string; Value: character for the second string
        Map<Character, Character> map = new HashMap<>();
        // store the characters has occurred in String t
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(chars[i])) {
                if (set.contains(chart[i]))
                    return false;
                map.put(chars[i], chart[i]);
                set.add(chart[i]);
            } else {
                if (map.get(chars[i]) == chart[i])
                    continue;
                return false;
            }
        }
        return true;
    }
}

// another solution with only HashMap, using containsValue() API, slower solution since containsValue() is a O(n) operation
// total TC is O(n^2)
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s == null || s.length() <= 1) return true;
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for(int i = 0 ; i< s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(map.containsKey(a)){
                 if(map.get(a).equals(b))
                continue;
                else
                return false;
            }else{
                if(!map.containsValue(b))
                map.put(a,b);
                else return false;

            }
        }
        return true;

    }
}

// a good and clear solution using hashmap

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        Map<Character, Integer> mapS = new HashMap<Character, Integer>();
        Map<Character, Integer> mapT = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            int indexS = mapS.getOrDefault(s.charAt(i), -1);
            int indexT = mapT.getOrDefault(t.charAt(i), -1);

            if (indexS != indexT) {
                return false;
            }

            mapS.put(s.charAt(i), i);
            mapT.put(t.charAt(i), i);
        }

        return true;
    }
}
