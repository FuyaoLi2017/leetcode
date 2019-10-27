/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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
     public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
         if (root == null) {
             return new ArrayList<List<Integer>>();
         }
         // use a deque structure to reverse part of the treenode sequence
         // deque can poll elements from the front, also from the back
         Deque<TreeNode> deque = new LinkedList<>();
         List<List<Integer>> res = new ArrayList<>();
         deque.offerFirst(root);
         // root is level 0
         int layer = 0;
         while (!deque.isEmpty()) {
             int size = deque.size();
             List<Integer> current = new ArrayList<>();
             for (int i = 0; i < size; i++) {
                 if (layer == 0) {
                     // at even layer, from right to left, normal sequence
                     TreeNode temp = deque.pollLast();
                     current.add(temp.val);
                     // first, offer the left number
                     if (temp.left != null) {
                         deque.offerFirst(temp.left);
                     }
                     // then, offer the right number
                     if (temp.right != null) {
                         deque.offerFirst(temp.right);
                     }
                 } else {
                     // at odd layer, poll elements from another side
                     TreeNode temp = deque.pollFirst();
                     current.add(temp.val);
                     // first, offer the right number
                     if (temp.right != null) {
                         deque.offerLast(temp.right);
                     }
                     // then offer the left number
                     if (temp.left != null) {
                         deque.offerLast(temp.left);
                     }
                 }
             }
             res.add(current);
             layer = 1 - layer;
         }
         return res;
     }
 }


 // use recusion to build new ArrayList
 class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root)
    {
        List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);
        return sol;
    }

    private void travel(TreeNode curr, List<List<Integer>> sol, int level)
    {
        if(curr == null) return;

        if(sol.size() <= level)
        {
            List<Integer> newLevel = new LinkedList<>();
            sol.add(newLevel);
        }

        List<Integer> collection  = sol.get(level);
        if(level % 2 == 0) collection.add(curr.val);
        else collection.add(0, curr.val);

        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
}
