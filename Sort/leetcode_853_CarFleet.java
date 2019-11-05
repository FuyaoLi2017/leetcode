/*
N cars are going to the same destination along a one lane road.  The destination is target miles away.

Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.

A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.

The distance between these two cars is ignored - they are assumed to have the same position.

A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.

If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.


How many car fleets will arrive at the destination?



Example 1:

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation:
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.

Note:

0 <= N <= 10 ^ 4
0 < target <= 10 ^ 6
0 < speed[i] <= 10 ^ 6
0 <= position[i] < target
All initial positions are different.
*/

// My solution using sort
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        if(position == null || position.length == 0) return 0;
        int count = 0;

        int[] group = new int[position.length];
        for(int i = 0; i < group.length; i++){
            group[i] = i;
        }

        List<int[]> pairs = new ArrayList<>();
        for(int i = 0; i < position.length; i++){
            pairs.add(new int[]{position[i], speed[i]});
        }

        Collections.sort(pairs, (o1, o2) -> {
            return o2[0]-o1[0];
        });

        for(int i = 1; i < pairs.size(); i++){
            int[] cur = pairs.get(i);
            int[] prev = pairs.get(group[i-1]);
            // speed is larger than the previous one
            if(cur[1] > prev[1]){
                double distanceToCatch = (double)(prev[0]-cur[0])/(cur[1]-prev[1])*cur[1];
                if(distanceToCatch <= (double)(target-cur[0])) {
                    group[i] = group[i-1];
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int g : group){
            set.add(g);
        }
        return set.size();
    }
}

// using treemap and time calculation
class Solution {
    public int carFleet(int target, int[] pos, int[] speed) {
        TreeMap<Integer, Double> m = new TreeMap<>();
        // -pos[i] is to use treemap easier without Collections.reverseOrder()
        for (int i = 0; i < pos.length; ++i) m.put(-pos[i], (double)(target - pos[i]) / speed[i]);
        int res = 0; double cur = 0;
        for (double time : m.values()) {
            if (time > cur) {
                cur = time;
                res++;
            }
        }
        return res;
    }
}
