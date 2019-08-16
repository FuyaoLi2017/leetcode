/*
In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.



Example 1:

Input: [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,2,1,2,1]


Note:

1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000
*/

// use heap to advance similar values one by next
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int[] res = new int[barcodes.length];
        if (barcodes == null || barcodes.length == 0) return new int[]{};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : barcodes) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq
                = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue() == 0 ? b.getKey() - a.getKey() :
                b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
        int counter = 0;
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> first = pq.poll();
            res[counter++] = first.getKey();
            if (!pq.isEmpty()) {
                Map.Entry<Integer, Integer> second = pq.poll();
                res[counter++] = second.getKey();
                if (second.getValue() > 1) {
                    second.setValue(second.getValue() - 1);
                    pq.add(second);
                }
            }
            if (first.getValue() > 1) {
                first.setValue(first.getValue() - 1);
                pq.add(first);
            }
        }
        return res;
    }
}
