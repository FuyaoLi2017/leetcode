/*
Write a program to find the node at which the intersection of two singly linked lists begins.
*/

// basic solutions includes traverse and hash table
// a better solution is using two pointers

// when a pointer reaches the end of the linked list, jump to the head of another linkedlist
// in this way, the second traverse, two pointers will reach the same position at the same time
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
