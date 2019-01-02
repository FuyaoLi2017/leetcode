/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
*/
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 //HashSet
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }
}

//Floyd's Tortoise and Hare
public class Solution {
    private ListNode getIntersect(ListNode head){
        ListNode tortoise = head;
        ListNode hare = head;
        // find the intersect
        while(hare != null && hare.next != null){
            tortoise = tortoise.next;
            hare = hare.next.next;
            if(tortoise == hare){
                return tortoise;
            }
        }
        return null;
    }
    public ListNode detectCycle(ListNode head){
        if(head == null){
            return null;
        }
        ListNode intersect = getIntersect(head);
        // use a node at first and a node at the intersection
        // all move forward till they meet
        // we will get the final result
        if(intersect == null){
            return null;
        }
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while(ptr1 != ptr2){
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }
}
