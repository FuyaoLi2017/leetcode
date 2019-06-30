/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 /*
 I first use loop to add two number,but the sum might overflow
 If I use double to represent
 I cannot compel the double value to integer
 */
 //the stack mehthod is a good solution
 //first in, last out, realize the reverse by such data structure
 //head -> list, they are forward to the head of the List
 class Solution {
     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         Deque<Integer> s1 = new ArrayDeque<>();
         Deque<Integer> s2 = new ArrayDeque<>();

         while (l1 != null) {
             s1.push(l1.val);
             l1 = l1.next;
         }
         while (l2 != null) {
             s2.push(l2.val);
             l2 = l2.next;
         }
         int sum = 0;
         ListNode list = new ListNode(0);
         // 这个位置用 OR 的逻辑更加简洁
         while (!s1.isEmpty() || !s2.isEmpty()) {
             if (!s1.isEmpty()) sum += s1.pop();
             if (!s2.isEmpty()) sum += s2.pop();
             list.val =  sum % 10;
             ListNode head = new ListNode(sum / 10);
             head.next = list;
             list = head;
             sum /= 10;
         }
         return list.val == 0 ? list.next : list;
     }
 }
