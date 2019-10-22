/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
*/


// 维护一个单调栈
class Solution {
    public int[] maxSlidingWindow(int[] a, int k) {
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
	}
}

// my previous solution
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];
        int len = nums.length;
        int resultLen = len-k+1;
        int[] result = new int[resultLen];

        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        for(int i = 0; i < k; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for(int i = 0; i < resultLen; i++){
            int maxNum = map.firstKey();
            result[i] = maxNum;

            if(i < resultLen-1) {
                int currentFront = nums[i];
                int currentCount = map.get(currentFront);
                if(currentCount == 1) map.remove(currentFront);
                else {
                    map.put(currentFront, currentCount-1);
                }
                int nextTail = nums[i+k];
                map.put(nextTail, map.getOrDefault(nextTail, 0) + 1);
            }
        }
        return result;
    }
}
