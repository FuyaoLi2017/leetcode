/*

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
  public ListNode selectionSort(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode first = head;
    while (first.next != null) {
      ListNode min = first.next;
      ListNode cursor = first.next;
      // find the minimum number
      while (cursor != null) {
        if (cursor.value < min.value) {
          min = cursor;
        }
        cursor = cursor.next;
      }
      // update the value if needed
      if (first.value > min.value) {
        int temp = min.value;
        min.value = first.value;
        first.value = temp;
      }
      first = first.next;
    }
    return head;
  }
}
