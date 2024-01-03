/**
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 */

 // My solution
 class Solution {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            long midNumSquare = (long)mid * mid;
            
            if (midNumSquare > x) {
                right = mid-1;
            } else if (midNumSquare < x) {
                left = mid+1;
            } else {
                return mid;
            }
        }
        
        return left-1;
    }
}

// we can also use divide to avoid overflow, handle the corner case conditions


// Editoral solution, very similar, the right cursor will finally move to the left side, which is the same as left-1
class Solution {
  public int mySqrt(int x) {
    if (x < 2) return x;

    long num;
    int pivot, left = 2, right = x / 2;
    while (left <= right) {
      pivot = left + (right - left) / 2;
      num = (long)pivot * pivot;
      if (num > x) right = pivot - 1;
      else if (num < x) left = pivot + 1;
      else return pivot;
    }

    return right;
  }
}