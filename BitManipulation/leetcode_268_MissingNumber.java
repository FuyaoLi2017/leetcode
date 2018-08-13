// my Solution, much slower than bit manipulation
// TC:O(nlgn)
// SC:O(1)
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        int res = 0;
        int count = 0;
        for(int i = 0; i < size; i++){
            if(nums[i] != i){
                res = i;
                break;
            }else{
                count++;
            }
        }
        if(count == nums.length) return nums.length;
        return res;
    }
}

// bit manipulation
// TC:O(n)
// SC:O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for(int i = 0; i < nums.length; i++){
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
