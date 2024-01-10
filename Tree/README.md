# Tree
### 1.Pre-order, In-order, Post-order traversal of Binary Tree
The definition of different traversal method is determined by the position of the root
root first -> Pre-order
root middle -> In-order
root last -> Post-order
https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

### 2. complete binary Tree
>https://baike.baidu.com/item/%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91

>http://www.techiedelight.com/check-given-binary-tree-complete-binary-tree-not/  

>https://www.geeksforgeeks.org/check-whether-binary-tree-complete-not-set-2-recursive-solution/

判断一棵树是否是完全二叉树的思路
1>如果树为空，则直接返回错
　　2>如果树不为空：层序遍历二叉树
　　2.1>如果一个结点左右孩子都不为空，则pop该节点，将其左右孩子入队列；
　　2.1>如果遇到一个结点，左孩子为空，右孩子不为空，则该树一定不是完全二叉树；
　　2.2>如果遇到一个结点，左孩子不为空，右孩子为空；或者左右孩子都为空；加入该节点，则该节点之后的队列中的结点都为叶子节点；该树才是完全二叉树，否则就不是完全二叉树
```java
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public boolean isCompleted(TreeNode root) {
    if (root == null) return true;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      TreeNode cur = q.poll();
      if (cur.left != null && cur.right != null) {
        q.offer(cur.left);
        q.offer(cur.right);
      } else if (cur.left == null && cur.right != null) {
        return false;
      } else {
        if(cur.left != null) {
          q.offer(cur.left);
        }
        while (!q.isEmpty()) {
          TreeNode lastLevel = q.poll();
          if (lastLevel.left != null || lastLevel.right != null) {
            return false;
          }
        }
        return true;
      }
    }
    return true;
  }
}

```
```Java
// a  concise way to implement it
public class Solution {
  public boolean isCompleted(TreeNode root) {
    if (root == null) return true;
    Queue<TreeNode> queue = new LinkedList<>();
    boolean flag = false;
    queue.offer(root);
    while (!queue.isEmpty()) {
    TreeNode cur = queue.poll();
    if (cur.left == null) {
      flag = true;
    } else if (flag) {
      return false;
    } else {
      queue.offer(cur.left);
    }
    if (cur.right == null) {
      flag = true;
    } else if (flag) {
      return false;
    } else {
      queue.offer(cur.right);
    }
  }
  return true;
  }
}

```

### Iterative way for BST
>https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)


### LCA questions
如果确保元素存在，其实可以只看能看到的所有元素就可以，典型的就K node LCA,和classic LCA,看laioffer的答案

## PRUNE TREE
1. 可以通过corner case, 尽可能缩小查询的范围，LC87

## concat the string
lc 572, 可以通过拼接字符串解决问题

##如果向上传递新生成的节点
recursive做法，向上传递节点，先create一个new treenode, return回去，层层传递，在所有循环之前先recursive，这样的不是尾递归的题目比较有挑战，需要对recursive理解的比较好。

### how to do bottom up recusion in tree
LC366,

recusion 放前面，通过特定的计算得到结果，用于返回值

### BST serialization
- Binary tree could be constructed from preorder/postorder and inorder traversal.
- Inorder traversal of BST is an array sorted in the ascending order: inorder = sorted(preorder).

- LC 449!

### LC701 辨析iterative和recursive写法的异同
尾递归很好写iterative

### ArrayDeque push/pop
push is pushing the data to the front of the ArrayDeque.

### Python3 reverse list
https://stackoverflow.com/questions/31633635/what-is-the-meaning-of-inta-1-in-python
a[::-1]

### Level order BFS
Leetcode 2385, use size of the queue and traverse them in a for loop