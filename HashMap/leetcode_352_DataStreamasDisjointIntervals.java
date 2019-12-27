/*
Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]


Follow up:

What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
*/
// my solution
class SummaryRanges {

    /** Initialize your data structure here. */
    TreeMap<Integer, int[]> map;
    public SummaryRanges() {
        map = new TreeMap<>();
    }

    public void addNum(int val) {
        Integer floorVal = map.floorKey(val);
        if(floorVal != null && map.get(floorVal)[1] >= val) return;

        Integer ceilingVal = map.ceilingKey(val);

        if((floorVal == null || map.get(floorVal)[1] < val-1) && (ceilingVal == null || map.get(ceilingVal)[0] > val+1)){
            map.put(val, new int[]{val, val});
            return;
        } else {
            if(floorVal != null && map.get(floorVal)[1] == val-1){
                int[] originalPair = map.get(floorVal);
                // update the original range
                if(ceilingVal != null && map.get(ceilingVal)[0] == val+1){
                    map.put(floorVal, new int[]{originalPair[0], map.get(ceilingVal)[1]});
                    map.remove(ceilingVal);
                    return;
                } else {
                    map.put(floorVal, new int[]{originalPair[0], val});
                    return;
                }
            } else if (ceilingVal != null){
                // we just want to update the upper range
                int[] originalPair = map.get(ceilingVal);
                map.put(val, new int[]{val, originalPair[1]});
                map.remove(originalPair[0]);
            }
        }
    }

    public int[][] getIntervals() {
        return map.values().toArray(new int[map.size()][2]);
    }
}
