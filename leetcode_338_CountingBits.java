public class Solution {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 0; i <= num; ++i)
                ans[i] = popcount(i);
            return ans;
        }
        private int popcount(int x) {
            int count;
            for (count = 0; x != 0; ++count)
              x &= x - 1; //zeroing out the least significant nonzero bit
            return count;
        }
    }
