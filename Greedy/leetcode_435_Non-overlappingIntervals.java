/*
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Note:

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
*/
// 这道题还是有难度的，可以看看解析
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> {
            return a[0]-b[0];
        });
        int end = intervals[0][1], prev = 0, count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[prev][1] > intervals[i][0]) {
                if (intervals[prev][1] > intervals[i][1]) {
                    prev = i;
                }
                count++;
            } else {
                prev = i;
            }
        }
        return count;
    }
}

// my new solution
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        List<int[]> meetings = new ArrayList<>();
        for(int[] interval : intervals) {
            meetings.add(new int[]{interval[0], 0});
            meetings.add(new int[]{interval[1], 1});
        }
        Collections.sort(meetings, (a, b) -> {
            if(a[0] < b[0]) return -1;
            else if (a[0] > b[0]) return 1;
            else return b[1] - a[1];
        });
        int res = 0;
        int cur = 0;
        for (int i = 0; i < meetings.size(); i++) {
            int[] meeting = meetings.get(i);
            if(meeting[1] == 0) {
                cur++;
                res = Math.max(res, cur);
            } else {
                cur--;
            }
        }
        return res;
    }
}
