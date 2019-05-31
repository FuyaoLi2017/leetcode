/*
Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
*/
// iterative
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode curP = root;
        TreeLinkNode nextDummyHead = new TreeLinkNode(0);
        TreeLinkNode p = nextDummyHead;
        while (curP != null) {
            if (curP.left != null) {
                p.next = curP.left;
                p = p.next;
            }
            if (curP.right != null) {
                p.next = curP.right;
                p = p.next;
            }
            if (curP.next != null) {
                curP = curP.next;
            }
            else {
                curP = nextDummyHead.next;
                nextDummyHead.next = null;
                p = nextDummyHead;
            }
        }
    }
}

//Another version with two while loops, but with same performance, and the meaning is clearer...
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode p = dummy;
        TreeLinkNode head = root;
        while (head != null) { //if the head of the traverse layer is not null, then traverse that layer...
            TreeLinkNode node = head;
            while (node != null) {
                if (node.left != null) {
                    p.next = node.left;
                    p = p.next;
                }
                if (node.right != null) {
                    p.next = node.right;
                    p = p.next;
                }
                node = node.next;
            }
            //after traversed to the end of current layer, move to the next layer
            head = dummy.next;
            dummy.next = null;
            p = dummy;
        }
    }
}

// recusive
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/38030/Accepted-Java-recursive-solution
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        // link root's child nodes
        link(root);

        // before we recurse to the next level
        // make sure all the child nodes of the nodes at current level are linked
        TreeLinkNode curr = root.next;
        while (curr != null) {
            link(curr);
            curr = curr.next;
        }

        connect(root.left);
        connect(root.right);
    }

    // helper function
    // link root node's left and right nodes
    void link(TreeLinkNode root) {
        if (root == null) return;

        if (root.left != null) {
            root.left.next = root.right != null ? root.right : getNext(root);
        }

        if (root.right != null) {
            root.right.next = getNext(root);
        }
    }

    // get the left most node at the next level
    TreeLinkNode getNext(TreeLinkNode node) {
        TreeLinkNode next = node.next;

        while (next != null) {
            if (next.left != null) return next.left;
            if (next.right != null) return next.right;
            next = next.next;
        }

        return null;
    }
}
