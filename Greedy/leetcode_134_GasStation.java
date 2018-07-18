class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int size = gas.length;
        int sum = 0;
        int res = 0;
        int total = 0;
        //if the sum in the loop can keep positive since one index, the oil tank will accumulate to the largest extent
        //and we will get the best outcome to reach the end in this way. since there is only one solution.
        //We can absolutely determine the answer by using greedy algorithm in this way.
        for(int i = 0; i < size; i++){
            sum += gas[i] - cost[i];  //calculate the sum continueously,
            if(sum < 0){
                total += sum;
                sum = 0;
                res = i + 1;
            }
        }
        total += sum;
        //consider the last index's influence, if in the last step of the loop, sum is positive,this sum will
        //be add into consideration in this step. If not, this step will add 0 (no influence)
        return total < 0 ? -1 : res;
    }
}
