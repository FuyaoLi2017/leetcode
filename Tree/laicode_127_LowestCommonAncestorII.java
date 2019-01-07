/*
Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.

Assumptions

There is parent pointer for the nodes in the binary tree

The given two nodes are not guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
*/
// normal practice, merge the tree
/**
 * public class TreeNodeP {
 *   public int key;
 *   public TreeNodeP left;
 *   public TreeNodeP right;
 *   public TreeNodeP parent;
 *   public TreeNodeP(int key, TreeNodeP parent) {
 *     this.key = key;
 *     this.parent = parent;
 *   }
 * }
 */
public class Solution {
  public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
    int l1 = length(one);
    int l2 = length(two);
    if (l1 >= l2) {
      return merge(one, two, l1 - l2);
    } else {
      return merge(two, one, l2 - l1);
    }
  }

  private TreeNodeP merge(TreeNodeP longer, TreeNodeP shorter, int diff) {
    while (diff > 0) {
      longer = longer.parent;
      diff--;
    }
    // will return the LCA if it exist, or return null if it doesn't exist
    while (longer != shorter) {
      longer = longer.parent;
      shorter = shorter.parent;
    }
    return longer;
  }

  private int length(TreeNodeP node) {
    int lengthCount = 0;
    while (node != null) {
      node = node.parent;
      lengthCount++;
    }
    return lengthCount;
  }
}




// a very tricky solution
// https://piazza.com/class/j0eqhhdregb3i?cid=1712
public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
     TreeNodeP iterA = one;
     TreeNodeP iterB = two;
     while (iterA != iterB) {
         iterA = iterA == null ? two : iterA.parent;
         iterB = iterB == null ? one : iterB.parent;
     }
     return iterA;
 }
