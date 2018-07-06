/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
public ListNode mergeKLists(ListNode[] lists) {
	if (lists == null || lists.length == 0)
		return null;
    return mergeKLists(lists, 0, lists.length - 1);
}
public ListNode mergeKLists(ListNode[] lists, int start, int end) {
	if (start == end) {
		return lists[start];
	} else if (start < end){
		int mid = (end - start) / 2 + start;
		ListNode left = mergeKLists(lists, start, mid);
		ListNode right = mergeKLists(lists, mid + 1, end);
		return mergeTwoLists(left, right);
	} else {
		return null;
	}
}

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head=null;
        ListNode former=null;
        while (l1!=null&&l2!=null) {
            if (l1.val>l2.val) {
                if (former==null) former=l2; else former.next=l2;
                if (head==null) head=former; else former=former.next;
                l2=l2.next;
            } else {
                if (former==null) former=l1; else former.next=l1;
                if (head==null) head=former; else former=former.next;
                l1=l1.next;
            }
        }
        if (l2!=null) l1=l2;
        former.next=l1;
        return head;

    }
}
