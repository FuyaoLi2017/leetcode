//My solution
class Solution {
    public boolean isPowerOfThree(int n) {
        double m =(double)n;
        while(m >= 3){
            m = m / 3;
        }
        if(m == 1){
            return true;
        }
        return false;
    }
}

//loop method
public class Solution {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}

//convert into string
public class Solution {
    public boolean isPowerOfThree(int n) {
        //string match the form that first digit is 1 and following digits are all zeros or nothing.
        return Integer.toString(n, 3).matches("^10*$");
    }
}

//换底公式
public class Solution {
    public boolean isPowerOfThree(int n) {
        return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
    }
}

//利用Integer最大值 3^19 = 1162261467 <Integer.MAX_VALUE(2147483647)
public class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
