/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.
*/

public class LongestRectangle {
    // brute force
    public int largestRectangleArea(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                int minheight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++)
                    minheight = Math.min(minheight, heights[k]);
                maxarea = Math.max(maxarea, minheight * (j - i + 1));
            }
        }
        return maxarea;
    }

    // using the previous min value
    public int largestRectangleArea2(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minheight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minheight = Math.min(minheight, heights[j]);
                maxarea = Math.max(maxarea, minheight * (j - i + 1));
            }
        }
        return maxarea;
    }

// using stack
    public int largestRectangleArea3(int[] heights) {
            // push the index into the stack
            Stack< Integer > stack = new Stack < > ();
            stack.push(-1);
            int maxarea = 0;
            for (int i = 0; i < heights.length; ++i) {
                // 当stack不是只有-1，以及stack的pop出来的元素的高度大于height时候可以这样做
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                    maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
                stack.push(i);
            }
            while (stack.peek() != -1){
                // the element is already poped out, so the peeked element is actually one position before the
                // calculated value, so -1 at the end, just to keep values to be consistent
                maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
            }

            return maxarea;
        }



// more concise version using Stackprivate int calcArea(int[] heights){                        //method of problem 84
    if(heights == null || heights.length == 0) return 0;
    int ans = 0;
    LinkedList<Integer> stack = new LinkedList<>();
    stack.push(-1);
    for(int i = 0;i<= heights.length;i++){
        while(stack.peek()!=-1 && (i==heights.length || heights[i] <  heights[stack.peek()])){
            int index = stack.pop();
            int start = stack.peek() + 1;
            ans = Math.max(ans,heights[index]*(i-start));
        }
        stack.push(i);
    }
    return ans;
}
}
