/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
*/
// a high vote answer
public class Solution {
    class Point {
        int x,y;
        public Point(int _x, int _y) {x=_x;y=_y;}
    }
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m=maze.length, n=maze[0].length;
        if (start[0]==destination[0] && start[1]==destination[1]) return true;
        int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        boolean[][] visited=new boolean[m][n];
        LinkedList<Point> list=new LinkedList<>();
        visited[start[0]][start[1]]=true;
        list.offer(new Point(start[0], start[1]));
        while (!list.isEmpty()) {
            Point p=list.poll();
            int x=p.x, y=p.y;
            for (int i=0;i<4;i++) {
                int xx=x, yy=y;
                while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0) {
                    xx+=dir[i][0];
                    yy+=dir[i][1];
                }
                xx-=dir[i][0];
                yy-=dir[i][1];
                if (visited[xx][yy]) continue;
                visited[xx][yy]=true;
                if (xx==destination[0] && yy==destination[1]) return true;
                list.offer(new Point(xx, yy));
            }
        }
        return false;

    }
}

// my solution, could be optimized
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(start[0] == destination[0] && start[1] == destination[1]) return true;
        if(maze[destination[0]][destination[1]] == 1) return false;

        int m = maze.length;
        int n = maze[0].length;

        boolean[][] visited = new boolean[m][n];

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        Queue<int[]> q = new LinkedList<>();

        q.offer(start);

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            visited[curX][curY] = true;

            if(curX == destination[0] && curY == destination[1]) return true;

            for(int[] dir : dirs){
                int nextX = curX;
                int nextY = curY;

                if(dir[0] != 0){ // move x
                    while(nextX >= 0 && nextX < m && maze[nextX][nextY] != 1){
                        nextX += dir[0];
                    }
                    // go back one step
                    nextX -= dir[0];
                    if(!visited[nextX][nextY]){
                        q.offer(new int[]{nextX, nextY});
                    }
                } else { // move y
                    while(nextY >= 0 && nextY < n && maze[nextX][nextY] != 1){
                        nextY += dir[1];
                    }
                    // go back one step
                    nextY -= dir[1];
                    if(!visited[nextX][nextY]){
                        q.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }
        return false;
    }
}
