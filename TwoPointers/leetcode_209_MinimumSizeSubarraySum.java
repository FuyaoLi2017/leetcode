//Loop with memorization O(n^3)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;
        //check all the numbers of the nums
        int addAll = 0;
        for(int i = 0; i < nums.length; i++){
            addAll += nums[i];
        }
        if(addAll < s) return 0;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < nums.length; i++){
            sum[i] = sum[i - 1] + nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                int count;
                count = sum[j] - sum[i] + nums[i];
                if(count >= s){
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        return min;
    }
}

//two pointer solution O(n)
class Solution {
    public int minSubArrayLen(int s, int[] a) {
      if (a == null || a.length == 0)
        return 0;

      int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

      while (j < a.length) {
        sum += a[j++];

        while (sum >= s) {
          min = Math.min(min, j - i);
          sum -= a[i++];
        }
      }

      return min == Integer.MAX_VALUE ? 0 : min;
    }
}
