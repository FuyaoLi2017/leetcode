/*
You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.

You may from index i jump forward to index j (with i < j) in the following way:

During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
(It may be the case that for some index i, there are no legal jumps.)
A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)

Return the number of good starting indexes.
*/
class Solution {
    public int oddEvenJumps(int[] A) {
        int N = A.length;
        if (N <= 1) return N;
        // the true/false value means the that position in A can be reached in a odd/even jump
        boolean[] odd = new boolean[N];
        boolean[] even = new boolean[N];
        odd[N - 1] = even[N - 1] = true;

        TreeMap<Integer, Integer> vals = new TreeMap<>();
        // put the last element into the map
        vals.put(A[N - 1], N - 1);
        for (int i = N - 2; i >= 0; --i) {
            // get the current number in the given array
            int v = A[i];
            // first check the map if it has already have the same number
            // with same number, it can be reached directly with odd/even jump
            if (vals.containsKey(v)) {
            odd[i] = even[vals.get(v)];
            even[i] = odd[vals.get(v)];
            } else {
                // if the map don't have such key
                //we can find a key a strictly lower/higher than the new value
                Integer lower = vals.lowerKey(v);
                Integer higher = vals.higherKey(v);
                // for even jump, previous value should be larger,
                // the value in the map(number with larger index) should be smaller
                // so the even[i] will be placed with the value in odd[vals.get(lower)]
                if (lower != null) {
                    even[i] = odd[vals.get(lower)];
                }
                // for odd jump, previous value should be smaller,
                // the value in the map(number with larger index) should be larger
                // so the odd[i] will be placed with the value in even[vals.get(higher)]
                if (higher != null) {
                    odd[i] = even[vals.get(higher)];
            }
        }
        // put the value into the map after all check
        vals.put(v, i);
    }
    int ans = 0;
        for (boolean b: odd)
            if (b) ans++;
        return ans;
    }
}
