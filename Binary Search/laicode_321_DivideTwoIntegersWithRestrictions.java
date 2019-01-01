/*
Given two integers a and b, calculate a / b without using divide/mod operations.

Examples:

0 / 1 = 0

1 / 0 = Integer.MAX_VALUE

-1 / 0 = Integer.MAX_VALUE

11 / 2 = 5

-11 / 2 = -5

11 / -2 = -5

-11 / -2 = 5
*/
public class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        if (divisor == 0) {
          return Integer.MAX_VALUE;
        }
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
