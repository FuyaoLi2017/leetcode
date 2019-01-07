/*
Given a linked list, reverse alternate nodes and append at the end.

Examples:

Input List:    1 -> 2 -> 3 -> 4 -> 5 -> 6

Output List: 1 -> 3 -> 5 -> 6 -> 4 -> 2

Input List:    1 -> 2 -> 3 -> 4 -> 5

Output List: 1 -> 3 -> 5 -> 4 -> 2
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
  public ListNode reverseAlternate(ListNode head) {
    // (1) extract the list nodes with even index
    // (2) reverse the extracted list
    // (3) connect the list
    if (head == null || head.next == null || head.next.next == null)
      return head;
    ListNode dummy = new ListNode(0);
    // cursor for even node
    ListNode even = dummy;
    // cursor for odd node
    ListNode odd = head;
    while (odd.next != null && odd.next.next != null) {
      ListNode temp = odd.next;
      odd.next = odd.next.next;
      even.next = temp;
      // advance the even cursor
      even = even.next;
      // advance the odd cursor
      odd = odd.next;
    }
    even.next = null;
    ListNode evenHalf = dummy.next;
    ListNode reverse = reverse(evenHalf);

    // handle the total number of list is odd number
    if (odd.next != null) {
      odd = odd.next;
    }

    odd.next = reverse;
    return head;
  }

  // reverse a linked list
  private ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }
}
