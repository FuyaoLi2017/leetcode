/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
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

 // a concise solution
 class Solution {
    public int sumNumbers(TreeNode root) {
	return sum(root, 0);
    }

    public int sum(TreeNode n, int s){
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s*10 + n.val;
        // just return one side, since the other side will be 0
        return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
    }
}

 // my solution
class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        // using dfs to traverse the tree
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, root, new ArrayList<>());
        // get all numbers
        int sum = 0;
        for (List<Integer> list : result) {
            int currentNum = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                currentNum += list.get(i);
                currentNum *= 10;
            }
            currentNum += list.get(list.size() - 1);
            sum += currentNum;
        }
        return sum;
    }

    private void dfs(List<List<Integer>> result, TreeNode root, List<Integer> current) {
        if (root.left == null && root.right == null) {
            current.add(root.val);
            result.add(new ArrayList<>(current));
            // remember to backtrack when you need to return
            current.remove(current.size() - 1);
            return;
        } else if (root.left != null && root.right != null) {
            current.add(root.val);
            dfs(result, root.left, current);
            dfs(result, root.right, current);
            current.remove(current.size() - 1);
        } else if (root.left != null) {
            current.add(root.val);
            dfs(result, root.left, current);
            current.remove(current.size() - 1);
        } else {
            current.add(root.val);
            dfs(result, root.right, current);
            current.remove(current.size() - 1);
        }
    }
}
