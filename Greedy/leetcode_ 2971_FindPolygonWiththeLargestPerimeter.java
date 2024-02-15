/**
 * You are given an array of positive integers nums of length n.

A polygon is a closed plane figure that has at least 3 sides. The longest side of a polygon is smaller than the sum of its other sides.

Conversely, if you have k (k >= 3) positive real numbers a1, a2, a3, ..., ak where a1 <= a2 <= a3 <= ... <= ak and a1 + a2 + a3 + ... + ak-1 > ak, then there always exists a polygon with k sides whose lengths are a1, a2, a3, ..., ak.

The perimeter of a polygon is the sum of lengths of its sides.

Return the largest possible perimeter of a polygon whose sides can be formed from 
nums, or -1 if it is not possible to create a polygon.
 */

 class Solution {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long previousElementsSum = 0;
        long ans = -1;
        for (int num : nums) {
            if (num < previousElementsSum) {
                ans = num + previousElementsSum;
            }
            previousElementsSum += num;
        }
        return ans;
    }
}


// my previous submission in contest, we can scan the results in line, we don't need to store these vals
// it doesn't fully use the sorted array
class Solution {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        
        long[] cacheSum = new long[nums.length+1];
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cacheSum[i+1] = sum;
        }
        
        // key - end, val - start
        Map<Integer, Integer> cacheMap = new HashMap<>();
        
        long res = -1;
        for (int i = 0; i < nums.length-2; i++) {
            for (int j = nums.length-2; j >= i+1; j--) {
                if (cacheMap.containsKey(j) && cacheMap.get(j) <= i) {
                    continue;
                }
                long start = cacheSum[i];
                long end = cacheSum[j+1];
                cacheMap.put(j, i);
                if (end - start > nums[j+1]) {
                    // res = Math.max(res, cacheSum[j+2] - start);
                    return cacheSum[j+2] - start;
                }
            }
        }
        return res;
    }
}