/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
*/

// my solution, just dfs
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1 == null || words2 == null) return false;
        if (words1.length != words2.length) return false;
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> list : pairs) {
            String first = list.get(0);
            String second = list.get(1);
            if (!map.containsKey(first)) {
                Set<String> set = new HashSet<>();
                set.add(second);
                map.put(first, set);
            } else {
                map.get(first).add(second);
            }

            if (!map.containsKey(second)) {
                Set<String> set = new HashSet<>();
                set.add(first);
                map.put(second, set);
            } else {
                map.get(second).add(first);
            }
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])
                || (map.containsKey(words1[i]) && dfs(map,  words1[i], words2[i], new HashSet<>())))
                continue;
            else return false;
        }
        return true;
    }

    private boolean dfs(Map<String, Set<String>> map, String word1, String word2, Set<String> visited) {
        if (visited.contains(word1)) return false;
        if (word1.equals(word2)) return true;
        visited.add(word1);
        Set<String> set = map.get(word1);
        Iterator iter = set.iterator();
        boolean result = false;
        while (iter.hasNext()) {
            String cur = (String)iter.next();
            if(dfs(map, cur, word2, visited)) {
                result = true;
                break;
            }
        }
        return result;
    }
}

// a better dfs, answer solution 1
class Solution {
    public boolean areSentencesSimilarTwo(
            String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, List<String>> graph = new HashMap();
        for (String[] pair: pairs) {
            for (String p: pair) if (!graph.containsKey(p)) {
                graph.put(p, new ArrayList());
            }
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        for (int i = 0; i < words1.length; ++i) {
            String w1 = words1[i], w2 = words2[i];
            Stack<String> stack = new Stack();
            Set<String> seen = new HashSet();
            stack.push(w1);
            seen.add(w1);
            search: {
                while (!stack.isEmpty()) {
                    String word = stack.pop();
                    if (word.equals(w2)) break search;
                    if (graph.containsKey(word)) {
                        for (String nei: graph.get(word)) {
                            if (!seen.contains(nei)) {
                                stack.push(nei);
                                seen.add(nei);
                            }
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }
}

// union by rank
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;
        Map<String, Integer> mp = new HashMap<>();
        int i = 0;
        UF uf = new UF(pairs.size() * 2);
        for (List<String> w : pairs) {
            mp.putIfAbsent(w.get(0),i++);
            mp.putIfAbsent(w.get(1),i++);

            uf.union(mp.get(w.get(0)), mp.get(w.get(1)));
        }
        for (int j = 0; j < words1.length; j++) {
            if (words1[j].equals(words2[j])) continue;
            int inW1 = mp.getOrDefault(words1[j], -1), inW2 = mp.getOrDefault(words2[j], -1);
            if (inW1 == -1 || inW2 == -1 || uf.find(inW1) !=  uf.find(inW2)) return false;
        }
        return true;
    }
}
// Weighted quick-union
class UF {
    int[] parent;
    int[] rank;
    public UF(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        rank = new int[n];
    }
    public int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int p , int q) {
        int pX = find(p),  qX = find(q);
        if (pX != qX) {
            if (rank[pX] > rank[qX])
                parent[qX] = pX;
            else if (rank[pX] < rank[qX])
                parent[pX] = qX;
            else {
                parent[qX] = pX;
                rank[pX]++;
            }
        }
    }
}
