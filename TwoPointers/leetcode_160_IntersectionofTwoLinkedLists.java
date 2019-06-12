/*
Write a program to find the node
at which the intersection of two singly linked lists begins.
*/
// my solution
// 我的解法是用的two pointer思想，但是是直接放的listnode，过于heavy weight，应该有更好的two pointers解法
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> checked = new HashSet<>();
        while (headA != null && headB != null) {
            if (headA == headB) return headA;
            if (checked.contains(headB)) {
                return headB;
            } else if(checked.contains(headA)) {
                return headA;
            }
            checked.add(headA);
            checked.add(headB);
            headA = headA.next;
            headB = headB.next;
        }

        while (headA != null) {
            if (checked.contains(headA)) {
                return headA;
            }
            headA = headA.next;
        }
        while (headB != null) {
            if (checked.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}


// a better two pointer solution
// https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!

public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //boundary check
    if(headA == null || headB == null) return null;

    ListNode a = headA;
    ListNode b = headB;

    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;
    }

    return a;
}
