/*
Insert a value in a sorted linked list.

Examples

L = null, insert 1, return 1 -> null
L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
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
  public ListNode insert(ListNode head, int value) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode newNode = new ListNode(value);
        if (head == null) {
          return newNode;
        }

        while (cur.next != null && cur.next.value < value) {   // 注意一定要把next记住，否则会插入到想插入的后面的位置
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
        return dummy.next;
    }
}
