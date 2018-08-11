/**
* we cannot add the element first and then check whether following elements contains import junit.framework.TestCase;
* because if there is just one element, this can be wrong, we should first use contains() and then add(), just like #2 method
*/
// #1 method
class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }
}

// #2 method
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i]))
            return true;
            set.add(nums[i]);
        }
        return false;
    }
}
