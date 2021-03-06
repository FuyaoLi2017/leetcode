/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)



Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.


Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/
// my solution
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0, j = 0;
        List<int[]> resultArray = new ArrayList<>();
        while(i < A.length && j < B.length) {
            // 注意index更新的要正确，之前笔误了
            int aStart = A[i][0], aEnd = A[i][1];
            int bStart = B[j][0], bEnd = B[j][1];
            if (bStart >= aStart && bStart <= aEnd) {
                resultArray.add(new int[]{bStart, Math.min(aEnd, bEnd)});
                if (aEnd <= bEnd) i++;
                if (aEnd >= bEnd) j++;
            } else if (aStart >= bStart && aStart <= bEnd) {
                resultArray.add(new int[]{aStart, Math.min(aEnd, bEnd)});
                if (aEnd <= bEnd) i++;
                if (aEnd >= bEnd) j++;
            } else if (aEnd < bStart) {
                i++;
            } else if (bEnd < aStart) {
                j++;
            }
        }
        return resultArray.toArray(new int[resultArray.size()][2]);
    }
}
