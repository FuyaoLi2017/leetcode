/*
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
*/
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode oddEvenList(ListNode head) {
        // (1) extract the list nodes with even index
        // (2) connect the list
        if (head == null || head.next == null || head.next.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        // cursor for even node
        ListNode even = dummy;
        // cursor for odd node
        ListNode odd = head;
        while (odd != null && odd.next != null) {
            ListNode temp = odd.next;
            odd.next = odd.next.next;
            even.next = temp;
            // advance the even cursor
            even = even.next;
            // advance the odd cursor
            // don't advance when odd.next is null
            // or we cannot link the odd and even list together
            if (odd.next != null) {
                odd = odd.next;
            }
        }
        // cut the connection between the even list and odd list!!!!
        even.next = null;
        ListNode evenHalf = dummy.next;

        ListNode cur = evenHalf;

        odd.next = evenHalf;
        return head;
    }
}
