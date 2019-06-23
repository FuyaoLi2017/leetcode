/*
A boomerang is a set of 3 points that are all distinct and not in a straight line.

Given a list of three points in the plane, return whether these points are a boomerang.



Example 1:

Input: [[1,1],[2,3],[3,2]]
Output: true
Example 2:

Input: [[1,1],[2,2],[3,3]]
Output: false
*/
class Solution {
    public boolean isBoomerang(int[][] points) {
        int[] first = points[0];
        int[] second = points[1];
        int[] third = points[2];
        int left = (second[0] - first[0]) * (third[1] - first[1]);
        int right = (third[0] - first[0]) * (second[1] - first[1]);
        if (left == right) return false;
        return true;
    }
}
