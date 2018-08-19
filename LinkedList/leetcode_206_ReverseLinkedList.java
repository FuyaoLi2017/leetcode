/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//Iterative method
// using a prev to point at the null at first
// prev move along the linked list to reverse each pointing relationship
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;

        }
        return prev;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
public ListNode reverseList(ListNode head) {
    /* recursive solution */
    return reverseListInt(head, null);
}

private ListNode reverseListInt(ListNode head, ListNode newHead) {
    if (head == null)
        return newHead;
    ListNode next = head.next;
    head.next = newHead;
    return reverseListInt(next, head);
}
}
