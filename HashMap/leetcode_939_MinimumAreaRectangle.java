/*
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
*/

// a very brute  force but intuitive solution, I am thinking about a similar idea, but I didn't implement it
class Solution {
    public int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> rows = new TreeMap();
        for (int[] point: points) {
            int x = point[0], y = point[1];
            rows.computeIfAbsent(x, z-> new ArrayList()).add(y);
        }

        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> lastx = new HashMap();
        for (int x: rows.keySet()) {
            List<Integer> row = rows.get(x);
            Collections.sort(row);
            for (int i = 0; i < row.size(); ++i)
                for (int j = i+1; j < row.size(); ++j) {
                    int y1 = row.get(i), y2 = row.get(j);
                    int code = 40001 * y1 + y2;
                    if (lastx.containsKey(code))
                        ans = Math.min(ans, (x - lastx.get(code)) * (y2-y1));
                    lastx.put(code, x);
                }
        }

        return ans < Integer.MAX_VALUE ? ans : 0;
    }
}

// check 对角线，看看是不是4个点存在
class Solution {
    public int minAreaRect(int[][] points) {
        Set<Integer> pointSet = new HashSet();
        for (int[] point: points)
            pointSet.add(40001 * point[0] + point[1]);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; ++i)
            for (int j = i+1; j < points.length; ++j) {
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
                    if (pointSet.contains(40001 * points[i][0] + points[j][1]) &&
                            pointSet.contains(40001 * points[j][0] + points[i][1])) {
                        ans = Math.min(ans, Math.abs(points[j][0] - points[i][0]) *
                                            Math.abs(points[j][1] - points[i][1]));
                    }
                }
            }

        return ans < Integer.MAX_VALUE ? ans : 0;
    }
}
