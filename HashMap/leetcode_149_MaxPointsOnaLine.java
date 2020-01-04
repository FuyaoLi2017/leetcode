/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public int maxPoints(Point[] points) {
        	if (points==null) return 0;
        	if (points.length<=2) return points.length;

            // 除以公约数之后的x, y坐标, 对应该值的count
        	Map<Integer,Map<Integer,Integer>> map = new HashMap<Integer,Map<Integer,Integer>>();
        	int result=0;
        	for (int i=0;i<points.length;i++){
        		map.clear();
                // overlap is node at the same position like point i
                // max the current result
        		int overlap=0,max=0;
        		for (int j=i+1;j<points.length;j++){
        			int x=points[j].x-points[i].x;
        			int y=points[j].y-points[i].y;
                    // 完全重合的点
        			if (x==0&&y==0){
        				overlap++;
        				continue;
        			}
        			int gcd=generateGCD(x,y);
        			if (gcd!=0){
        				x/=gcd;
        				y/=gcd;
        			}

        			if (map.containsKey(x)){
        				if (map.get(x).containsKey(y)){
        					map.get(x).put(y, map.get(x).get(y)+1);
        				}else{
        					map.get(x).put(y, 1);
        				}
        			}else{
        				Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        				m.put(y, 1);
        				map.put(x, m);
        			}
        			max=Math.max(max, map.get(x).get(y));
        		}
        		result=Math.max(result, max+overlap+1);
        	}
        	return result;


        }
        private int generateGCD(int a,int b){

        	if (b==0) return a;
        	else return generateGCD(b,a%b);

        }
}



// new input, the same implementation
class Solution {
    public int maxPoints(int[][] points) {
        if(points == null) return 0;
        if(points.length <= 2) return points.length;

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int result = 0;

        for(int i = 0; i < points.length; i++){
            map.clear();
            int overlap = 0, max = 0;

            for(int j = i+1; j < points.length; j++){
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];

                if(x == 0 && y == 0){
                    overlap++;
                    continue;
                }


                int gcd = generateGCD(x,y);
                if(gcd != 0){
                    x /= gcd;
                    y /= gcd;
                }

                if(map.containsKey(x)){
                    map.get(x).put(y, map.get(x).getOrDefault(y,0)+1);
                } else {
                    Map<Integer, Integer> m = new HashMap<>();
                    m.put(y, 1);
                    map.put(x, m);
                }
                max = Math.max(max, map.get(x).get(y));
            }
            result = Math.max(result, max+overlap+1);
        }
        return result;
    }

    private int generateGCD(int a, int b){
        if(b == 0) return a;
        else return generateGCD(b, a%b);
    }
}
