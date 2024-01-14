/**
 * Two strings are considered close if you can attain one from the other using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.


 */

 // my solution using sorting and hashtable
 class Solution {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() !=  word2.length()) return false;

        Map<Character, Integer> count1 = new HashMap<>();
        Map<Character, Integer> count2 = new HashMap<>();

        for (int i = 0; i < word1.length(); i++) {
            count1.put(word1.charAt(i), count1.getOrDefault(word1.charAt(i), 0) + 1);
            count2.put(word2.charAt(i), count2.getOrDefault(word2.charAt(i), 0) + 1);
        }

        if (!count1.keySet().equals(count2.keySet())) return false;

        // the occurence for different characters
        List<Integer> countList1 = new ArrayList<>();
        List<Integer> countList2 = new ArrayList<>();

        for (Integer i : count1.values()) {
            countList1.add(i);
        }

        for (Integer i : count2.values()) {
            countList2.add(i);
        }

        Collections.sort(countList1);
        Collections.sort(countList2);

        return countList1.equals(countList2);
    }
}


// Editoral solution
// this can be a frequency map using int[26]
// for the keySet, this can be further improved to use bitmap to resemble the key distribution
class Solution {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> word1Map = new HashMap<>();
        Map<Character, Integer> word2Map = new HashMap<>();
        for (char c : word1.toCharArray()) {
            word1Map.put(c, word1Map.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            word2Map.put(c, word2Map.getOrDefault(c, 0) + 1);
        }
        if (!word1Map.keySet().equals(word2Map.keySet())) {
            return false;
        }
        List<Integer> word1FrequencyList = new ArrayList<>(word1Map.values());
        List<Integer> word2FrequencyList = new ArrayList<>(word2Map.values());
        Collections.sort(word1FrequencyList);
        Collections.sort(word2FrequencyList);
        return word1FrequencyList.equals(word2FrequencyList);
    }
}