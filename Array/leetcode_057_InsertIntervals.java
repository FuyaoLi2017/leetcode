/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

*/

// O(N) one pass method, short but a little bit slow
class Solution {
   public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new LinkedList<>();
    int i = 0;
    // add all the intervals ending before newInterval starts
    while (i < intervals.size() && intervals.get(i).end < newInterval.start)
        result.add(intervals.get(i++));
    // merge all overlapping intervals to one considering newInterval
    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
        newInterval = new Interval( // we could mutate newInterval here also
                Math.min(newInterval.start, intervals.get(i).start),
                Math.max(newInterval.end, intervals.get(i).end));
        i++;
    }
    result.add(newInterval); // add the union of intervals we got
    // add all the rest
    while (i < intervals.size()) result.add(intervals.get(i++));
    return result;
    }
}
// binary search method
public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new ArrayList<>();
    if (intervals == null || newInterval == null) return result;
    int iStart = findStartPos(intervals, newInterval.start);
    int iEnd = findEndPos(intervals, newInterval.end);
    if (iStart > 0 && intervals.get(iStart - 1).end >= newInterval.start) iStart--;
    if (iEnd == intervals.size() || intervals.get(iEnd).start > newInterval.end) iEnd--;

    //If not in the corner cases, this condition should apply.
    if (iStart <= iEnd) {
        newInterval = new Interval(Math.min(newInterval.start, intervals.get(iStart).start),Math.max(newInterval.end, intervals.get(iEnd).end));
    }

    int i = 0;
    while (i < iStart) result.add(intervals.get(i++));
    result.add(newInterval);
    i = iEnd + 1;
    while (i < intervals.size()) result.add(intervals.get(i++));
    return result;
}

private int findStartPos(List<Interval> intervals, int value) {
    int l = 0, r = intervals.size() - 1;
    while (l <= r) {
        int m = (l + r) >> 1;
        if (intervals.get(m).start == value) return m;
        else if (intervals.get(m).start < value) l = m + 1;
        else r = m - 1;
    }
    return l;
}

private int findEndPos(List<Interval> intervals, int value) {
    int l = 0, r = intervals.size() - 1;
    while (l <= r) {
        int m = (l + r) >> 1;
        if (intervals.get(m).end == value) return m;
        else if (intervals.get(m).end < value) l = m + 1;
        else r = m - 1;
    }
    return l;
}
