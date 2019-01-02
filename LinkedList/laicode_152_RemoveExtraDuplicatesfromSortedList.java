/*

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Input:  1->2->3->3->4->4->5

Output: 1->2->5

Input:  1->1->1

Output: NULL
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
 // 需要keep 3个node
public class Solution {
  public ListNode removeDup(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode mid = head;
    ListNode cur = head.next;
    while (cur != null) {
      if (cur.value != mid.value) {
        prev = prev.next;
        mid = mid.next;
        cur = cur.next;
      } else {
        while (cur.value == mid.value) {
          // null check
          if (cur.next == null) {
            prev.next = null;
            return dummy.next;
          }
          cur = cur.next;
          mid = mid.next;
        }
        // exit the while loop when cur.value != mid.value
        // delete these nodes
        cur = cur.next;
        mid = mid.next;
        prev.next = mid;
      }
    }
    return dummy.next;
  }
}
