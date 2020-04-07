/*
Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
*/


class Solution {
public int myAtoi(String str) {
    int i = 0;
    str = str.trim();
    char[] c = str.toCharArray();

    int sign = 1;
    if (i < c.length && (c[i] == '-' || c[i] == '+')) {
        if (c[i] == '-') {
            sign = -1;
        }
        i++;
    }

    int num = 0;
    int bound = Integer.MAX_VALUE / 10;
    while (i < c.length && c[i] >= '0' && c[i] <= '9') {
        int digit = c[i] - '0';
        if (num > bound || (num == bound && digit > 7)) {
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        num = num * 10 + digit;
        i++;
    }
    return sign * num;
}
}

// a more general solution
class Solution {
	public static int myAtoi(String str) {
        if (str == null) return 0;
        str = str.trim();
        if(str.length() == 0) return 0;
        char firstChar = str.charAt(0);
        int sign = 1, start = 0, len = str.length();
        long sum = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }

        // using long to handle overflow elegantly
        for (int i = start; i < len; i++) {
            if (!Character.isDigit(str.charAt(i)))
                return (int) sum * sign;
            sum = sum * 10 + str.charAt(i) - '0';
            if (sign == 1 && sum > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }

        return (int) sum * sign;
    }
}
