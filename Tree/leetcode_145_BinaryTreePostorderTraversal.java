/*
Given a binary tree, return the postorder traversal of its nodes' values.
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
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private void postOrder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);
    }
}

// iterative, reverse the preorder, laioffer solution 1
class Solution {
    // post-order is reverse order of pre-order with traversing
    // right subtree before traversing the left subtree
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> preOrder = new LinkedList<TreeNode>();
        preOrder.offerFirst(root);
        while (!preOrder.isEmpty()) {
            TreeNode current = preOrder.pollFirst();
            // conduct the result for the special pre-order traversal.
            // we will reverse it afterwards
            result.add(current.val);
            // in pre-order the right subtree will be traversed before the
            // left subtree so pushing left child first
            if (current.left != null) {
                preOrder.offerFirst(current.left);
            }
            if (current.right != null) {
                preOrder.offerFirst(current.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}

// similar ways, another implementation
class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> output = new LinkedList<>();
    if (root == null) {
      return output;
    }

    stack.add(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pollLast();
      output.addFirst(node.val);
      if (node.left != null) {
        stack.add(node.left);
      }
      if (node.right != null) {
        stack.add(node.right);
      }
    }
    return output;
  }
}

// real way to do post order, not use reversing
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        // to record the previous node on the way of DFS so that we can determine the direction
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            // if we are going down, either left/right direction.
            if (prev == null || cur == prev.left || cur == prev.right) {
                // if we can still go down, try to go left first
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    // if we can not go either way, meaning cur is a leaf node.
                    stack.pollFirst();
                    result.add(cur.val);
                }
            } else if (prev == cur.right || prev == cur.left && cur.right == null) {
                // if we are going from right side or going up from the left side
                // but we can't go right afterwards
                stack.pollFirst();
                result.add(cur.val);
            } else {
                // otherwise, we are going up from the left side and we can go down right side
                stack.offerFirst(cur.right);
            }
            prev = cur;
        }
        return result;
    }
}
