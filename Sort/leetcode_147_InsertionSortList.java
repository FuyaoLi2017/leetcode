/*
Sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list


Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
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
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode temp = null, prev = null;
        while(cur != null && cur.next != null){
            if(cur.val <= cur.next.val){       //如果按顺序排列，将cur往后移动
                cur = cur.next;
            } else {
                //把cur.next (temp)这个值跳过，cur直接先连接到cur.next.next
                temp = cur.next;
                cur.next = temp.next;
                // 从dummy开始往后遍历寻找insert的位置
                prev = dummy;
                while (prev.next.val <= temp.val){
                    prev = prev.next;
                }
                //将temp那个存的值插入prev后面的位置
                temp.next = prev.next; //确定temp的后继节点
                prev.next = temp;      //确定temp的前驱节点

            }
        }
        return dummy.next;
    }
}
