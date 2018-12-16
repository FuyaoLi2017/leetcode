/*
Given an original string input, and two strings S and T, replace all occurrences of S in input with T.

Assumptions

input, S and T are not null, S is not empty string
Examples

input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
input = "dodododo", S = "dod", T = "a", input becomes "aoao"
*/
// method 1
public class Solution {
  public String replace(String input, String s, String t) {
    char[] array = input.toCharArray();
    if (s.length() >= t.length()) {
      return replaceShorter(array, s, t);
    }
    return replaceLonger(array, s, t);
  }

  public String replaceShorter(char[] input, String s, String t) {
    int slow = 0;
    int fast = 0;
    while (fast < input.length) {
      if (fast <= input.length - s.length() && equalSubstring(input, fast, s)) {
        copySubstring(input, slow, t);
        slow += t.length();
        fast += s.length();
      } else {
        input[slow++] = input[fast++];
      }
    }
    return new String(input, 0, slow);
  }

  public String replaceLonger(char[] input, String s, String t) {
    ArrayList<Integer> matches = getAllMatches(input, s);
    char[] result = new char[input.length + matches.size() * (t.length() - s.length())];
    // fast and slow pointers both from right to left direction.
    int lastIndex = matches.size() - 1;
    int fast = input.length - 1;
    int slow = result.length - 1;
    while (fast >= 0) {
      if (lastIndex >= 0 && fast == matches.get(lastIndex)) {
        copySubstring(result, slow - t.length() + 1, t);
        slow -= t.length();
        fast -= s.length();
        lastIndex--;
      } else {
        result[slow--] = input[fast--];
      }
    }
    return new String(result);
  }

  private boolean equalSubstring(char[] input, int fromIndex, String s) {
    for (int i = 0; i < s.length(); i++) {
      if (input[fromIndex + i] != s.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  private void copySubstring(char[] result, int fromIndex, String t) {
    for (int i = 0; i < t.length(); i++) {
      result[fromIndex + i] = t.charAt(i);
    }
  }

  // return the last position of the matched pattern
  private ArrayList<Integer> getAllMatches(char[] input, String s) {
    ArrayList<Integer> matches = new ArrayList<>();
    int i = 0;
    while (i <= input.length - s.length()) {
      if (equalSubstring(input, i, s)) {
        matches.add(i + s.length() - 1);
        i += s.length();
      } else {
        i++;
      }
    }
    return matches;
  }
}

// method 2, using StringBuilder
public class Solution {
  public String replace(String input, String source, String target) {
    StringBuilder sb = new StringBuilder();
    int fromIndex = 0;
    int matchIndex = input.indexOf(source, fromIndex);
    while (matchIndex != -1) {
      sb.append(input, fromIndex, matchIndex).append(target);
      fromIndex = matchIndex + source.length();
      matchIndex = input.indexOf(source, fromIndex);
    }
    sb.append(input, fromIndex, input.length());
    return sb.toString();
  }
}
