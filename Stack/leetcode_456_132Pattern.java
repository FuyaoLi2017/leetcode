//just array method
public class Solution {
    public boolean find132pattern(int[] nums) {
        int min_i = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length - 1; j++) {
            min_i = Math.min(min_i, nums[j]);
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] < nums[j] && min_i < nums[k])
                    return true;
            }
        }
        return false;
    }
}

//stack method
//write down in my notebook
class Solution {
    public boolean find132pattern(int[] nums) {
        if(nums.length < 3)
            return false;
        Stack<Integer> stack = new Stack<>();
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            min[i] = Math.min(min[i-1], nums[i]);
        }
        //no need to look through index at 0, because there must be one number in the front
        //if not, there won't be any 132 pattern
        for(int j = nums.length - 1; j > 0; j--){
            if(nums[j] > min[j]){
                while(!stack.isEmpty() && stack.peek() <= min[j])
                    stack.pop();
                if(!stack.isEmpty() && stack.peek() < nums[j])
                    return true;
                stack.push(nums[j]);
            }
        }
        return false;
    }
}
