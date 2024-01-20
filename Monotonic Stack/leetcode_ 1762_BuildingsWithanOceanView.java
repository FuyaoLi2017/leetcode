/**
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 */

 // This is a simple question, actually I am getting the optimal solution ..
 class Solution {
    public int[] findBuildings(int[] heights) {
        List<Integer> res = new ArrayList<>();

        int max = 0;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > max) {
                res.add(i);
                max = heights[i];
            }
        }

        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(res.size() - i - 1);
        }
        return resArr;
    }
}

// more complex version of monitonic stack
class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        
        // Monotonically decreasing stack.
        Stack<Integer> stack = new Stack<>();  
        for (int current = n - 1; current >= 0; --current) {
            // If the building to the right is smaller, we can pop it.
            while (!stack.isEmpty() && heights[stack.peek()] < heights[current]) {
                stack.pop();
            }
            
            // If the stack is empty, it means there is no building to the right 
            // that can block the view of the current building.
            if (stack.isEmpty()) { 
                list.add(current);
            }
            
            // Push the current building in the stack.
            stack.push(current);
        }
 
        // Push elements from list to answer array in reverse order.
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(list.size() - 1 - i);
        }
        
        return answer;
    }
}