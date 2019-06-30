/*
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/

// require O(1) space, using merge sort
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // use merge sort to sort the list
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prevMid = findMidPoint(head);
        ListNode secondHead = prevMid.next;
        prevMid.next = null;
        ListNode newFirst = sortList(head);
        ListNode newSecond = sortList(secondHead);
        return mergeList(newFirst, newSecond);
    }

    // find the listNode before the midPoint
    private ListNode findMidPoint(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, prev = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        return prev;
    }

    // merge two lists
    private ListNode mergeList(ListNode first, ListNode second) {
        ListNode dummy = new ListNode(0);
        ListNode cursor = dummy;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                cursor.next = first;
                first = first.next;
                cursor = cursor.next;
            } else {
                cursor.next = second;
                second = second.next;
                cursor = cursor.next;
            }
        }
        if (first != null) cursor.next = first;
        if (second != null) cursor.next = second;
        return dummy.next;
    }
}


// a high vote merge sort solution, exactly the same solution like mine
public class Solution {

  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null)
      return head;

    // step 1. cut the list to two halves
    ListNode prev = null, slow = head, fast = head;

    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    prev.next = null;

    // step 2. sort each half
    ListNode l1 = sortList(head);
    ListNode l2 = sortList(slow);

    // step 3. merge l1 and l2
    return merge(l1, l2);
  }

  ListNode merge(ListNode l1, ListNode l2) {
    ListNode l = new ListNode(0), p = l;

    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        p.next = l1;
        l1 = l1.next;
      } else {
        p.next = l2;
        l2 = l2.next;
      }
      p = p.next;
    }

    if (l1 != null)
      p.next = l1;

    if (l2 != null)
      p.next = l2;

    return l.next;
  }

}
