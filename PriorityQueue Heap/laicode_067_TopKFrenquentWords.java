/*
Given a composition with different kinds of words, return a list of the top K most frequent words in the composition.

Assumptions

the composition is not null and is not guaranteed to be sorted
K >= 1 and K could be larger than the number of distinct words in the composition, in this case, just return all the distinct words
Return

a list of words ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)
Examples

Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [“b”, “c”, "a", "d"]
*/
public class Solution {
  public String[] topKFrequent(String[] combo, int k) {
    Map<String, Integer> map = new HashMap<>();
    for (String str : combo) {
      map.put(str, map.getOrDefault(str, 0) + 1);
    }

    PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {  // In case of overflow, just compare, don't use minus to get a result
        if (o1.getValue() < o2.getValue()) return 1;
        else if (o1.getValue() > o2.getValue()) return -1;
        return 0;
      }
    });

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      pq.add(entry);
    }

    List<String> res = new ArrayList<>();
        while(res.size() < k && !pq.isEmpty()){    // 注意边界条件的处理
            Map.Entry<String, Integer> entry = pq.poll();
            res.add(entry.getKey());
        }
        return (String[])res.toArray(new String[0]);
  }
}
