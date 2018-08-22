/*
two assumptions.
1.the input won't have zero at digits[0] as it's not valid to have a number's most significant number to be a zero.

2.if the resulting digits[0] is zero, then all its trailing digits must be zeroes too. Thus we don't need to assign digits[] to res[i] to res[len+1].
*/
class Solution {
    public int[] plusOne(int[] digits) {
    for (int i = digits.length - 1; i >=0; i--) {
        if (digits[i] != 9) {
            digits[i]++;
            break;
        } else {
            digits[i] = 0;
        }
    }
    if (digits[0] == 0) {
        int[] res = new int[digits.length+1];
        res[0] = 1;
        return res;
    }
    return digits;
    }
}

//solution 2, similar method
class Solution {
    public int[] plusOne(int[] digits) {
        int n =digits.length;
        for(int i = n-1; i >= 0; i--){
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] res = new int [n+1];
        res[0] = 1;
        return res;
    }
}
