/*

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/

//DFS
// (1) the traverse of the tree is NOT standard pre-order traverse. It checks the RIGHT node first and then the LEFT
// (2) the line to check currDepth == result.size() makes sure the first element of that level will be added to the result list
// (3) if reverse the visit order, that is first LEFT and then RIGHT, it will return the left view of the tree.
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }
}

// bfs
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // we can use level order traversal to check the last element in the tree
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = q.poll();
                if (current.left != null) q.offer(current.left);
                if (current.right != null) q.offer(current.right);
                if (i == size - 1) {
                    result.add(current.val);
                }
            }
        }
        return result;
    }
}
