/*
Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.



Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
*/
// my iterative solution
// https://leetcode.com/problems/swap-nodes-in-pairs/discuss/320527/My-iterative-Java-solution
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = head;
        dummy.next = head.next;

        while (prev != null && prev.next != null) {
            ListNode nextPrev = prev.next.next;
            prev.next.next = prev;
            // decid when to move to the next step
            if (nextPrev != null && nextPrev.next != null) {
                prev.next = nextPrev.next;
            } else {
                prev.next = nextPrev;
            }

            prev = nextPrev;
        }

        return dummy.next;
    }
}

// recursion
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}

// a iterative
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode newHead = head.next;
        while (cur != null && cur.next != null) {
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
            if (cur != null && cur.next != null) tmp.next = cur.next;
        }
        return newHead;
    }
}
