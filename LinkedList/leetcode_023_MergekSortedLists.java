/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.val < o2.val) return -1;
            if (o1.val > o2.val) return 1;
            return 0;
        });
        for (ListNode node : lists) {
            if (node != null) {      // 对于这些位置都要检查null
                pq.offer(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            if (node.next != null) {
                pq.offer(node.next);
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
