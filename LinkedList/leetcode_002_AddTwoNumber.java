/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 // 通过这个题目加深对Linked List的理解，赋给的值其实就是一个一个的链接，如果一个node完全没有链接可以指向它，那它就会被回收。
 // 如果需要遍历的辅助cursor，那就需要再赋值一个新的ListNode，对这个链接进行操作，之前的node也不会丢失
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode first = l1, second = l2, curr = dummy;
        int carry = 0;
        while(first != null || second != null){
            int temp = 0;
            if(first != null && second != null){
                temp = first.val + second.val+ carry;
            }else if(first != null && second == null){
                temp = first.val + carry;
            }else{
                temp = second.val + carry;
            }
            curr.next = new ListNode(temp % 10);
            curr = curr.next;
            carry = temp / 10;
            if (first != null) first = first.next;
            if (second != null) second = second.next;
        }
        if(carry > 0)
            curr.next = new ListNode(carry);
        return dummy.next;
    }
}
