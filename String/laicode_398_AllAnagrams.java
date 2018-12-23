/*
Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

Assumptions

s is not null or empty.
l is not null.
Examples

l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
*/
// my solution
public class Solution {
  public List<Integer> allAnagrams(String sh, String lo) {
        List<Integer> res = new ArrayList<>();
        if (sh.length() == 0 || lo.length() < sh.length()) {
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < sh.length(); i++) {
            map.put(sh.charAt(i), map.getOrDefault(sh.charAt(i), 0) + 1);
        }

        // use a sliding window to scan if the index qualify the conditions
        int size = map.size();    // number of unique characters
        int count = 0;          // memorize how many character has already match the number in sh
        for (int i = 0; i < sh.length(); i++) {
            if (map.containsKey(lo.charAt(i))) {
                map.put(lo.charAt(i), map.get(lo.charAt(i)) - 1);
            }
        }
        // calculate how many number has already matched
        for (Character c : map.keySet()) {
            if (map.get(c) == 0)
                count++;
        }
//        System.out.println("size number:" + size);
//        System.out.println("count number:" + count);
        if (count == size) {
            res.add(0);
        }
        // sliding the window afterwards and find out the available index
        for (int i = 0; i < lo.length() - sh.length(); i++) {
            // delete the char at i, and add the char at i + sh.length()
            Character charBefore = lo.charAt(i);
            if (map.containsKey(charBefore)) {
                if (map.get(lo.charAt(i)) == 0) count -= 1;
                map.put(charBefore, map.get(lo.charAt(i)) + 1);
                if (map.get(charBefore) == 0) count += 1;
            }
            Character charAfter = lo.charAt(i+ sh.length());
            if (map.containsKey(charAfter)) {
                if (map.get(charAfter) == 0) count -= 1;
                map.put(charAfter, map.get(charAfter) - 1);
                if (map.get(charAfter) == 0) count += 1;
            }
            if (count == size) {
                res.add(i + 1);
            }
        }
        return res;
    }
}

// answer given by laicode
public class Solution {
  public List<Integer> allAnagrams(String s, String l) {
    List<Integer> result = new ArrayList<>();
    if (l.length() == 0) {
      return result;
    }
    if (s.length() > l.length()) {
      return result;
    }
    Map<Character, Integer> map = countMap(s);
    int match = 0;
    for (int i = 0; i < l.length(); i++) {
      char tmp = l.charAt(i);
      Integer count = map.get(tmp);
      if (count != null) {
        map.put(tmp, count - 1);
        if (count == 1) {
          match++;
        }
      }
      // handle the leftmost character at the previous silding window
      if (i >= s.length()) {
        tmp = l.charAt(i - s.length());
        count = map.get(tmp);
        if (count != null) {
          map.put(tmp, count + 1);
          if (count == 0) {
            match--;
        }
      }
    }
      if (match == map.size()) {
      result.add(i - s.length() + 1);
      }
    }
    return result;
  }

  private Map<Character, Integer> countMap(String s) {
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for (char ch : s.toCharArray()) {
      Integer count = map.get(ch);
      if (count == null) {
        map.put(ch, 1);
      } else {
        map.put(ch, count + 1);
      }
    }
    return map;
  }
}
