/*
We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation:
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].
*/

// 有2对组合
// For convenience, say a1 = A[i-1], b1 = B[i-1] and a2 = A[i], b2 = B[i].
// Now, if a1 < a2 and b1 < b2, then it is allowed to have both of these columns natural (unswapped),
// or both of these columns swapped. This possibility leads to n2 = min(n2, n1) and s2 = min(s2, s1 + 1).
// Another, (not exclusive) possibility is that a1 < b2 and b1 < a2.
// This means that it is allowed to have exactly one of these columns swapped.
// This possibility leads to n2 = min(n2, s1) or s2 = min(s2, n1 + 1).
// Note that it is important to use two if statements separately,
// because both of the above possibilities might be possible.
// At the end, the optimal solution must leave the last column either natural or swapped,
// so we take the minimum number of swaps between the two possibilities.

class Solution {
    public int minSwap(int[] A, int[] B) {
        // n: natural, s: swapped
        int n1 = 0, s1 = 1;
        for (int i = 1; i < A.length; ++i) {
            int n2 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                n2 = Math.min(n2, n1);
                s2 = Math.min(s2, s1 + 1);
            }
            if (A[i-1] < B[i] && B[i-1] < A[i]) {
                n2 = Math.min(n2, s1);
                s2 = Math.min(s2, n1 + 1);
            }
            n1 = n2;
            s1 = s2;
        }
        return Math.min(n1, s1);
    }
}

// a high vote more clear solution
// swapRecord means for the ith element in A and B, the minimum swaps if we swap A[i] and B[i]
// fixRecord means for the ith element in A and B, the minimum swaps if we DONOT swap A[i] and B[i]
class Solution {
    public int minSwap(int[] A, int[] B) {
        int swapRecord = 1, fixRecord = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= B[i] || B[i - 1] >= A[i]) {
		// In this case, the ith manipulation should be same as the i-1th manipulation
                // fixRecord = fixRecord;
                swapRecord++;
            } else if (A[i - 1] >= A[i] || B[i - 1] >= B[i]) {
		// In this case, the ith manipulation should be the opposite of the i-1th manipulation
                int temp = swapRecord;
                swapRecord = fixRecord + 1;
                fixRecord = temp;
            } else {
                // Either swap or fix is OK. Let's keep the minimum one
                int min = Math.min(swapRecord, fixRecord);
                swapRecord = min + 1;
                fixRecord = min;
            }
        }
        return Math.min(swapRecord, fixRecord);
    }
}
