/*
Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.

Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.



Example 1:

Input: [3,2,4,1]
Output: [4,2,4,3]
Explanation:
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: A = [3, 2, 4, 1]
After 1st flip (k=4): A = [1, 4, 2, 3]
After 2nd flip (k=2): A = [4, 1, 2, 3]
After 3rd flip (k=4): A = [3, 2, 1, 4]
After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
Example 2:

Input: [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.


Note:

1 <= A.length <= 100
A[i] is a permutation of [1, 2, ..., A.length]
*/
class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> result = new ArrayList<>();
        int N = A.length;
        Integer[] indexSortByValue = new Integer[N];
        for (int i = 0; i < N; i++) {
            indexSortByValue[i] = i + 1;
        }
        // the index with larger values in A is placed in the front
        Arrays.sort(indexSortByValue, (i, j) -> A[j-1] - A[i-1]);

        for (int i : indexSortByValue) {
            for (int f : result) {
			/* update the new position of the value after the result flip operation */
                if (i <= f) {
                    i = f + 1 - i;
                }
            }
            // flip the current largest value to the first place
            result.add(i);
            // flip the current largest value to the correct position
            result.add(N--);
        }
        return result;
    }
}

// a much faster solution
class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        for (int x = A.length, i; x > 0; --x) {
            // check whether the reverse array's value is equal to x
            // we can make sure we are moving the largest element to the correct place.
            for (i = 0; A[i] != x; ++i);
            reverse(A, i + 1);
            res.add(i + 1);
            reverse(A, x);
            res.add(x);
        }
        return res;
    }

    public void reverse(int[] A, int k) {
        for (int i = 0, j = k - 1; i < j; i++, j--) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
}
