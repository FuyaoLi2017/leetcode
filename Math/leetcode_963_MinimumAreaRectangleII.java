/*
Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the x and y axes.

If there isn't any rectangle, return 0.
*/


// Solution1: 利用矩形对角线相等且互相平分的性质
// 用矩形的中心和对角线的长度作为hash value建立map
class Solution {
    public double minAreaFreeRect(int[][] points) {
        int len = points.length;
        double res = Double.MAX_VALUE;
        if (len < 4) return 0.0;
        Map<String, List<int[]>> map = new HashMap<>(); // int[] is the index of two points forming the diagonal
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                long dis = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                double centerX = (double)(points[j][0] + points[i][0])/2; // centerX and centerY is the coordinate of the diagonal center
                double centerY = (double)(points[j][1] + points[i][1])/2;
                String key = "" + dis + "+" + centerX + "+" + centerY; // key includes the length of the diagonal and the coordinate of the diagonal center
                if (map.get(key) == null) map.put(key, new ArrayList<int[]>());
                map.get(key).add(new int[]{i,j});
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                List<int[]> list = map.get(key);
                for (int i = 0; i < list.size(); i++) { // there could be multiple rectangles inside
                    for (int j = i + 1; j < list.size(); j++) {
                        int p1 = list.get(i)[0]; // p1, p2 and p3 are the three vertices of a rectangle
                        int p2 = list.get(j)[0];
                        int p3 = list.get(j)[1];
                        // len1 and len2 are the length of the sides of a rectangle
                        double len1 = Math.sqrt((points[p1][0] - points[p2][0]) * (points[p1][0] - points[p2][0]) +  (points[p1][1] - points[p2][1]) * (points[p1][1] - points[p2][1]));
                        double len2 = Math.sqrt((points[p1][0] - points[p3][0]) * (points[p1][0] - points[p3][0]) +  (points[p1][1] - points[p3][1]) * (points[p1][1] - points[p3][1]));
                        double area = len1 * len2;
                        res = Math.min(res, area);
                    }
                }
            }
        }
        return res == Double.MAX_VALUE ?  0.0 : res;
    }
}


// Solution2: 查找3个点的位置，然后确定第四个点是不是存在

import java.awt.Point;

class Solution {
    public double minAreaFreeRect(int[][] points) {
        int N = points.length;

        Point[] A = new Point[N];
        Set<Point> pointSet = new HashSet();
        for (int i = 0; i < N; ++i) {
            A[i] = new Point(points[i][0], points[i][1]);
            pointSet.add(A[i]);
        }

        double ans = Double.MAX_VALUE;
        for (int i = 0; i < N-2; ++i) {
            Point p1 = A[i];
            for (int j = i+1; j < N-1; ++j) {
                Point p2 = A[j];
                for (int k = j+1; k < N; ++k) {
                    Point p3 = A[k];
                    Point p4 = new Point(p2.x + p3.x - p1.x, p2.y + p3.y - p1.y);

                    // 利用点积为0代表夹角为90度
                    if (pointSet.contains(p4)) {
                        int dot = ((p2.x - p1.x) * (p3.x - p1.x) +
                                   (p2.y - p1.y) * (p3.y - p1.y));
                        if (dot == 0) {
                            double area = p1.distance(p2) * p1.distance(p3);
                            if (area < ans)
                                ans = area;
                        }
                    }
                }
            }
        }

        return ans < Double.MAX_VALUE ? ans : 0;
    }
}
