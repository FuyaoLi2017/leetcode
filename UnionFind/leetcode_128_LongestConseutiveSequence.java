/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
*/

// Sort Solution takes Time Complexcity: O(nlogn) Space Complexcity:O(n)/O(1)
// HashSet Solution
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

// Union Find Solution
public class Solution {
        public int longestConsecutive(int[] nums) {
            UF uf = new UF(nums.length);
            Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
            for(int i=0; i<nums.length; i++){
                if(map.containsKey(nums[i])){
                    continue;
                }
                map.put(nums[i],i);
                if(map.containsKey(nums[i]+1)){
                    uf.union(i,map.get(nums[i]+1));
                }
                if(map.containsKey(nums[i]-1)){
                    uf.union(i,map.get(nums[i]-1));
                }
            }
            return uf.maxUnion();
        }
    }

    class UF{
        private int[] list;
        public UF(int n){
            list = new int[n];
            for(int i=0; i<n; i++){
                list[i] = i;
            }
        }

        private int root(int i){
            while(i!=list[i]){
                list[i] = list[list[i]];
                i = list[i];
            }
            return i;
        }

        public boolean connected(int i, int j){
            return root(i) == root(j);
        }

        public void union(int p, int q){
          int i = root(p);
          int j = root(q);
          list[i] = j;
        }

        // returns the maxium size of union
        public int maxUnion(){ // O(n)
            int[] count = new int[list.length];
            int max = 0;
            for(int i=0; i<list.length; i++){
                count[root(i)] ++;
                max = Math.max(max, count[root(i)]);
            }
            return max;
        }
    }
