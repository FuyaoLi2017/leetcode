/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
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
// PriorityQueue min-Heap
/*
最小堆排序MinHeap
MinHeap基本性质

最小堆中的最小元素值出现在根结点（堆顶）；
堆中每个父节点的元素值都小于等于其孩子结点（如果存在）
MinHeap用途

1.求一个数列中的第K大的数，建立一个大小为K的最小堆，堆顶就是第K大的数

2.递归去除最顶元素，用于取top K等。

MinHeap可设置容量上限N带来两个好处：

内存占用可控
因为上限N的存在，可提高添加和删除元素的速度
*/
/*
>explanation of the Algorithm

Sort the given meetings by their start time.
Initialize a new min-heap and add the first meeting's ending time to the heap. We simply need to keep track of the ending times as that tells us when a meeting room will get free.
For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
If the room is free, then we extract the topmost element and add it back with the ending time of the current meeting we are processing.
If not, then we allocate a new room and add it to the heap.
After processing all the meetings, the size of the heap will tell us the number of rooms allocated. This will be the minimum number of rooms needed to accommodate all the meetings.
*/
 public int minMeetingRooms(Interval[] intervals) {
     if (intervals == null || intervals.length == 0)
         return 0;

     // Sort the intervals by start time
     Arrays.sort(intervals, new Comparator<Interval>() {
         public int compare(Interval a, Interval b) { return a.start - b.start; }
     });

     // Use a min heap to track the minimum end time of merged intervals
     PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
         public int compare(Interval a, Interval b) { return a.end - b.end; }
     });

     // start with the first meeting, put it to a meeting room
     heap.offer(intervals[0]);

     for (int i = 1; i < intervals.length; i++) {
         // get the meeting room that finishes earliest
         Interval interval = heap.poll();

         if (intervals[i].start >= interval.end) {
             // if the current meeting starts right after
             // there's no need for a new room, merge the interval
             interval.end = intervals[i].end;
         } else {
             // otherwise, this meeting needs a new room
             heap.offer(intervals[i]);
         }

         // don't forget to put the meeting room back
         heap.offer(interval);
     }

     return heap.size();
 }

 // answer 2 for Heap
 /**
  * Definition for an interval. public class Interval { int start; int end; Interval() { start = 0;
  * end = 0; } Interval(int s, int e) { start = s; end = e; } }
  */
 class Solution {

   public int minMeetingRooms(Interval[] intervals) {

     // Check for the base case. If there are no intervals, return 0
     if (intervals.length == 0) {
       return 0;
     }

     // Min heap
     PriorityQueue<Integer> allocator =
         new PriorityQueue<Integer>(
             intervals.length,
             new Comparator<Integer>() {
               public int compare(Integer a, Integer b) {
                 return a - b;
               }
             });

     // Sort the intervals by start time
     Arrays.sort(
         intervals,
         new Comparator<Interval>() {
           public int compare(Interval a, Interval b) {
             return a.start - b.start;
           }
         });

     // Add the first meeting
     allocator.add(intervals[0].end);

     // Iterate over remaining intervals
     for (int i = 1; i < intervals.length; i++) {

       // If the room due to free up the earliest is free, assign that room to this meeting.
       if (intervals[i].start >= allocator.peek()) {
         allocator.poll();
       }

       // If a new room is to be assigned, then also we add to the heap,
       // If an old room is allocated, then also we have to add to the heap with updated end time.
       allocator.add(intervals[i].end);
     }

     // The size of the heap tells us the minimum rooms required for all the meetings.
     return allocator.size();
   }
 }


 //Chronological Ordering
 /*
 Separate out the start times and the end times in their separate arrays.
Sort the start times and the end times separately. Note that this will mess up the original correspondence of start times and end times. They will be treated individually now.
We consider two pointers: s_ptr and e_ptr which refer to start pointer and end pointer. The start pointer simply iterates over all the meetings and the end pointer helps us track if a meeting has ended and if we can reuse a room.
When considering a specific meeting pointed to by s_ptr, we check if this start timing is greater than the meeting pointed to by e_ptr. If this is the case then that would mean some meeting has ended by the time the meeting at s_ptr had to start. So we can reuse one of the rooms. Otherwise, we have to allocate a new room.
If a meeting has indeed ended i.e. if start[s_ptr] >= end[e_ptr], then we increment e_ptr.
Repeat this process until s_ptr processes all of the meetings.
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }
}
