/*
Given a string, find the longest substring without any repeating characters and return the length of it. The input string is guaranteed to be not null.

For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", we should return 4 in this case.
*/
public class Solution {
  public int longest(String input) {
    if (input == null || input.length() == 0) return 0;
    int slow = 0;
    int fast = 0;
    int longest = 0;
    Set<Character> set = new HashSet<>();
    while(fast < input.length()) {
      if (!set.contains(input.charAt(fast))) {
        set.add(input.charAt(fast++));
        longest = Math.max(fast - slow, longest);
      } else {
        set.remove(input.charAt(slow));
        slow++;
      }
    }
    return longest;
  }
}
