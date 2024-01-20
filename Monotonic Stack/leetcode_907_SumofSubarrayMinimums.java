/**
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. 
 * Since the answer may be large, return the answer modulo 109 + 7.
 */

 // my DP solution - pass 86/87
 class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int mod = (int)Math.pow(10, 9) + 7;
        int res = 0;

        for (int i = 0; i < n; i++) {
            int temp = 0;
            temp += arr[i];
            int prevMin = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < prevMin) {
                    temp += arr[j];
                    prevMin = arr[j];
                } else {
                    temp += prevMin;
                }
            }

            res = (res + temp) % mod;
        }
        return res;
    }
}

/**
 * You can think of the problem as a three-step process:

1. Consider all subarrays of the given array.
2. For each of the subarrays, calculate the minimum.
3. Add all the minimums calculated above.
The summation of all minimums is what we need to return as an answer.

The first approach introduces the concept of each array element's contribution to the answer. It then uses a monotonic stack to arrive at the solution. The second approach applies dynamic programming and builds on top of the earlier monotonic stack method.

Let's dive in.
 */

 // Approach 1: Monotonic Stack - Contribution of Each Element
 // Let's figure out how to get the number of subarrays that contain a specific element in a given range.
 // expected TC O(n) and SC O(n)

 class Solution {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;

        Stack<Integer> stack = new Stack<>();
        long sumOfMinimums = 0;

        // building monotonically increasing stack
        for (int i = 0; i <= arr.length; i++) {

            // when i reaches the array length, it is an indication that
            // all the elements have been processed, and the remaining
            // elements in the stack should now be popped out.

            while (!stack.empty() && (i == arr.length || arr[stack.peek()] >= arr[i])) {

                // Notice the sign ">=", This ensures that no contribution
                // is counted twice. rightBoundary takes equal or smaller 
                // elements into account while leftBoundary takes only the
                // strictly smaller elements into account

                int mid = stack.pop();
                int leftBoundary = stack.empty() ? -1 : stack.peek();
                int rightBoundary = i;

                // count of subarrays where mid is the minimum element
                long count = (mid - leftBoundary) * (rightBoundary - mid) % MOD;

                sumOfMinimums += (count * arr[mid]) % MOD;
                sumOfMinimums %= MOD;
            }
            stack.push(i);
        }

        return (int) (sumOfMinimums);
    }
}


// Approach 2: Monotonic Stack + Dynamic Programming, this one is easier than first appraoch
// dp[i] signifies the sum of the minimums of all subarrays, which end at an index i
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;

        Stack<Integer> stack = new Stack<>();

        // make a dp array of the same size as the input array `arr`
        int[] dp = new int[arr.length];

        // making a monotonic increasing stack
        for (int i = 0; i < arr.length; i++) {
            // pop the stack until it is empty or
            // the top of the stack is greater than or equal to
            // the current element
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // either the previousSmaller element exists
            if (stack.size() > 0) {
                int previousSmaller = stack.peek();
                // previous dp array min also contributes to current array
                dp[i] = dp[previousSmaller] + (i - previousSmaller) * arr[i];
            } else {
                // or it doesn't exist, in this case the current element
                // contributes with all subarrays ending at i
                dp[i] = (i + 1) * arr[i];
            }
            // push the current index
            stack.push(i);
        }

        // Add all elements of the dp to get the answer
        long sumOfMinimums = 0;
        for (int count : dp) {
            sumOfMinimums += count;
            sumOfMinimums %= MOD;
        }

        return (int) sumOfMinimums;
    }
}
