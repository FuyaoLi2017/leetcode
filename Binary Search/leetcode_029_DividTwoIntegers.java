// 这道题利用了二分查找的思想，由于不能使用乘除法，所以利用bit manipulation,将得到的数位左移实现二分查找
// 这个题目可以设置2层while循环，可以减去二分查找的最大模块，然后在剩下的比较小的一块再做二分累加查找
// 根据：https://leetcode.com/problems/divide-two-integers/discuss/13407/Detailed-Explained-8ms-C++-solution 整理得到的java解法
class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        long dvd = Math.abs((long) dividend);
	    long dvs = Math.abs((long) divisor);
        int res = 0;
        while (dvd >= dvs) {
            long temp = dvs, multiple = 1;
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            res += multiple;
        }
        return sign == 1 ? res : -res;
    }
}

// high vote answer in Java
// using recursive calls to replace the outside layer while loop  while [(dvd >= (temp << 1))]
ublic int divide(int dividend, int divisor) {
	//Reduce the problem to positive long integer to make it easier.
	//Use long to avoid integer overflow cases.
	int sign = 1;
	if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
		sign = -1;
	long ldividend = Math.abs((long) dividend);
	long ldivisor = Math.abs((long) divisor);

	//Take care the edge cases.
	if (ldivisor == 0) return Integer.MAX_VALUE;
	if ((ldividend == 0) || (ldividend < ldivisor))	return 0;

	long lans = ldivide(ldividend, ldivisor);

	int ans;
	if (lans > Integer.MAX_VALUE){ //Handle overflow.
		ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
	} else {
		ans = (int) (sign * lans);
	}
	return ans;
}

private long ldivide(long ldividend, long ldivisor) {
	// Recursion exit condition
	if (ldividend < ldivisor) return 0;

	//  Find the largest multiple so that (divisor * multiple <= dividend),
	//  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
	//  Think this as a binary search.
	long sum = ldivisor;
	long multiple = 1;
	while ((sum+sum) <= ldividend) {
		sum += sum;
		multiple += multiple;
	}
	//Look for additional value for the multiple from the reminder (dividend - sum) recursively.
	return multiple + ldivide(ldividend - sum, ldivisor);
}
