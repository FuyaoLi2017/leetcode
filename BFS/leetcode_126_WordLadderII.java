/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

// 1). Use BFS to find the shortest distance between start and end,
//  tracing the distance of crossing nodes from start node to end node,
//  and store node's next level neighbors to HashMap;
//
// 2). Use DFS to output paths with the same distance as the shortest distance
// from distance HashMap: compare if the distance of the next level node equals
// the distance of the current node + 1.
public List<List<String>> findLadders(String start, String end, List<String> wordList) {
   HashSet<String> dict = new HashSet<String>(wordList);
   List<List<String>> res = new ArrayList<List<String>>();
   HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
   HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
   ArrayList<String> solution = new ArrayList<String>();

   dict.add(start);
   bfs(start, end, dict, nodeNeighbors, distance);
   dfs(start, end, dict, nodeNeighbors, distance, solution, res);
   return res;
}

// BFS: Trace every node's distance from the start node (level by level).
private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
  for (String str : dict)
      nodeNeighbors.put(str, new ArrayList<String>());

  Queue<String> queue = new LinkedList<String>();
  queue.offer(start);
  distance.put(start, 0);

  while (!queue.isEmpty()) {
      int count = queue.size();
      boolean foundEnd = false;
      for (int i = 0; i < count; i++) {
          String cur = queue.poll();
          int curDistance = distance.get(cur);
          ArrayList<String> neighbors = getNeighbors(cur, dict);

          for (String neighbor : neighbors) {
              nodeNeighbors.get(cur).add(neighbor);
              if (!distance.containsKey(neighbor)) {// Check if visited
                  distance.put(neighbor, curDistance + 1);
                  if (end.equals(neighbor))// Found the shortest path
                      foundEnd = true;
                  else
                      queue.offer(neighbor);
                  }
              }
          }

          if (foundEnd)
              break;
      }
  }

  // Find all next level nodes.
private ArrayList<String> getNeighbors(String node, Set<String> dict) {
  ArrayList<String> res = new ArrayList<String>();
  char chs[] = node.toCharArray();

  for (char ch ='a'; ch <= 'z'; ch++) {
      for (int i = 0; i < chs.length; i++) {
          if (chs[i] == ch) continue;
          char old_ch = chs[i];
          chs[i] = ch;
          if (dict.contains(String.valueOf(chs))) {
              res.add(String.valueOf(chs));
          }
          chs[i] = old_ch;
      }

  }
  return res;
}

// DFS: output all paths with the shortest distance.
private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
    solution.add(cur);
    if (end.equals(cur)) {
       res.add(new ArrayList<String>(solution));
    } else {
       for (String next : nodeNeighbors.get(cur)) {
            if (distance.get(next) == distance.get(cur) + 1) {
                 dfs(next, end, dict, nodeNeighbors, distance, solution, res);
            }
        }
    }
   solution.remove(solution.size() - 1);
}


// two end bfs
public List<List<String>> findLadders(String start, String end, Set<String> dict) {
  // hash set for both ends
  Set<String> set1 = new HashSet<String>();
  Set<String> set2 = new HashSet<String>();

  // initial words in both ends
  set1.add(start);
  set2.add(end);

  // we use a map to help construct the final result
  Map<String, List<String>> map = new HashMap<String, List<String>>();

  // build the map
  helper(dict, set1, set2, map, false);

  List<List<String>> res = new ArrayList<List<String>>();
  List<String> sol = new ArrayList<String>(Arrays.asList(start));

  // recursively build the final result
  generateList(start, end, map, sol, res);

  return res;
}

boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
  if (set1.isEmpty()) {
    return false;
  }

  if (set1.size() > set2.size()) {
    return helper(dict, set2, set1, map, !flip);
  }

  // remove words on current both ends from the dict
  dict.removeAll(set1);
  dict.removeAll(set2);

  // as we only need the shortest paths
  // we use a boolean value help early termination
  boolean done = false;

  // set for the next level
  Set<String> set = new HashSet<String>();

  // for each string in end 1
  for (String str : set1) {
    for (int i = 0; i < str.length(); i++) {
      char[] chars = str.toCharArray();

      // change one character for every position
      for (char ch = 'a'; ch <= 'z'; ch++) {
        chars[i] = ch;

        String word = new String(chars);

        // make sure we construct the tree in the correct direction
        String key = flip ? word : str;
        String val = flip ? str : word;

        List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();

        if (set2.contains(word)) {
          done = true;

          list.add(val);
          map.put(key, list);
        }

        if (!done && dict.contains(word)) {
          set.add(word);

          list.add(val);
          map.put(key, list);
        }
      }
    }
  }

  // early terminate if done is true
  return done || helper(dict, set2, set, map, !flip);
}

void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
  if (start.equals(end)) {
    res.add(new ArrayList<String>(sol));
    return;
  }

  // need this check in case the diff between start and end happens to be one
  // e.g "a", "c", {"a", "b", "c"}
  if (!map.containsKey(start)) {
    return;
  }

  for (String word : map.get(start)) {
    sol.add(word);
    generateList(word, end, map, sol, res);
    sol.remove(sol.size() - 1);
  }
}


// bi-directional bfs
class Solution {
    // bi-directional bfs
    public List<List<String>> findLadders(String beginWord,
String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        Map<String, List<String>> map = new HashMap<>();
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        startSet.add(beginWord);
        bfs(startSet, endSet, map, dict, false);
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(res, list, beginWord, endWord, map);
        return res;
}
    private void dfs(List<List<String>> res, List<String>
list, String word, String endWord, Map<String, List<String>>
map) {
        if (word.equals(endWord)) {
            res.add(new ArrayList(list));
            return;
}
        if (map.get(word) == null) return;
        for (String next : map.get(word)) {
            list.add(next);
            dfs(res, list, next, endWord, map);

             list.remove(list.size() - 1);
        }
}
    private void bfs(Set<String> startSet, Set<String> endSet,
Map<String, List<String>> map, Set<String> dict, boolean
reverse) {
        if (startSet.size() == 0) return;
        if (startSet.size() > endSet.size()) {
            bfs(endSet, startSet, map, dict, !reverse);
            return;
}
        Set<String> tmp = new HashSet<>();
        dict.removeAll(startSet);
        boolean finish = false;
        for (String s : startSet) {
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char old = chs[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chs[i] = c;
                    String word = new String(chs);
                    if (dict.contains(word)) {
                        if (endSet.contains(word)) {
                            finish = true;
                        } else {
                            tmp.add(word);
                        }
                        String key = reverse ? word : s;
                        String val = reverse ? s : word;
                        if (map.get(key) == null) {
                            map.put(key, new ArrayList<>());
                        }
                        map.get(key).add(val);

} }
                chs[i] = old;
            }
}
        if (!finish) {
            bfs(tmp, endSet, map, dict, reverse);
} }
}
