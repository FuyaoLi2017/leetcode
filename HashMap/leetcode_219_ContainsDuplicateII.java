//my Solution
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                //why here we cannot use List<Integer> list = new ArrayList<>(); ?
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            }else{
                map.get(nums[i]).add(i);
                int size = map.get(nums[i]).size();
                //i is the index of the present element
                //we need to know the index of the nearest same value get(size - 1) is i, get(size - 2) is the nearest value
                int distance = i - map.get(nums[i]).get(size - 2);
                if(distance <= k)
                    return true;
            }
        }
        return false;
    }
}

//high vote answer
public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            //if it cannot add a new element, it should return true
            if(!set.add(nums[i])) return true;
        }
        return false;
 }
