/*
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.



Example:

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
*/

// recusive, zhiying qian
// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/discuss/157606/Java-Recursive-Solution
public Node flatten(Node head) {
    flattentail(head);
    return head;
}

// flattentail: flatten the node "head" and return the tail in its child (if exists)
// the tail means the last node after flattening "head"

// Five situations:
// 1. null - no need to flatten, just return it
// 2. no child, no next - no need to flatten, it is the last element, just return it
// 3. no child, next - no need to flatten, go next, 2,3 can be combined
// 4. child, no next - flatten the child and done
// 5. child, next - flatten the child, connect it with the next, go next

private Node flattentail(Node head) {
    if (head == null) return head; // CASE 1
    if (head.child == null) {
        if (head.next == null) return head; // CASE 2
        return flattentail(head.next); // CASE 3
    }
    else {
        Node child = head.child;
        head.child = null;
        Node next = head.next;
        Node childtail = flattentail(child);
        head.next = child;
        child.prev = head;
        if (next != null) { // CASE 5
            childtail.next = next;
            next.prev = childtail;
            return flattentail(next);
        }
        return childtail; // CASE 4
    }
}

// another recusive
class Solution {
    public Node flatten(Node head) {
        Node curr = head;
        recur(curr);
        return head;
    }

   Node recur(Node node) {
        Node prev = node;
        while(node != null) {
            prev = node;

            // Continue iterating if there is no child node.
            if (node.child == null) {
                node = node.next;
                continue;
            }

            // At this point, the current node has a child.
            Node next = node.next; // Save the next node, will be used, when we backtrack.
            node.next = node.child; // Point the currenr node's next pointer to child node.
            node.child.prev = node; // Update the prev pointer of child node.

            // Recurse over the child node.
            Node ret = recur(node.child);
            node.child = null;

            if (next != null) {
                ret.next = next;
                next.prev = ret;
                node = next;
            } else {
                node = ret;
            }
        }
        return prev;
    }
}


// iterative
class Solution {
    public Node flatten(Node head) {
        if( head == null) return head;
	// Pointer
        Node p = head;
        while( p!= null) {
            /* CASE 1: if no child, proceed */
            if( p.child == null ) {
                p = p.next;
                continue;
            }
            /* CASE 2: got child, find the tail of the child and link it to p.next */
            Node temp = p.child;
            // Find the tail of the child
            while( temp.next != null )
                temp = temp.next;
            // Connect tail with p.next, if it is not null
            temp.next = p.next;
            if( p.next != null )  p.next.prev = temp;
            // Connect p with p.child, and remove p.child
            p.next = p.child;
            p.child.prev = p;
            p.child = null;
        }
        return head;
    }
}


// iterative
public Node flatten(Node head) {
    //We can use a stack to store the next node when the current node has a child, so we can go back to it when we reach to the end of the current list.
    Stack<Node> stack = new Stack<>();

	// Keep the head pointer and traverse the list using current node (curNode)
    Node curNode = head;


    while(curNode != null){

        if(curNode.child != null){
		    // if the current node has a child, then add the next node to the stack
            stack.add(curNode.next);
			// point the next node to the child
            curNode.next = curNode.child;
			// remove the child's pointer
            curNode.child = null;
        }

        // Determine which is the next node, if reached to the end of the current list and the stack is NOT empty, then pop the lastest node to be the next.
        Node next = (curNode.next == null && !stack.isEmpty()) ? stack.pop() : curNode.next;

        if(next != null){
		    // make sure all the pointers are correct
            next.prev = curNode;
            curNode.next = next;
        }

        curNode = next;
    }
    return head;
}
