/*
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

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
public Node connect(Node root) {
    Node level_start = root;
    while (level_start != null) {
        Node cur = level_start;
        while (cur != null) {
            if (cur.left != null) cur.left.next = cur.right;
            if (cur.right != null && cur.next != null) cur.right.next = cur.next.left;
            cur = cur.next;
        }
        level_start = level_start.left;
    }
    return root;
}
// O(1) space, O(n) time

// recusive, 这样的recusion真的非常精髓了
public Node connect(Node root) {
        if (root == null) return null;
        helper(root.left, root.right);
        return root;
    }

    void helper(Node node1, Node node2) {
        if (node1 == null) return;
        node1.next = node2;
        helper(node1.left, node1.right);
        helper(node2.left, node2.right);
        helper(node1.right, node2.left);
}
