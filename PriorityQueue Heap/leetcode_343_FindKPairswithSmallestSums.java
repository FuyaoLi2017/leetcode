/*
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence:
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence:
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
*/
class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        PriorityQueue<Tuple> pq = new PriorityQueue<>(k, (o1, o2) -> {
            if (o1.pair[0] + o1.pair[1] < o2.pair[0] + o2.pair[1]) {
                return -1;
            }
             else if (o1.pair[0] + o1.pair[1] > o2.pair[0] + o2.pair[1]) {
                return 1;
             }
            return 0;
        });
        // put the first k elements for nums1[0] + nums2[i]  (0 < i < k)
        // into pq if there exist such number of elements
        int min = Math.min(nums2.length, k);
        for (int i = 0; i < min; i++) {
            pq.offer(new Tuple(new int[]{nums1[0], nums2[i]}, 0, i));
        }
        while (k > 0 && !pq.isEmpty()) {
            Tuple cur = pq.poll();
            res.add(cur.pair);
            if (cur.nums1Index + 1 < nums1.length) {
                int firstNumber = nums1[cur.nums1Index + 1];
                int secondNumber = cur.pair[1];
                pq.offer(new Tuple(new int[]{firstNumber, secondNumber},
                                   cur.nums1Index + 1, cur.nums2Index));
            }
            k--;
        }
        return res;
    }

    class Tuple {
        int[] pair;
        int nums1Index;
        int nums2Index;
        public Tuple(int[] pair, int nums1Index, int nums2Index) {
            this.pair = pair;
            this.nums1Index = nums1Index;
            this.nums2Index = nums2Index;
        }
    }
}
