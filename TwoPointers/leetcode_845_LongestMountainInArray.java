/*
Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain.

Return 0 if there is no mountain.

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: [2,2,2]
Output: 0
Explanation: There is no mountain.
Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000
Follow up:

Can you solve it using only one pass?
Can you solve it in O(1) space?
*/

//  my one pass solution
class Solution {
    public int longestMountain(int[] A) {
        int start = 0;
        int end = 0;
        int res=0;
        while(start < A.length && end < A.length){
            // find ascending part
            while(end<A.length-1 && A[end+1] > A[end]){
                end++;
            }
            if (start==end){
                start++;
                end++;
                continue;
            }
            // find descending part
            int peak = end;
            while(end<A.length-1 && A[end+1] < A[end]){
                end++;
            }
            if(peak==end){
                start=end+1;
                end++;
                continue;
            }
            int range=end-start+1;
            start = end;
            res = Math.max(res,range);
        }
        return res;
    }
}

// a standard solution, very similar
class Solution {
    public int longestMountain(int[] A) {
        int N = A.length;
        int ans = 0, base = 0;
        while (base < N) {
            int end = base;
            // if base is a left-boundary
            if (end + 1 < N && A[end] < A[end + 1]) {
                // set end to the peak of this potential mountain
                while (end + 1 < N && A[end] < A[end + 1]) end++;

                // if end is really a peak..
                if (end + 1 < N && A[end] > A[end + 1]) {
                    // set end to the right-boundary of mountain
                    while (end + 1 < N && A[end] > A[end + 1]) end++;
                    // record candidate answer
                    ans = Math.max(ans, end - base + 1);
                }
            }
            // 这一个条件判断的很好，综合了corner case
            base = Math.max(end, base + 1);
        }

        return ans;
    }
}
