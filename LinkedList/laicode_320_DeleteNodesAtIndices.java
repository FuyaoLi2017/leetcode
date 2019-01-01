/*

Given a linked list and an sorted array of integers as the indices in the list. Delete all the nodes at the indices in the original list.

Examples

1 -> 2 -> 3 -> 4 -> NULL, indices = {0, 3, 5}, after deletion the list is 2 -> 3 -> NULL.

Assumptions

the given indices array is not null and it is guaranteed to contain non-negative integers sorted in ascending order.
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
  public ListNode deleteNodes(ListNode head, int[] indices) {
    // Write your solution here
    if (head == null || indices == null || indices.length == 0) return head;
    ListNode dummy = new ListNode(0);
    ListNode res = dummy;
    int i = 0, j = 0;
    while (head != null) {
      if (j < indices.length && i == indices[j]) {
        j++;
      } else {
        dummy.next = head;
        dummy = dummy.next;
      }
      head = head.next;
      i++;
    }
    // break the tie between the last valid point and the remaining
    dummy.next = null;
    return res.next;
  }
}
/*
i 3
j 3
dummy 3

                       head
[1,    2,    3,    4,    5]

[0 , 3, 4]
*/
