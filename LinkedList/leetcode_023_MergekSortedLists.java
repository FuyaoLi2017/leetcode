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

// use merge sort
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        for (int i = 1; i < lists.length; i *= 2)
            for (int j = i; j < lists.length; j += 2 * i)
                lists[j - i] = merge(lists[j - i], lists[j]);
        return lists[0];
    }
    private ListNode merge(ListNode one, ListNode two) {
        ListNode start = new ListNode(0), cur = start;
        while (one != null && two != null) {
            if (one.val > two.val) {
                cur.next = two;
                two = two.next;
            }
            else {
                cur.next = one;
                one = one.next;
            }
            cur = cur.next;
        }
        if (one == null)
            cur.next = two;
        else
            cur.next = one;
        return start.next;
    }
}

// more concise merge sort
class Solution {
    public static ListNode mergeKLists(ListNode[] lists){
    return partiton(lists,0,lists.length-1);
}

public static ListNode partiton(ListNode[] lists,int s,int e){
    if(s==e)  return lists[s];
    if(s<e){
        int q=(s+e)/2;
        ListNode l1=partiton(lists,s,q);
        ListNode l2=partiton(lists,q+1,e);
        return merge(l1,l2);
    }else
        return null;
}

//This function is from Merge Two Sorted Lists.
public static ListNode merge(ListNode l1,ListNode l2){
    if(l1==null) return l2;
    if(l2==null) return l1;
    if(l1.val<l2.val){
        l1.next=merge(l1.next,l2);
        return l1;
    }else{
        l2.next=merge(l1,l2.next);
        return l2;
    }
}
}
