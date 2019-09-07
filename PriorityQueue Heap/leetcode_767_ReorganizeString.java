/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
*/

// my solution
class Solution {
    public String reorganizeString(String S) {
        int[] count = new int[26];
        for(int i = 0; i < S.length(); i++) {
            count[S.charAt(i) - 'a']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                pq.offer(new int[]{i, count[i]});
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (first[1] > 1 && pq.isEmpty()) return "";
            if (first[1] == 1 && pq.isEmpty()) {
                sb.append((char)('a'+first[0]));
                break;
            }
            int[] second = pq.poll();

            int pairs = Math.min(first[1], second[1]);
            while(pairs > 0) {
                sb.append((char)('a'+first[0]));
                sb.append((char)('a'+second[0]));
                pairs--;
            }
            if (first[1] > second[1]) pq.offer(new int[]{first[0], first[1]-second[1]});
        }
        return sb.toString();
    }
}
