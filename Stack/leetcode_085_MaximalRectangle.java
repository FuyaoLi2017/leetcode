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
                    // 前面的数字大表示可以继续接着
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



// using the leetcode 84
// 水平方向一层一层切出一个截面，然后算rectangle,有1个0就置0，否则加1
public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int[] heights = new int[matrix[0].length];
        int ans = 0;
        // consider it row by row
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                if(matrix[i][j] == '0') heights[j] = 0;
                else heights[j]++;
            }
            ans = Math.max(ans,calcArea(heights));
        }
        return ans;
    }
    private int calcArea(int[] heights){                        //method of problem 84
        if(heights == null || heights.length == 0) return 0;
        int ans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for(int i = 0;i<= heights.length;i++){
            // heights这个位置取等号不取等号就是在heights这个地方判断
            while(stack.peek()!=-1 && (i==heights.length || heights[i] <  heights[stack.peek()])){
                int index = stack.pop();
                int start = stack.peek() + 1;
                ans = Math.max(ans,heights[index]*(i-start));
            }
            stack.push(i);
        }
        return ans;
    }
