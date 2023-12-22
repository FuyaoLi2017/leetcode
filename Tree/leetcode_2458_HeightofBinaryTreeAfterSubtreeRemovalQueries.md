# Question
 You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.


didn't propose a solution, reference
https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/solutions/2757990/python-3-explanation-with-pictures-dfs/

- When a node (Let's say D) is removed, all of its children are removed as well. So every path that goes through D stops by here. However, if D has some cousions, then the paths through these cousins will certainly be longer. We should look for the longest path among these paths through the cousins of D, which is equivalent to finding the cousin with the largest height.
- Therefore, we store the nodes according to depth. For nodes having the same depth, we sort them by their heights and only keep the top 2 of them that having the maximum depth.

Time O(N)
Space O(N)

Notes: https://realpython.com/inner-functions-what-are-they-good-for/
 
```python
class Solution:
    def treeQueries(self, R: Optional[TreeNode], Q: List[int]) -> List[int]:
        Depth, Height = collections.defaultdict(int), collections.defaultdict(int)

        def dfs(node, depth):
            if not node:
                return -1
            Depth[node.val] = depth
            cur = max(dfs(node.left, depth + 1), dfs(node.right, depth + 1)) + 1
            Height[node.val] = cur
            return cur
        dfs(R, 0)

        cousins = collections.defaultdict(list) # Group nodes according to their depth. Keep the top 2 heights.
        for val, depth in Depth.items():
            cousins[depth].append((-Height[val], val))
            cousins[depth].sort()
            if len(cousins[depth]) > 2:
                cousins[depth].pop()

        ans = []
        for q in Q:
            depth = Depth[q]
            if len(cousins[depth]) == 1:  # No cousin, path length equals depth - 1.
                ans.append(depth - 1)
            elif cousins[depth][0][1] == q:  # The removed node has the largest height, look for the node with 2nd largest height.
                ans.append(-cousins[depth][1][0] + depth)
			else:   # Look for the node with the largest height.
                ans.append(-cousins[depth][0][0] + depth)
        return ans
```