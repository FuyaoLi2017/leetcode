/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

E.g.    Input: n = 4, k = 2

    Output: [

                     [2,4],

                     [3,4],

                     [2,3],

                     [1,2],

                     [1,3],

                     [1,4]

        ]
*/
public class Solution {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (n < k || k == 0) {
      res.add(new ArrayList<>());
      return res;
    }
    res.clear();
    helper(res, new ArrayList<Integer>(), 1, k, n);
    return res;
  }

  private void helper(List<List<Integer>> res, List<Integer> cur, int index, int length, int n) {
    if(cur.size() == length) {
      res.add(new ArrayList<Integer>(cur));
      return;       //这个return可以加也可以不加
      // 不加return的话，i还会不断增加，但是在下面的for loop一定会超过n，
      // 超过n的时候就退出了。多做了几步操作，但是没有加到结果中，结果都是正确的
    }
    for (int i = index; i <= n; i++) {
      cur.add(i);
      helper(res, cur, i + 1, length, n);
      cur.remove(cur.size() - 1);
    }
  }
}
