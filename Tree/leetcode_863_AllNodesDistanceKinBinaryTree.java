/*
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
*/

// 转化为图
class Solution {
    Map<TreeNode, List<TreeNode>> map = new HashMap();
//here can also use Map<TreeNode, TreeNode> to only store the child - parent mapping, since parent-child mapping is inherent in the tree structure

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
         List<Integer> res = new ArrayList<Integer> ();
        if (root == null || K < 0) return res;
        buildMap(root, null);
        if (!map.containsKey(target)) return res;
        Set<TreeNode> visited = new HashSet<TreeNode>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(target);
        visited.add(target);
        while (!q.isEmpty()) {
            int size = q.size();
            if (K == 0) {
                for (int i = 0; i < size ; i++) res.add(q.poll().val);
                return res;
            }
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                for (TreeNode next : map.get(node)) {
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    q.add(next);
                }
            }
            K--;
        }
        return res;
    }

    private void buildMap(TreeNode node, TreeNode parent) {
        if (node == null) return;
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<TreeNode>());
            if (parent != null)  { map.get(node).add(parent); map.get(parent).add(node) ; }
            buildMap(node.left, node);
            buildMap(node.right, node);
        }
    }
}


// 转换成图的问题， 带上parent pointer, we can also use hashmap to remember it
// https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/solution/
class Solution {
    private GNode targetGNode;

    private class GNode {
        TreeNode node;
        GNode parent, left, right;
        GNode (TreeNode node) {
            this.node = node;
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<Integer> ();
        if (root == null || K < 0) return res;
        cloneGraph(root, null, target);
        if (targetGNode == null) return res;
        Set<GNode> visited = new HashSet<GNode>();
        Queue<GNode> q = new LinkedList<GNode>();
        q.add(targetGNode);
        visited.add(targetGNode);
        while (!q.isEmpty()) {
            int size = q.size();
            if (K == 0) {
                for (int i = 0; i < size ; i++) res.add(q.poll().node.val);
                return res;
            }
            for (int i = 0; i < size; i++) {
                GNode gNode = q.poll();
                if (gNode.left != null && !visited.contains(gNode.left)) { visited.add(gNode.left); q.add(gNode.left); }
                if (gNode.right != null && !visited.contains(gNode.right)) { visited.add(gNode.right); q.add(gNode.right); }
                if (gNode.parent != null && !visited.contains(gNode.parent)) { visited.add(gNode.parent); q.add(gNode.parent); }
            }
            K--;
        }
        return res;
    }

    private GNode cloneGraph(TreeNode node, GNode parent, TreeNode target) {
        if (node == null) return null;
        GNode gNode = new GNode(node);
        if (node == target) targetGNode = gNode;
        gNode.parent = parent;
        gNode.left = cloneGraph(node.left, gNode, target);
        gNode.right = cloneGraph(node.right, gNode, target);
        return gNode;
    }
}
