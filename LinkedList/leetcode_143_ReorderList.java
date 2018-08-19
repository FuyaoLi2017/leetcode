public void reorderList(ListNode head) {
            if(head==null||head.next==null) return;

            //Find the middle of the list
            ListNode p1=head;
            ListNode p2=head;
            while(p2.next!=null&&p2.next.next!=null){
                p1=p1.next;
                p2=p2.next.next;
            }

            //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
            ListNode preMiddle=p1;
            ListNode preCurrent=p1.next;
            while(preCurrent.next!=null){
              // 注意顺序，先连接后面的，防止成环
                ListNode current=preCurrent.next;
                preCurrent.next=current.next;
                current.next=preMiddle.next;
                preMiddle.next=current;
            }

            // Start reorder one by one
            // 1->2->3->6->5->4 to 1->6->2->3->5->4 to 1->6->2->5->3->4
            p1=head;
            p2=preMiddle.next;
            while(p1!=preMiddle){
              // make the transformation to move the preMiddle.next to the head.next and keep other sequence not change
                preMiddle.next=p2.next;
                p2.next=p1.next;
                p1.next=p2;
                p1=p2.next;
                p2=preMiddle.next;
            }
        }


// using functions to encapsulate
public class Solution {

  public void reorderList(ListNode head) {
    if (head == null || head.next == null)
        return;

    // step 1. cut the list to two halves
    // prev will be the tail of 1st half
    // slow will be the head of 2nd half
    ListNode prev = null, slow = head, fast = head, l1 = head;

    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    prev.next = null;

    // step 2. reverse the 2nd half
    ListNode l2 = reverse(slow);

    // step 3. merge the two halves
    merge(l1, l2);
  }

  ListNode reverse(ListNode head) {
    ListNode prev = null, curr = head, next = null;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }

  void merge(ListNode l1, ListNode l2) {
    while (l1 != null) {
      ListNode n1 = l1.next, n2 = l2.next;
      l1.next = l2;

      if (n1 == null)
        break;

      l2.next = n1;
      l1 = n1;
      l2 = n2;
    }
  }

}
