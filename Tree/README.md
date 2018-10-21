# Tree
### 1.Pre-order, In-order, Post-order traversal of Binary Tree
The definition of different traversal method is determined by the position of the root
root first -> Pre-order
root middle -> In-order
root last -> Post-order

### 2. complete binary Tree
>https://baike.baidu.com/item/%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91

>http://www.techiedelight.com/check-given-binary-tree-complete-binary-tree-not/  

>https://www.geeksforgeeks.org/check-whether-binary-tree-complete-not-set-2-recursive-solution/

判断一棵树是否是完全二叉树的思路
1>如果树为空，则直接返回错
　　2>如果树不为空：层序遍历二叉树
　　2.1>如果一个结点左右孩子都不为空，则pop该节点，将其左右孩子入队列；
　　2.1>如果遇到一个结点，左孩子为空，右孩子不为空，则该树一定不是完全二叉树；
　　2.2>如果遇到一个结点，左孩子不为空，右孩子为空；或者左右孩子都为空；则该节点之后的队列中的结点都为叶子节点；该树才是完全二叉树，否则就不是完全二叉树
```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public boolean completeBinaryTree(TreeNode root) {
        if (root == null) return false;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.add(root);
        while (!dq.isEmpty()) {
            TreeNode current = dq.peekFirst();
            TreeNode currentLeft = current.left;
            TreeNode currentRight = current.right;
            if (currentLeft != null && currentRight != null) {
                dq.poll();
                dq.add(currentLeft);
                dq.add(currentRight);
            } else if (currentLeft == null && currentRight != null){
                return false;
            } else {
                //上述情况下（左孩子非空右孩子空，或者左右都空），之后的所有node就必须都是叶子节点才是完全二叉树
                //遍历当前dq中所有节点的孩子是不是都是空的，如果是，就return true, 如果不是，就return false
                dq.poll(); //poll是删掉首元素
                while(!dq.isEmpty()) {
                    TreeNode currentNode = dq.poll();
                    if (currentNode.left == null && currentNode.right == null)
                        continue;
                    else return false;
                }
            }

        }
        return true;
    }
}
```
