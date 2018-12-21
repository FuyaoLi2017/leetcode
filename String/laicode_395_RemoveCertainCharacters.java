/*

*/

// not constant space
public class Solution {
  public String remove(String input, String t) {
    if (input == null || input.length() == 0)
      return input;
    StringBuilder sb = new StringBuilder();
    char[] array = t.toCharArray();
    Set<Character> set = new HashSet<>();
    for (char c : array) {
      set.add(c);
    }
    for (int i = 0; i < input.length(); i++) {
      if (!set.contains(input.charAt(i))) {
        sb.append(input.charAt(i));
      }
    }
    return sb.toString();
  }
}

// constant space, two pointers
public class Solution {
  public String remove(String input, String t) {
    char[] array = input.toCharArray();

    Set<Character> set = new HashSet<>();
    for (int i = 0; i < t.length(); i++) {
      set.add(t.charAt(i));
    }

    int slow = 0;
    for (int fast = 0; fast < array.length; fast++) {
      if (!set.contains(array[fast])) {
        array[slow++] = array[fast];
      }
    }
    return new String(array, 0, slow);
  }
}
