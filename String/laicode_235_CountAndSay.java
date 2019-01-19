/*
Given a sequence of number: 1, 11, 21, 1211, 111221, …

The rule of generating the number in the sequence is as follow:

1 is "one 1" so 11.

11 is "two 1s" so 21.

21 is "one 2 followed by one 1" so 1211.

Find the nth number in this sequence.

Assumptions:

n starts from 1, the first number is "1", the second number is "11"
*/

// My solution
public class Solution {
  public String countAndSay(int n) {
    if (n < 1)
      return "";
    return countAndSayHelper("1", n);
  }

  private String countAndSayHelper(String input, int n) {
    if (n == 1) return input;
    char[] array = input.toCharArray();
    StringBuilder sb = new StringBuilder();
    int curCount = 1;
    for (int i = 1; i < array.length; i++) {
      if (Integer.valueOf(array[i]) == Integer.valueOf(array[i - 1])) {
        curCount++;
      } else {
        sb.append(curCount).append(array[i - 1]);
        curCount = 1;
      }
    }
    sb.append(curCount).append(array[array.length - 1]);
    return countAndSayHelper(sb.toString(), n - 1);
  }
}
