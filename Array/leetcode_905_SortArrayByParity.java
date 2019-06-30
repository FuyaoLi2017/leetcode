/*
Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.



Example 1:

Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.


Note:

1 <= A.length <= 5000
0 <= A[i] <= 5000
*/
class Solution {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) return A;
        // use two pointers
        int evenBound = 0;
        int oddBound = A.length - 1;
        while (evenBound < oddBound) {
            if (A[evenBound] % 2 == 0) {
                evenBound++;
            } else {
                if (A[oddBound] % 2 == 0) {
                    swap(A, evenBound++, oddBound--);
                } else {
                    while (oddBound > evenBound && A[oddBound] % 2 == 1) {
                        oddBound--;
                    }
                    swap(A, evenBound++, oddBound--);
                }
            }
        }
        return A;
    }

    private void swap(int[] A, int first, int second) {
        int temp = A[first];
        A[first] = A[second];
        A[second] = temp;
    }
}

// given solution, 每个情况都考虑到其实，可以把code写的更加简洁
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if (A[i] % 2 > A[j] % 2) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            if (A[i] % 2 == 0) i++;
            if (A[j] % 2 == 1) j--;
        }

        return A;
    }
}
