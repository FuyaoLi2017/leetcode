/*
Reverse the words in a sentence and truncate all heading/trailing/duplicate space characters.

Examples

“ I  love  Google  ” → “Google love I”

Corner Cases

If the given string is null, we do not need to do anything.
*/
// 去掉多余开头尾部空格 + reverse String, 然后内部reverse string
public class Solution {
  public String reverseWords(String input) {
    if (input == null || input.length() == 0) {
      return input;
    }
    int end = 0;
    char[] array = input.toCharArray();
    for (int i = 0; i < array.length; i++) {
      char c = array[i];
      if (c != ' ') {
        array[end] = c;
        end++;
      } else {
        if(i > 0 && array[i - 1] != ' ') {
          array[end] = ' ';
          end++;
        }
      }
    }
    String trim = "";
    if (array[end - 1] == ' ') {
      trim = new String(array, 0, end - 1);
    } else {
      trim = new String(array, 0, end);
    }

    char[] reverse = trim.toCharArray();
    reverse(reverse, 0, trim.length() - 1);
    int start = 0;
    for (int i = 0; i < reverse.length; i++) {
      if (reverse[i] != ' ' && (i == 0 || reverse[i - 1] == ' ')) {
        start = i;
      }
      if (reverse[i] != ' ' && (i == reverse.length - 1 || reverse[i + 1] == ' ')) {
        reverse(reverse, start, i);
      }
    }
    return new String(reverse);
  }

  private void reverse(char[] array, int left, int right) {
    while (left < right) {
      char temp = array[left];
      array[left] = array[right];
      array[right] = temp;
      left++;
      right--;
    }
  }
}
