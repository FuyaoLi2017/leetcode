/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree:

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false
Example 2:

Input: [5,2,1,3,6]
Output: true
Follow up:
Could you do it using only constant space complexity?

*/


// check the boundary solution, general solution
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean verify(int[] preorder, int start, int end, int min, int max) {
        if (start > end) {
            return true;
        }
        int root = preorder[start];
        if (root > max || root < min) {
            return false;
        }

        int rightIndex = start;
        while (rightIndex <= end && preorder[rightIndex] <= root) {
            rightIndex++;
        }
        return verify(preorder, start + 1, rightIndex - 1, min, root)
            && verify(preorder, rightIndex, end, root, max);
    }
}


class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0) return true;
        return verify(preorder, 0, preorder.length - 1);
    }

    private boolean verify(int[] a, int start, int end) {
        if(start >= end) return true;
        int pivot = a[start];
        int bigger = -1;
        for(int i = start + 1; i <= end; i++) {
            if(bigger == -1 && a[i] > pivot) bigger = i;
            if(bigger != -1 && a[i] < pivot) return false;
        }
        if(bigger == -1) {
            return verify(a, start + 1, end);
        } else {
            return verify(a, start + 1, bigger - 1) && verify(a, bigger, end);
        }
    }
}


// a fast solution
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder.length <= 1)
            return true;
        verify(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return index == preorder.length;
    }

    private int index;

    public void verify(int[] preorder, int left, int right) {
        if (index >= preorder.length)
            return;
        if (preorder[index] <= left || preorder[index] >= right)
            return;
        int val = preorder[index];
        index++;
        if (index < preorder.length) {
            verify(preorder, left, val);
        }
        if (index < preorder.length) {
            verify(preorder, val, right);
        }
    }
}
