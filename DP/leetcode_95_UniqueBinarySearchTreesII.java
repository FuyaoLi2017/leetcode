```
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
```
Example:
Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

   /**
    * Definition for a binary tree node.
    * public class TreeNode {
    *     int val;
    *     TreeNode left;
    *     TreeNode right;
    *     TreeNode(int x) { val = x; }
    * }
    */
   public class Solution {
       public List<TreeNode> generateTrees(int n) {
           if(n==0) return new ArrayList<TreeNode>();
           return genTrees(1,n);
       }

       public List<TreeNode> genTrees (int start, int end)
       {

           List<TreeNode> list = new ArrayList<TreeNode>();

           if(start>end)
           {
               list.add(null);
               return list;
           }

           if(start == end){
               list.add(new TreeNode(start));
               return list;
           }

           List<TreeNode> left,right;
           for(int i=start;i<=end;i++)
           {
               // DP algorithm, this is the feature of BST
               left = genTrees(start, i-1);
               right = genTrees(i+1,end);
               // traverse all the possible combinations of left node and right node and assign them the root.
               // with the DP algorithm, we can look through all the cases of BST
               for(TreeNode lnode: left)
               {
                   for(TreeNode rnode: right)
                   {
                       TreeNode root = new TreeNode(i);
                       root.left = lnode;
                       root.right = rnode;
                       list.add(root);
                   }
               }

           }

           return list;
       }
   }
