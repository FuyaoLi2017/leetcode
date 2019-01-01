/*

*/

// my Solution
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        if(head == null) {
            Node res = new Node(insertVal, null);
            res.next = res;
            return res;
        }
        if (head.val == insertVal) {
            Node temp = head.next;
            head.next = new Node(insertVal, temp);
            return head;
        } else {
            Node cur = head.next;
            Node prev = head;
            boolean inserted = false;
            while (cur != head) {
                // figure out the max value in the linked list
                // if the insertVal is larger than max or less than min
                // it should be inserted here
                if (prev.val > cur.val && (insertVal >= prev.val || insertVal <= cur.val)) {
                    prev.next = new Node(insertVal, cur);
                    inserted  = true;
                    break;
                }
                // normal insert
                if (cur.val > insertVal && prev.val <= insertVal) {
                    prev.next = new Node(insertVal, cur);
                    inserted = true;
                    break;
                }
                cur = cur.next;
                prev = prev.next;
            }
            // the last position has been considered (the position before head)
            // two cases:
            // (1) all the number are equals, we can insert anywhere
            // (2) the node should be at the position just before head
            // we all treat it by adding the insertVal in this position
            if (!inserted) {
                prev.next = new Node(insertVal, cur);
                return head;
            }
            return head;
        }
    }
}



// another high vote answer, the same like me
class Solution {
    public Node insert(Node start, int x) {
        // if start is null, create a node pointing to itself and return
        if (start == null) {
            Node node = new Node(x, null);
            node.next = node;
            return node;
        }
        // is start is NOT null, try to insert it into correct position
        Node cur = start;
        while (true) {
            // case 1A: has a tipping point, still climbing
            if (cur.val < cur.next.val) {
                if (cur.val <= x && x <= cur.next.val) { // x in between cur and next
                    insertAfter(cur, x);
                    break;
                }
            // case 1B: has a tipping point, about to return back to min node
            } else if (cur.val > cur.next.val) {
                if (cur.val <= x || x <= cur.next.val) { // cur is the tipping point, x is max or min val
                    insertAfter(cur, x);
                    break;
                }
            // case 2: NO tipping point, all flat
            } else {
                if (cur.next == start) {  // insert x before we traverse all nodes back to start
                    insertAfter(cur, x);
                    break;
                }
            }
            // None of the above three cases met, go to next node
            cur = cur.next;
        }
        return start;
    }

    // insert value x after Node cur
    private void insertAfter(Node cur, int x) {
        cur.next = new Node(x, cur.next);
    }
}
