/**
 * Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.
 */


 // DP solution, this comes with some variation
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
      // Test case with empty set.
      int n = nums.length;
      if (n == 0) return new ArrayList();
          
      // Container to keep the largest divisible subset
      //   that ends with each of the nums.
      List<ArrayList> EDS = new ArrayList();
      for (int num : nums) EDS.add(new ArrayList());
  
      /* Sort the original list in ascending order. */
      Arrays.sort(nums);
  
      /* Calculate all the values of EDS(X_i) */
      for (int i = 0; i < n; ++i) {
        List<Integer> maxSubset = new ArrayList();
              
        // Find the largest divisible subset of previous elements.
        for (int k = 0; k < i; ++k) 
          if (nums[i] % nums[k] == 0 && maxSubset.size() < EDS.get(k).size())
            maxSubset = EDS.get(k);
            
        // Extend the found subset with the element itself.
        EDS.get(i).addAll(maxSubset);
        EDS.get(i).add(nums[i]);
      }
      /* Find the largest of EDS values  */
      List<Integer> ret = new ArrayList();
      for (int i = 0; i < n; ++i) 
        if (ret.size() < EDS.get(i).size()) ret = EDS.get(i);
      return ret;
    }  
  }

 class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
      // Test case with empty set.
      int n = nums.length;
      if (n == 0) return new ArrayList();
          
      // Container to keep the size of the largest divisible subset
      //   that ends with each of the nums.
      Integer[] dp = new Integer[n];
  
      /* Sort the original list in ascending order. */
      Arrays.sort(nums);
  
      Integer maxSubsetSize = -1, maxSubsetIndex = -1;
      
      /* Calculate the rest of EDS(X_i) */
      for (int i = 0; i < n; ++i) {
        Integer subsetSize = 0;
  
        // Find the size of the largest divisible subset.
        for (int k = 0; k < i; ++k) 
          if (nums[i] % nums[k] == 0 && subsetSize < dp[k])
            subsetSize = dp[k];
  
        // Extend the found subset with the element itself.
        dp[i] = subsetSize + 1;
      
        // We reuse this loop to obtain the largest subset size 
        //   in order to prepare for the reconstruction of subset.
        if (maxSubsetSize < dp[i]) {
          maxSubsetSize = dp[i];
          maxSubsetIndex = i;
        }
      }
      
      /* Reconstruct the largest divisible subset  */
      LinkedList<Integer> subset = new LinkedList();
      Integer currSize = maxSubsetSize;
      Integer currTail = nums[maxSubsetIndex];
      for (int i = maxSubsetIndex; i >= 0; --i) {
        if (currSize == 0) break;
      
        if (currTail % nums[i] == 0 && currSize == dp[i]) {
          subset.addFirst(nums[i]);
          currTail = nums[i];
          currSize -= 1;
        }
      }
  
      return subset;
    }  
  }