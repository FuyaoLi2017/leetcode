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
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head; //the list and head will be in the same place after the final loop
            sum /= 10;
        }
        // if list.val == 0, the highest digit is 0
        return list.val == 0 ? list.next : list;
    }
}
