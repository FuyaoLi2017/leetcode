/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true
*/

// my solution
class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        // extract the topological order in pairs
        if(seqs == null || seqs.size() == 0) return false;
        // dependency map
        Map<Integer, List<Integer>> dependencies = new HashMap<>();
        // degree map
        Map<Integer, Integer> degree = new HashMap<>();

        // make sure every number in the topo sort have a unique degree
        for(List<Integer> seq : seqs){
            if(seq.size() == 0) continue;
            if(seq.size() == 1 && !degree.containsKey(seq.get(0))) {
                degree.put(seq.get(0), 0);
            }
            for(int i = 0; i < seq.size()-1; i++){
                int from = seq.get(i);
                int to = seq.get(i+1);
                dependencies.putIfAbsent(from, new ArrayList<>());
                dependencies.putIfAbsent(to, new ArrayList<>());
                degree.putIfAbsent(from, 0);
                degree.putIfAbsent(to, 0);
                dependencies.get(from).add(to);
                degree.put(to, degree.get(to)+1);
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(Map.Entry<Integer, Integer> entry : degree.entrySet()){
            if(entry.getValue() == 0) {
                q.offer(entry.getKey());
            }
            if(q.size() > 1) return false;
        }

        // not a direct indegree
        List<Integer> constructRes = new ArrayList<>();
        while(!q.isEmpty() && q.size() < 2){
            int cur = q.poll();
            constructRes.add(cur);
            List<Integer> depList = dependencies.get(cur);
            if(depList != null){
                for(int num : depList){
                    int updateVal = degree.get(num)-1;
                    if(updateVal > 0){
                        degree.put(num, updateVal);
                    } else {
                        q.offer(num);
                    }
                }
            }
        }

        if(q.size() > 1) return false;

        // basic case
        if(constructRes.size() != org.length) return false;

        // inner cycle
        if(degree.size() != constructRes.size()) return false;

        for(int i = 0; i < org.length; i++){
            if(org[i] != constructRes.get(i)) return false;
        }

        return true;
    }
}
