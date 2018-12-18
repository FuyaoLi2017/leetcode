/*
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
*/

// the most intuitive way is to use a stack to hold the linked list/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;  // uncertain
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // judge whether it has odd number of list nodes
        if(fast.next == null) {
            slow = slow.next;
        }
        // push the nodes into a stack
        Deque<ListNode> stack = new ArrayDeque<>();
        while (slow != null) {
            stack.offerFirst(slow);
            slow = slow.next;
        }
        // check whether it is the same like the previous
        while (!stack.isEmpty()) {
            if(head.val == stack.pollFirst().val) {
                head = head.next;
            } else {
                return false;
            }
        }
        return true;
    }
}

// a BETTER way, O(n) space and O(1) time
