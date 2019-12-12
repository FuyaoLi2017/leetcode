/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
*/
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(n, 2, result, new ArrayList<Integer>());
        return result;
    }

    private void getResult(int n, int start, List<List<Integer>> result, List<Integer> list){

        for(int i=start;i<=Math.sqrt(n);i++){
            if(n%i!=0){
                continue;
            }
            list.add(i);
            list.add(n/i);
            result.add(new ArrayList<Integer>(list));
            list.remove(list.size()-1);
            getResult(n/i, i, result, list);
            list.remove(list.size()-1);
        }

    }
}
