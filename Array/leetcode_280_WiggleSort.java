//basic Sort method
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length - 1; i += 2){
            swap(i, i+1, nums);
        }
    }

    private void swap(int i, int j, int nums[]){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//another method
/*We can swap along the array according to the rules,
*the swap method is just the opposite between the odd and even index
*/
public void wiggleSort(int[] nums) {
    boolean less = true;
    for (int i = 0; i < nums.length - 1; i++) {
        if (less) {
            if (nums[i] > nums[i + 1]) {
                swap(nums, i, i + 1);
            }
        } else {
            if (nums[i] < nums[i + 1]) {
                swap(nums, i, i + 1);
            }
        }
        less = !less;
    }
}

//a shorten version by compressing the conditions into one sentence
public void wiggleSort(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
        if (((i % 2 == 0) && nums[i] > nums[i + 1])
                || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
            swap(nums, i, i + 1);
        }
    }
}
