/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/
// BFS
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for(String str : wordList) {
            dict.add(str);
        }
        if (!dict.contains(endWord)) return 0;

        Queue<String> q = new LinkedList<>();;
        q.add(beginWord);

        int l = beginWord.length();
        int step = 0;

        while (!q.isEmpty()) {
                step++;
                System.out.println("step: " + step);
                for (int size = q.size(); size > 0; size--) {
                String w = q.poll();
                    System.out.println("polled string: " + w);
                for (int i = 0; i < l; i++) {
                    char[] chs = w.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++) {
                        char old = chs[i];
                        chs[i] = j;
                        String target = String.valueOf(chs);
                        // Found the solution
                        if (target.equals(endWord)) {
                            System.out.println("end: " + step);
                            return step + 1;
                        }
                        // Not in dict, skip it
                        if (!dict.contains(target)) continue;
                        // Remove new word from dict
                        dict.remove(target);
                        // Add new word into queue
                        q.add(target);
                        // resume chs[i]
                        chs[i] = old;
                    }
                }
            }

        }
        return 0;
    }
}

// bidirectional BFS
// TC: 26的length / 2次方
public class Solution {

public int ladderLength(String beginWord, String endWord, List<String> wordList) {

	Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
    if (!wordList.contains(endWord))
        return 0;
	int len = 1;
	int strLen = beginWord.length();
	HashSet<String> visited = new HashSet<String>();

	beginSet.add(beginWord);
	endSet.add(endWord);
	while (!beginSet.isEmpty() && !endSet.isEmpty()) {
		if (beginSet.size() > endSet.size()) {
			Set<String> set = beginSet;
			beginSet = endSet;
			endSet = set;
		}

		Set<String> temp = new HashSet<String>();
		for (String word : beginSet) {
			char[] chs = word.toCharArray();

			for (int i = 0; i < chs.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char old = chs[i];
					chs[i] = c;
					String target = String.valueOf(chs);

					if (endSet.contains(target)) {
						return len + 1;
					}

					if (!visited.contains(target) && wordList.contains(target)) {
						temp.add(target);
						visited.add(target);
					}
					chs[i] = old;
				}
			}
		}

		beginSet = temp;
		len++;
	}

	return 0;
}
}
