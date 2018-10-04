/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:

1 is typically treated as an ugly number.
n does not exceed 1690.
*/

// brute force, TLE
class Solution {
    public int nthUglyNumber(int n) {
        int current = 1;
        for(int i = 1; n > 0; i++) {
            if(isUglyNumber(i)) {
                n--;
                current = i;
            }
        }
        return current;
    }

    private boolean isUglyNumber(int num) {
        if(num <= 0) return false;
        while(num % 2 == 0) num >>= 1;
        while(num % 3 == 0) num /= 3;
        while(num % 5 == 0) num /= 5;
        return num == 1;
    }
}

// dp
/*
The idea of this solution is from this page:http://www.geeksforgeeks.org/ugly-numbers/

The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:

(1) 1×2, 2×2, 3×2, 4×2, 5×2, …
(2) 1×3, 2×3, 3×3, 4×3, 5×3, …
(3) 1×5, 2×5, 3×5, 4×5, 5×5, …
We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.

Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.

Every step we choose the smallest one, and move one step after,including nums with same value.

Thanks for this author about this brilliant idea. Here is my java solution
*/
public class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }
}

// Priority Queue
/*
we can keep using poll() to remove the same element in the heap until we have found a new larger element,
the new larger element than the previous all elements will stay on the top of the heap (easy to found ,huh~)
then we can begin a new round
remember to use Long type to avoid overflow
*/
class Solution {
    public int nthUglyNumber(int n) {
    if(n==1) return 1;
    PriorityQueue<Long> q = new PriorityQueue();
    q.add(1l);

    for(long i=1; i<n; i++) {
        long tmp = q.poll();
        while(!q.isEmpty() && q.peek()==tmp) tmp = q.poll(); //check if all the duplicate elements has been removed

        q.add(tmp*2);
        q.add(tmp*3);
        q.add(tmp*5);
    }
    return q.poll().intValue();
}
}
