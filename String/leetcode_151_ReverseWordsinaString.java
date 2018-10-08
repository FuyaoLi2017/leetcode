/*

Given an input string, reverse the string word by word.

Example:

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.
*/
// my solution, using regex
public class Solution {
    public String reverseWords(String s) {
        s.trim();
        String[] input = s.split("\\s+");
        StringBuilder res = new StringBuilder();
        for (int i = input.length - 1; i >= 0; i--) {
            res.append(input[i]);
            res.append(" ");
        }
        String result = res.toString();
        return result.trim();
    }
}

// an high vote answer, similar ideas
String[] parts = s.trim().split("\\s+");
String out = "";
for (int i = parts.length - 1; i > 0; i--) {
    out += parts[i] + " ";
}
return out + parts[0];
/*
Something in the lines of

myString.split("\\s+");
This groups all white spaces as a delimiter.

So if I have the string:

"Hello[space][tab]World"

This should yield the strings "Hello" and "World" and omit the empty space between the [space] and the [tab].

As VonC pointed out, the backslash should be escaped, because Java would first try to escape the string to a special character, and send that to be parsed. What you want, is the literal "\s", which means, you need to pass "\\s". It can get a bit confusing.

The \\s is equivalent to [ \\t\\n\\x0B\\f\\r]
*/
