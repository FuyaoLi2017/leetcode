/*

*/

// first solution, put Math.min(k, nums.length) elements into the priorityqueue
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0 || k == 0) return res;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);

        for(int i = 0; i < k && i < nums1.length; i++){
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }

        while(k-- > 0 && !pq.isEmpty()){
            int[] cur = pq.poll();
            res.add(Arrays.asList(cur[0], cur[1]));
            // cur[2] is the index of the nums2 array
            if(cur[2] == nums2.length-1) continue;
            pq.offer(new int[]{cur[0], nums2[cur[2]+1], cur[2]+1});
        }

        return res;
    }
}

// my solution, using dijkstra
class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // corner case
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return new ArrayList<int[]>();

        // We can imagine there is matrix with sum
        PriorityQueue<Tuple> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.sum < o2.sum) return -1;
            if (o1.sum > o2.sum) return 1;
            return 0;
        });
        // start index
        List<int[]> res = new ArrayList<>();
        // visited map
        boolean[][] visited = new boolean[nums1.length][nums2.length];
        pq.offer(new Tuple(nums1[0], nums2[0], 0, 0));
        while (!pq.isEmpty() && k > 0) {
            Tuple cur = pq.poll();
            res.add(new int[]{cur.num1, cur.num2});
            k--;
            if (cur.i < nums1.length - 1 && !visited[cur.i + 1][cur.j]) {
                pq.offer(new Tuple(nums1[cur.i + 1], nums2[cur.j], cur.i + 1, cur.j));
                visited[cur.i + 1][cur.j] = true;
            }
            if (cur.j < nums2.length - 1 && !visited[cur.i][cur.j + 1]) {
                pq.offer(new Tuple(nums1[cur.i], nums2[cur.j + 1], cur.i, cur.j + 1));
                visited[cur.i][cur.j + 1] = true;
            }
        }
        return res;
    }

    class Tuple {
        int num1;
        int num2;
        // the row index
        int i;
        // the column index
        int j;
        int sum;

        public Tuple(int num1, int num2, int i, int j) {
            this.num1 = num1;
            this.num2 = num2;
            this.i = i;
            this.j = j;
            this.sum = num1 + num2;
        }
    }
}
