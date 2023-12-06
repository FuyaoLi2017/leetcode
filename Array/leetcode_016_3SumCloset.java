public class Solution {
public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int closest=nums[0]+nums[1]+nums[2];
    int low,high;
    for(int i=0;i<nums.length-1;i++){
        low=i+1;
        high=nums.length-1;
        while(low<high){
            if(nums[low]+nums[high]==target-nums[i]) return target;
            else if(nums[low]+nums[high]>target-nums[i]){
                while(low<high&&nums[low]+nums[high]>target-nums[i]) high--;
                if(Math.abs(nums[i]+nums[low]+nums[high+1]-target)<Math.abs(closest-target))
                    closest=nums[i]+nums[low]+nums[high+1];
            }
            else{
                while(low<high&&nums[low]+nums[high]<target-nums[i]) low++;
                if(Math.abs(nums[i]+nums[low-1]+nums[high]-target)<Math.abs(closest-target))
                    closest=nums[i]+nums[low-1]+nums[high];
            }
        }
    }
    return closest;
}
}


// solution 12/6/2023
class Solution2 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        int diff = Math.abs(target - sum);
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i+1;
                int hi = nums.length-1;
                while (lo < hi) {
                    // the same number if appeared multiple times, it can be reused
                    // while(lo < hi && nums[lo] == nums[lo+1]) lo++;
                    // while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                    int currSum = nums[i] + nums[lo] + nums[hi];
                    int currDiff = Math.abs(currSum - target);
                    if (currDiff < diff) {
                        sum = currSum;
                        diff = currDiff;
                    }
                    if (currSum < target) {
                        lo++;
                    }
                    else if (currSum > target) {
                        hi--;
                    }
                    else {
                        return currSum;
                    }
                }
            }
        }
        return sum;
    }
}