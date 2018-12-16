/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
*/


// bfs, or Greeedy???
class Solution {
     int jump(int A[]) {
     int n = A.length;
	 if(n<2)return 0;
	 int level=0,currentMax=0,i=0,nextMax=0;

	 while(currentMax-i+1>0){		//nodes count of current level>0
		 level++;
		 for(;i<=currentMax;i++){	//traverse current level , and update the max reach of next level
			nextMax=Math.max(nextMax,A[i]+i);
			if(nextMax>=n-1)return level;   // if last element is in level+1,  then the min jump=level
		 }
		 currentMax=nextMax;
	 }
	 return 0;
 }
}

// below solutions consider the unreachable state, return -1. the solution above didn't coonsider that

// LAICODE Greeedypublic class Solution {
  public int minJump(int[] array) {
    if (array == null || array.length == 1) {
      return 0;
    }
    int jump = 0;
    int cur = 0;
    int next = 0;
    for (int i = 0; i < array.length; i++) {
      if (i > cur) {
        // jump one more step
        jump++;
        if (cur == next) {
          return -1;
        }
        cur = next;
      }
      next = Math.max(next, array[i] + i);
    }
    return jump;
  }
}


//DP solution, LAICODE

public class Solution {
  public int minJump(int[] array) {
    // using DP
    if (array == null || array.length == 0) {
      return -1;
    }
    int[] dp = new int[array.length];
    dp[0] = 0;
    for (int i = 1; i < array.length; i++) {
      dp[i] = -1;
      for (int j = i - 1; j >= 0; j--) {
        if (array[j] + j >= i && dp[j] != -1) {
          if (dp[i] == -1 || dp[i] > dp[j] + 1) {
            dp[i] = dp[j] + 1;
          }
        }
      }
    }
    return dp[array.length - 1];
  }
}
