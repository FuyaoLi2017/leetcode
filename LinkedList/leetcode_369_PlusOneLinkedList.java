/*
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example :

Input: [1,2,3]
Output: [1,2,4]
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 // find the last node that is not nine, two pointer solution
public class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastNotNine = dummy, node = head;

        while (node != null) {
            if (node.val != 9) {
                lastNotNine = node;
            }
            node = node.next;
        }
        lastNotNine.val++;
        node = lastNotNine.next;
        while (node != null) {
            node.val = 0;
            node = node.next;
        }
        return dummy.val == 1 ? dummy : dummy.next;
    }
}

// my stack solution
*/
class Solution {
   public ListNode plusOne(ListNode head) {
       if(head == null) return null;
       Stack<ListNode> s = new Stack<>();
       ListNode dummy = new ListNode(0);
       dummy.next = head;
       s.push(dummy);

       while(head.next != null) {
           s.push(head);
           head = head.next;
       }

       if(head.val < 9) {
           head.val += 1;
           return dummy.next;
       }


       head.val = 0;
       boolean carry = true;
       while(!s.isEmpty() && carry){
           ListNode cur = s.pop();
           if(cur.val == 9) {
               cur.val = 0;
           } else {
               cur.val++;
               carry = false;
           }
       }

       // dummy might contain the carry
       return dummy.val == 0 ? dummy.next : dummy;
   }
}
