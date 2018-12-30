/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
*/
class Solution {
    public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        String str = s.toLowerCase();
        System.out.println(str);
        while (start < end) {
            char head = str.charAt(start);
            char tail = str.charAt(end);
            if (Character.isLetterOrDigit(head) && Character.isLetterOrDigit(tail)) {
                if (head == tail) {
                    start++;
                    end--;
                } else {
                    return false;
                }
            } else if (!Character.isLetterOrDigit(head)) {
                start++;
            } else {
                end--;
            }
        }
        return true;
    }
}
