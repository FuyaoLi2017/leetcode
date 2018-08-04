// two pointers,give the non-zero values replace the front part of the array
class Solution {
    public void moveZeroes(int[] nums) {
        int lastZeroFoundAt = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[lastZeroFoundAt++] = nums[i];
            }
        }
        for(int i = lastZeroFoundAt; i < nums.length; i++){
            nums[i] = 0;
        }
        return;
    }
}
