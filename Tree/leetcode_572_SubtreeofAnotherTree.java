/*
Given two non-empty binary trees s and t, check whether tree t has exactly
the same structure and node values with a subtree of s.
A subtree of s is a tree consists of a node in s and all of this node's descendants.
The tree s could also be considered as a subtree of itself.
https://leetcode.com/problems/subtree-of-another-tree/
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// solution 1: using preorder to construct a tree, need to consider the null cases
public class Solution {
    HashSet < String > trees = new HashSet < > ();
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tree1 = preorder(s, true);
        String tree2 = preorder(t, true);
        return tree1.indexOf(tree2) >= 0;
    }
    public String preorder(TreeNode t, boolean left) {
        if (t == null) {
            if (left)
                return "lnull";
            else
                return "rnull";
        }
        // add # to distinguish between numbers
        return "#"+t.val + " " +preorder(t.left, true)+" " +preorder(t.right, false);
    }
}

// solution 2: best solution, use recusion to check left and right
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    public boolean equals(TreeNode x, TreeNode y) {
        if (x == null && y == null) { // both is null
            return true;
        }else if (x == null || y == null) { // one is null, the other is not
            return false;
        }
        return x.val == y.val && equals(x.left, y.left) && equals(x.right, y.right);
    }

    public boolean traverse(TreeNode s, TreeNode t) {
        return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }
}


 // my solution, too much logic
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // do a preorder traverse for TreeNode s
        if (s == null) return false;
        if (s.val == t.val) {
            boolean currentNode = checkSame(s, t);
            boolean leftChild = false, rightChild = false;
            if (s.left != null) {
                leftChild = isSubtree(s.left, t);
            }
            if (s.right != null) {
                rightChild = isSubtree(s.right, t);
            }
            return currentNode || leftChild || rightChild;
        } else {
            boolean leftChild = false, rightChild = false;
            if (s.left != null) {
                leftChild = isSubtree(s.left, t);
            }
            if (s.right != null) {
                rightChild = isSubtree(s.right, t);
            }
            return leftChild || rightChild;
        }

    }

    private boolean checkSame(TreeNode s, TreeNode t) {
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.offer(s);
        Queue<TreeNode> q2 = new LinkedList<>();
        q2.offer(t);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode current1 = q1.poll();
            TreeNode current2 = q2.poll();
            if (current1.val == current2.val) {
                if (current1.left != null && current2.left != null) {
                    q1.offer(current1.left);
                    q2.offer(current2.left);
                } else if (current1.left != null || current2.left != null) {
                    return false;
                }
                if (current1.right != null && current2.right != null) {
                    q1.offer(current1.right);
                    q2.offer(current2.right);
                } else if (current1.right != null || current2.right != null) {
                    return false;
                }
            } else {
                return false;

            }
        }

        if(!q1.isEmpty() || !q2.isEmpty()) {
            return false;
        }
        return true;
    }
}
