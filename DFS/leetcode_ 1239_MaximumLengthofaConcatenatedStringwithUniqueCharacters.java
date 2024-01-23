/**
 * You are given an array of strings arr. A string s is formed by the concatenation 
 * of a subsequence of arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be derived from another array by deleting
 some or no elements without changing the order of the remaining elements.
 */

 // bit manipulation
 class Solution {
    public int maxLength(List<String> arr) {        
        // Pre-process arr with an optimizing helper
        // which converts each word to its character bitset
        // and then uses a set to prevent duplicate results
        Set<Integer> optSet = new HashSet<>();
        for (String word : arr) 
            wordToBitSet(optSet, word);
        
        // Convert the set back to an array for iteration
        // then start up the recursive helper
        int[] optArr = new int[optSet.size()];
        int i = 0;
        for (Integer num : optSet)
            optArr[i++] = num;
        return dfs(optArr, 0, 0);
    }
    
    private void wordToBitSet(Set<Integer> optSet, String word) {        
        // Initialize an empty int to use as a character bitset
        int charBitSet = 0;
        for (char c : word.toCharArray()) {            
            // If the bitset contains a duplicate character
            // then discard this word with an early return
            // otherwise add the character to the bitset
            int mask = 1 << c - 'a';
            if ((charBitSet & mask) > 0)
                return;
            charBitSet += mask;
        }
        
        // Store the length of the word in the unused space
        // then add the completed bitset to our optimized set
        optSet.add(charBitSet + (word.length() << 26));
    }
    
    private int dfs(int[] optArr, int pos, int res) {        
        // Separate the parts of the bitset res
        int oldChars = res & ((1 << 26) - 1);
        int oldLen = res >> 26;
        int best = oldLen;
        
        // Iterate through the remaining results
        for (int i = pos; i < optArr.length; i++) {
            int newChars = optArr[i] & ((1 << 26) - 1);
            int newLen = optArr[i] >> 26;
            
            // If the two bitsets overlap, skip to the next result
            if ((newChars & oldChars) != 0)
                continue;
            
            // Combine the two results and trigger the next recursion
            int newRes = newChars + oldChars + (newLen + oldLen << 26);
            best = Math.max(best, dfs(optArr, i + 1, newRes));
        }
        return best;
    }
}

// backtracking
class Solution {
    public int maxLength(List<String> arr) {    
        // Use depth first search recursion through arr
        // with backracking and a map for results
        return backtracking(arr, 0, new HashMap<Character, Integer>());
    }
    
    private int backtracking(List<String> arr, int pos, Map<Character, Integer> resMap) {    
        // Check for duplicate characters
        for (Integer val : resMap.values())
            if (val > 1)
                return 0;

        // Recurse through each possible next option
        // and find the best answer
        int best = resMap.size();
        for (int i = pos; i < arr.size(); i++) {
            // Add the current word to the result map
            // and recurse to the next position
            char[] wordArr = arr.get(i).toCharArray();
            for (char c : wordArr)
                resMap.put(c, resMap.getOrDefault(c, 0) + 1);
            best = Math.max(best, backtracking(arr, i + 1, resMap));

            // Backtrack the result map before continuing
            for (char c : wordArr) {
                if (resMap.get(c) == 1) {
                    resMap.remove(c);
                } else {
                    resMap.put(c, resMap.get(c) - 1);
                }
            }
        }
        return best;
    }
}