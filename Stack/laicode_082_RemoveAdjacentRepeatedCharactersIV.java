/*
Repeatedly remove all adjacent, repeated characters in a given string from left to right.

No adjacent characters should be identified in the final string.

Examples

"abbbaaccz" → "aaaccz" → "ccz" → "z"
"aabccdc" → "bccdc" → "bdc"
*/
public class Solution {
  public String deDup(String input) {
    Deque<Character> stack = new ArrayDeque<>();
    char[] array = input.toCharArray();
    int f = 0;
    // 这种对状态做判断的就是用while
    while (f < array.length) {
      char c = array[f];
      if (stack.size() > 0 && c == stack.peek()) {
        while (f < array.length && c == array[f]) {  //注意此处必须加 f < array.length, 否则加的超过界限的话就会array[f] out of bound
          f++;
        }
        stack.pop();
      } else {
        stack.push(array[f]);
        f++;
      }
    }

    char[] res = new char[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--) {
      res[i] = stack.pop();
    }
    return new String(res);
  }
}

// more concise solution using two pointers
public class Solution {
  public String deDup(String input) {
    if (input == null || input.length() < 2) return input;
    char[] array = input.toCharArray();
    int end = 0;
    for (int i = 1; i <array.length; i++) {
      // if the stack is empty(when end = -1) or there is no duplicate chars
      // we are able to push the character into the stack
      if (end == -1 || array[i] != array[end]) {
        array[++end] = array[i];
      } else {
        // otherwise, we need to pop the top element by end--
        // and ignore all the consecutive duplicate chars
        end--;
        while (i + 1 < array.length && array[i] == array[i + 1]) {
          i++;
        }
      }
    }
    return new String(array, 0, end + 1);
  }
}
