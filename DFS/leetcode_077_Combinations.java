/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

// my solution
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if (n < k || k == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> currentList, int index, int n, int k) {
        if (currentList.size() == k) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        if (index == n+1) { // have reach the end of the array and has not accumlated to k elements
            return;
        }
        for (int i = index; i <= n; i++) {
            currentList.add(i);
            dfs(result, currentList, i + 1, n, k);
            currentList.remove(currentList.size() - 1);
        }
    }
}


// a high vote solution
// in this solution, the author didn't return the index == n+1 case since it will not go to for loop
// and will terminate directly, but it will be a little bit slower
// this method use k also as a counter, which saves space
public static List<List<Integer>> combine(int n, int k) {
       List<List<Integer>> combs = new ArrayList<List<Integer>>();
       combine(combs, new ArrayList<Integer>(), 1, n, k);
       return combs;
   }
   public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
       if(k==0) {
           combs.add(new ArrayList<Integer>(comb));
           return;
       }
       for(int i=start;i<=n;i++) {
           comb.add(i);
           combine(combs, comb, i+1, n, k-1);
           comb.remove(comb.size()-1);
       }
   }
