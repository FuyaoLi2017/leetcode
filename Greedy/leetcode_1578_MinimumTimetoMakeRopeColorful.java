/**
 * Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.

Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.

Return the minimum time Bob needs to make the rope colorful.
 */

 // My solution using TreeMap, it is kind of hack
 class Solution {
    public int minCost(String colors, int[] neededTime) {
        int cost = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < neededTime.length; i++) {
            map.put(i, neededTime[i]);
        }

        char[] arr = colors.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            Integer prevIndex = map.lowerKey(i);

            char cur = arr[i];
            char prev = arr[prevIndex];
            if (cur == prev) {
                int prevCost = map.get(prevIndex);
                int curCost = map.get(i);
                if (prevCost < curCost) {
                    cost += prevCost;
                    map.remove(prevIndex);
                } else {
                    cost += curCost;
                    map.remove(i);
                }
            }
        }

        return cost;
    }
}

// two pointers to do greedy
// find the consecutive same color baloons and only keep the one with highest cost
class Solution {
    public int minCost(String colors, int[] neededTime) {
        // totalTime: total time needed to make rope colorful;
        // currMaxTime: maximum time of a balloon needed.
        int totalTime = 0, currMaxTime = 0;
        
        // For each balloon in the array:
        for (int i = 0; i < colors.length(); ++i) {
            // If this balloon is the first balloon of a new group
            // set the currMaxTime as 0.
            if (i > 0 && colors.charAt(i) != colors.charAt(i - 1)) {
                currMaxTime = 0;
            }
            
            // Increment totalTime by the smaller one.
            // Update currMaxTime as the larger one.
            totalTime += Math.min(currMaxTime, neededTime[i]);
            currMaxTime = Math.max(currMaxTime, neededTime[i]);
        }
        
        // Return totalTime as the minimum removal time.
        return totalTime;
    }
}

// enhanced 1 pass solution, keep track of the totalTime and avoid adding the currMaxTime
class Solution {
    public int minCost(String colors, int[] neededTime) {
        // totalTime: total time needed to make rope colorful;
        // currMaxTime: maximum time of a balloon needed.
        int totalTime = 0, currMaxTime = 0;
        
        // For each balloon in the array:
        for (int i = 0; i < colors.length(); ++i) {
            // If this balloon is the first balloon of a new group
            // set the currMaxTime as 0.
            if (i > 0 && colors.charAt(i) != colors.charAt(i - 1)) {
                currMaxTime = 0;
            }
            
            // Increment totalTime by the smaller one.
            // Update currMaxTime as the larger one.
            totalTime += Math.min(currMaxTime, neededTime[i]);
            currMaxTime = Math.max(currMaxTime, neededTime[i]);
        }
        
        // Return totalTime as the minimum removal time.
        return totalTime;
    }
}