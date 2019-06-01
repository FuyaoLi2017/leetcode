/*
Given a non-negative integer numRows,
generate the first numRows of Pascal's triangle.
In Pascal's triangle, each number
 is the sum of the two numbers directly above it.
*/
// my solution, iterative, This is using the previous result, actually a DP solution
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        for (int i = 0; i < numRows; i++) {
            List<Integer> current = new ArrayList<>();
            current.add(1);
            if (i > 0) {
                List<Integer> previous = res.get(i - 1);
                for (int j = 0; j < i - 1; j++) {
                    int addedValue = previous.get(j) + previous.get(j + 1);
                    current.add(addedValue);
                }
                current.add(1);
            }
            res.add(current);
        }
        return res;
    }
}
// Time complexity : O(numRows^2)
// Space complexity : O(numRows^2)
