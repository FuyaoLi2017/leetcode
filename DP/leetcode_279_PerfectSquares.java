/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself.
 For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 */

 // my solution
 // this is also the provided solution 2
 // TC: O(N * sqrt(N)), SC: O(N)
 class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;

        int maxRoot = (int)Math.sqrt(n);
        List<Integer> squares = new ArrayList<>();

        for (int i = 1; i <= maxRoot; i++) {
            squares.add(i * i);
        }

        for (int i = 1; i <= n; i++) {
            int min = n;

            for (int j = 0; j < squares.size(); j++) {
                int square = squares.get(j);
                if (i - square >= 0) {
                    min = Math.min(dp[i-square] + 1, min);
                }
            }
            dp[i] = min;
        }

        return dp[n];
    }
}

// greedy Enumeration
// TC: O(n^(h/2)) where h is the maximal number of recursion that could happen.
// SC:  O(sqrt(n))
class Solution {
    Set<Integer> square_nums = new HashSet<Integer>();
  
    protected boolean is_divided_by(int n, int count) {
      if (count == 1) {
        return square_nums.contains(n);
      }
  
      for (Integer square : square_nums) {
        if (is_divided_by(n - square, count - 1)) {
          return true;
        }
      }
      return false;
    }
  
    public int numSquares(int n) {
      this.square_nums.clear();
  
      for (int i = 1; i * i <= n; ++i) {
        this.square_nums.add(i * i);
      }
  
      int count = 1;
      for (; count <= n; ++count) {
        if (is_divided_by(n, count))
          return count;
      }
      return count;
    }
  }


  // Greedy + BFS (reduce branches)
//   The order of traversing is of BFS, rather than DFS (Depth-First Search), 
//   is due to the fact that before exhausting all the possibilities of decomposing a number n with 
//   a fixed amount of squares, we would not explore any potential combination that needs more elements.
// TC: O(n^(h/2)) where h is the maximal number of recursion that could happen.
// SC:  O(sqrt(n)^h)
class Solution {
    public int numSquares(int n) {
  
      ArrayList<Integer> square_nums = new ArrayList<Integer>();
      for (int i = 1; i * i <= n; ++i) {
        square_nums.add(i * i);
      }
  
      Set<Integer> queue = new HashSet<Integer>();
      queue.add(n);
  
      int level = 0;
      while (queue.size() > 0) {
        level += 1;
        Set<Integer> next_queue = new HashSet<Integer>();
  
        for (Integer remainder : queue) {
          for (Integer square : square_nums) {
            if (remainder.equals(square)) {
              return level;
            } else if (remainder < square) {
              break;
            } else {
              next_queue.add(remainder - square);
            }
          }
        }
        queue = next_queue;
      }
      return level;
    }
  }


  // Mathmatics
  class Solution {

    protected boolean isSquare(int n) {
      int sq = (int) Math.sqrt(n);
      return n == sq * sq;
    }
  
    public int numSquares(int n) {
      // four-square and three-square theorems.
      while (n % 4 == 0)
        n /= 4;
      if (n % 8 == 7)
        return 4;
  
      if (this.isSquare(n))
        return 1;
      // enumeration to check if the number can be decomposed into sum of two squares.
      for (int i = 1; i * i <= n; ++i) {
        if (this.isSquare(n - i * i))
          return 2;
      }
      // bottom case of three-square theorem.
      return 3;
    }
  }