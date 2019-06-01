/*
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.
*/
// a solution directly using list
public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<Integer>();
	    ret.add(1);
	    for (int i = 1; i <= rowIndex; i++) {
            // the second loop is performed in another direction
            // to avoid overwitting the previous value
            for (int j = i - 1; j >= 1; j--) {
                int tmp = ret.get(j - 1) + ret.get(j);
                ret.set(j, tmp);
            }
            ret.add(1);
        }
        return ret;
    }


// my solution
// rotate two DP lists to realize O(k) space
// speed is good
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex == 0) {
            result.add(1);
            return result;
        }
        int[] res1 = new int[rowIndex + 1];
        int[] res2 = new int[rowIndex + 1];
        res1[0] = 1;
        res2[0] = 1;
        // true means res2 is the generated result array
        boolean flag = true;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                if (flag) {
                    res2[j] = res1[j-1] + res1[j];
                } else {
                    res1[j] = res2[j-1] + res2[j];
                }
            }
            if (flag) {
                res2[i] = 1;
            } else {
                res1[i] = 1;
            }
            flag = !flag;
        }

        for (int i = 0; i <= rowIndex; i++) {
            if (!flag) {
                result.add(res2[i]);
            } else {
                result.add(res1[i]);
            }
        }
        return result;
    }
}
