class Solution {
    public int rob(int[] num) {
        int prevMax = 0;                             //the maxmium value not considering the current house
        int currMax = 0;                             //the maxmium value considering the current house
        for (int x : num) {                          //go through every number
            int temp = currMax;                      //temp is the value that not consider the current house
            currMax = Math.max(prevMax + x, currMax);//dp solution:plus the current house / leave the previous total value
            prevMax = temp;                          //update the previous value
        }
        return currMax;
    }
}
