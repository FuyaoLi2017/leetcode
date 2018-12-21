/*
Given a string, remove all leading/trailing/duplicated empty spaces.

Assumptions:

The given string is not null.
Examples:

“  a” --> “a”
“   I     love MTV ” --> “I love MTV”
*/
public class Solution {
  public String removeSpaces(String input) {
    if (input.isEmpty()) {
      return input;
    }
    char[] array = input.toCharArray();
    int end = 0;
    for (int i = 0; i < input.length(); i++) {
      if (array[i] == ' ' && (i == 0 || array[i - 1] == ' ')) {
        continue;
      }
      array[end++] = array[i];
    }
    if (end > 0 && array[end - 1] == ' ') {
      return new String(array, 0, end - 1);   //注意这个位置是exclude,对应的是index
    }
    return new String(array, 0, end);   // 这个地方一定要标注出来end
  }
}

// move the pointers, two pointers
