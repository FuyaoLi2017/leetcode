/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
*/
// my solution, 我一开始没注意到可以overlap，想的比较复杂了
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() == 0) return new ArrayList<String>();
        List<String> result = new ArrayList<>();
        // key: string, value: start index of 10 letter long sequence
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String current = s.substring(i, i + 10);
            if (map.containsKey(current)) {
                map.get(current).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(current, list);
            }
        }

        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<Integer> list = entry.getValue();
            if (list.size() >= 2) {
                result.add(key);
            }
        }
        return result;
    }
}

// sliding window, stefan
public List<String> findRepeatedDnaSequences(String s) {
    Set seen = new HashSet(), repeated = new HashSet();
    for (int i = 0; i + 9 < s.length(); i++) {
        String ten = s.substring(i, i + 10);
        if (!seen.add(ten))
            repeated.add(ten);
    }
    return new ArrayList(repeated);
}
