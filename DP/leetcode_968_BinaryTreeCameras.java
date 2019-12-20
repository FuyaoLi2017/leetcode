/*
Given a binary tree, we install cameras on the nodes of the tree.

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.
*/

// Do a direct dfs to consider all cases
class Solution {
    int ans;
    Set<TreeNode> covered;
    public int minCameraCover(TreeNode root) {
        ans = 0;
        covered = new HashSet();
        covered.add(null);

        dfs(root, null);
        return ans;
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            dfs(node.left, node);
            dfs(node.right, node);

            if (par == null && !covered.contains(node) ||
                    !covered.contains(node.left) ||
                    !covered.contains(node.right)) {
                ans++;
                covered.add(node);
                covered.add(par);
                covered.add(node.left);
                covered.add(node.right);
            }
        }
    }
}

// advanced version of dfs
// https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
// Explanation:
// Apply a recusion function dfs.
// Return 0 if it's a leaf.
// Return 1 if it's a parent of a leaf, with a camera on this node.
// Return 2 if it's coverd, without a camera on this node.
//
// For each node,
// if it has a child, which is leaf (node 0), then it needs camera.
// if it has a child, which is the parent of a leaf (node 1), then it's covered.
//
// If it needs camera, then res++ and we return 1.
// If it's covered, we return 2.
// Otherwise, we return 0.
int res = 0;
public int minCameraCover(TreeNode root) {
    return (dfs(root) < 1 ? 1 : 0) + res;
}

public int dfs(TreeNode root) {
    if (root == null) return 2;
    int left = dfs(root.left), right = dfs(root.right);
    if (left == 0 || right == 0) {
        res++;
        return 1;
    }
    return left == 1 || right == 1 ? 2 : 0;
}


// my explanation for dp solution
// https://leetcode.com/problems/binary-tree-cameras/discuss/454479/Explanation-for-DP-solution-in-Java
class Solution {
    public int minCameraCover(TreeNode root) {
        int[] ans = solve(root);
        return Math.min(ans[1], ans[2]);
    }

    // 0: Strict ST; All nodes below this are covered, but not this one
    // 1: Normal ST; All nodes below and incl this are covered - no camera
    // 2: Placed camera; All nodes below this are covered, plus camera here
    public int[] solve(TreeNode node) {
        if (node == null){
            // for initialization
            // the first two values are quite intuitive, we need 0 cameras to realize state 0 or state 1
            // the third value can be any number larger than 0(>=1), it is not necessary to place a camera
            // at null node, we just want to make the cost higher than previous states,
            // as a result, when we do Math.min() in our following steps, this case will be regarded as worse than not placing any camera here(null node)
            return new int[]{0, 0, 1};
        }

        int[] L = solve(node.left);
        int[] R = solve(node.right);

        // minimum camera to guarantee left node to be covered
        int mL12 = Math.min(L[1], L[2]);
        // minimum camera to guarantee right node to be covered
        int mR12 = Math.min(R[1], R[2]);

        // state 0: the children are all state 1, the current should be in state 0
        int d0 = L[1] + R[1];

        // state 1: no camera in current node,
        // but at least one of the children should have a camera to guarantee the state 1
        // select the smaller one
        // for left node: L[2] + mR12, for right node: R[2] + mL12
        int d1 = Math.min(L[2] + mR12, R[2] + mL12);

        // state 2, we will place a camera in current node
        // to optimize the result, we don't need any children to be covered or not,
        // any state should be fine, we just want the least number of cameras
        // for left side: Math.min(L[0], mL12), for right side: Math.min(R[0], mR12)
        int d2 = 1 + Math.min(L[0], mL12) + Math.min(R[0], mR12);

        // return the current layer to parent node
        return new int[]{d0, d1, d2};
    }
}
