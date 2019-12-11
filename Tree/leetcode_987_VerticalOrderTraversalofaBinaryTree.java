/*
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null) return res;

        PriorityQueue<Point> pq = new PriorityQueue(new Comparator<Point>(){
            public int compare(Point p1, Point p2){
                if (p1.x != p2.x)
                    return p1.x - p2.x;
                else if (p1.y != p2.y)
                    return p2.y - p1.y;
                else
                    return p1.val - p2.val;
            }
        });

        dfs(root, 0, 0, pq);
        int prev_x = Integer.MIN_VALUE;
        while (!pq.isEmpty()){
            Point p = pq.poll();
            if (p.x > prev_x){
                List<Integer> list = new ArrayList();
                list.add(p.val);
                res.add(list);
            }
            else{
                List<Integer> list = res.get(res.size()-1);
                list.add(p.val);
            }
            prev_x = p.x;
        }
        return res;
    }

    private void dfs(TreeNode root, int x, int y, PriorityQueue<Point> pq){
        if (root == null) return;
        pq.offer(new Point(x, y, root.val));
        dfs(root.left, x-1, y-1, pq);
        dfs(root.right, x+1, y-1, pq);
    }

    class Point{
        int x,y,val;
        Point(int x,int y,int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
