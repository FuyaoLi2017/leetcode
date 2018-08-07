public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix==null||matrix.length==0||matrix[0].length==0)
            return 0;
        int cLen = matrix[0].length;    // column length
        int rLen = matrix.length;       // row length
        // height array
        int[] h = new int[cLen+1];
        h[cLen]=0;
        int max = 0;


        for (int row=0;row<rLen;row++) {
            Stack<Integer> s = new Stack<Integer>();
            for (int i=0;i<cLen+1;i++) {
              // calculate the height of the rectangle, loop i once, the h[i] will increase 1
              // memorize in the ith column, how many '1' is above it.
                if (i<cLen)
                    if(matrix[row][i]=='1')
                        h[i]+=1;
                    else h[i]=0;
                // push the minimum height of '1' in the rectangle into the Stack
                // 木桶理论，最低的版子决定最高的高度
                if (s.isEmpty()||h[s.peek()]<=h[i])
                    s.push(i);
                    //上面的一行是push进去比较高的一项，当前项比较大的时候，push进去？？感觉有点问题
                else {
                    while(!s.isEmpty()&&h[i]<h[s.peek()]){
                      // 非空而且当前循环到的 i 比较小的时候
                        int top = s.pop();
                        int area = h[top]*(s.isEmpty()?i:(i-s.peek()-1));  //not understand
                        if (area>max)
                            max = area;
                    }
                    s.push(i);
                }
            }
        }
        return max;
    }
}
