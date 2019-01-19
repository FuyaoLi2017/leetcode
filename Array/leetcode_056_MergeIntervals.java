/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
 // my sorting solution
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<Interval>();
        }
        Collections.sort(intervals, (o1, o2) -> {
            if (o1.start < o2.start) return -1;
            else if (o1.start > o2.start) return 1;
            else {
                if (o1.end < o2.end) return -1;
                else if (o1.end > o2.end) return 1;
                return 0;
            }
        });

        int i = 1;
        while (i < intervals.size()) {
            int startBefore = intervals.get(i - 1).start;
            int endBefore = intervals.get(i - 1).end;
            int start = intervals.get(i).start;
            int end = intervals.get(i).end;
            if (startBefore == start) {
                intervals.remove(i - 1);
            } else if (endBefore >= start) {
                intervals.set(i, new Interval(startBefore, Math.max(endBefore, end)));
                intervals.remove(i - 1);
            } else {
                i++;
            }
        }
        return intervals;
    }
}

// just compare the first element is enough
// use two pointers to forward
public List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() <= 1)
        return intervals;

    // Sort by ascending starting point using an anonymous Comparator
    intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

    List<Interval> result = new LinkedList<Interval>();
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;

    for (Interval interval : intervals) {
        if (interval.start <= end) // Overlapping intervals, move the end if needed
            end = Math.max(end, interval.end);
        else {                     // Disjoint intervals, add the previous one and reset bounds
            result.add(new Interval(start, end));
            start = interval.start;
            end = interval.end;
        }
    }

    // Add the last interval
    result.add(new Interval(start, end));
    return result;
}
