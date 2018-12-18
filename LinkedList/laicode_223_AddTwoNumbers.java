/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)

Output: 7 -> 0 -> 8
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
 // 逻辑判断少，相对更快
public class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null)
      return l2;
    if (l2 == null)
      return l1;
    ListNode firstCur = l1;
    ListNode secondCur = l2;
    ListNode res = new ListNode(0);
    ListNode cur = res;
    int carry = 0;
    while(firstCur != null && secondCur != null) {
      int sum = firstCur.value + secondCur.value + carry;   // 注意sum的处理！
      carry = sum / 10;
      cur.next = new ListNode(sum % 10);
      cur = cur.next;
      firstCur = firstCur.next;
      secondCur = secondCur.next;
    }

    while (firstCur != null) {
      int sum = firstCur.value + carry;
      carry = sum / 10;
      cur.next = new ListNode(sum % 10);
      cur = cur.next;
      firstCur = firstCur.next;
    }

    while (secondCur != null) {
      int sum = secondCur.value + carry;
      carry = sum / 10;
      cur.next = new ListNode(sum % 10);
      cur = cur.next;
      secondCur = secondCur.next;
    }
    if (carry == 1)                // 注意进位！
      cur.next = new ListNode(1);

    return res.next;
  }
}

// more concise solution
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
