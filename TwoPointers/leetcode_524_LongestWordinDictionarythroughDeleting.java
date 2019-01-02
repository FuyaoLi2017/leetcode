/*
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
*/
// my solution
class Solution {
    public String findLongestWord(String s, List<String> d) {
        List<String> candidate = new ArrayList<>();
        for (String dict : d) {
            // index in s
            int sIndex = 0, dIndex = 0;
            while (sIndex < s.length() && dIndex < dict.length()) {
                if (s.charAt(sIndex) == dict.charAt(dIndex)) {
                    sIndex++;
                    dIndex++;
                } else {
                    sIndex++;
                }
            }
            if (dIndex == dict.length()) {
                candidate.add(dict);
            }
        }
        if (candidate.size() == 0) {
            return "";
        }
        Collections.sort(candidate);
        int max = 0;
        String result = "";
        for (String res : candidate) {
            if (res.length() > max) {
                result = res;
                max = res.length();
            }
        }
        return result;
    }
}

// more concise sorting
public String findLongestWord(String s, List<String> d) {
    Collections.sort(d, (a,b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) :  a.compareTo(b));
    for (String dictWord : d) {
        int i = 0;
        for (char c : s.toCharArray())
            if (i < dictWord.length() && c == dictWord.charAt(i)) i++;
        if (i == dictWord.length()) return dictWord;
    }
    return "";
}
/*
Time complexity : O(n*x*logn + n*x)O(n*x*logn+n*x).
Here nn refers to the number of strings in list dd and xx refers to average string length.
Sorting takes O(nlogn) and isSubsequence takes O(x) to check whether a string is a subsequence of another string or not.

Space complexity : O(logn)O(logn). Sorting takes O(logn)O(logn) space in average case.
*/
// without sorting
public String findLongestWord(String s, List<String> d) {
    String longest = "";
    for (String dictWord : d) {
        int i = 0;
        for (char c : s.toCharArray())
            if (i < dictWord.length() && c == dictWord.charAt(i)) i++;

        if (i == dictWord.length() && dictWord.length() >= longest.length())
            if (dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0)
                longest = dictWord;
    }
    return longest;
}
/*
Time complexity : O(n*x)O(n*x). One iteration over all strings is required.
Here nn refers to the number of strings in list dd and xx refers to average string length.

Space complexity : O(x). longest variable is used.
*/
