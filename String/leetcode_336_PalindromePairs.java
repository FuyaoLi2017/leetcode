/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]
*/

// #1 String method
public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> ret = new ArrayList<>();
    if (words == null || words.length < 2) return ret;
    Map<String, Integer> map = new HashMap<String, Integer>();
    // traverse all the words
    for (int i=0; i<words.length; i++) map.put(words[i], i);
    for (int i=0; i<words.length; i++) {
        // System.out.println(words[i]);
        // seperate the words[i] into two parts and analysis these situations
        for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
            String str1 = words[i].substring(0, j);    //substring(startindex(include), endindex(exclude))
            String str2 = words[i].substring(j);       //substring(startindex)
            // 如果前半部分是回文的，那添加的pair的另一个部分要和后半部分形成reverse关系
            // 找到的pair放在前面，然后str1放在后面
            if (isPalindrome(str1)) {
                String str2rvs = new StringBuilder(str2).reverse().toString();
                if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(map.get(str2rvs));
                    list.add(i);
                    ret.add(list);
                    // System.out.printf("isPal(str1): %s\n", list.toString());
                }
            }
            // 如果后半部分是回文的，那添加的pair的另一个部分要和后半部分形成reverse关系
            // 找到的pair放在前面，然后str1放在后面
            if (isPalindrome(str2)) {
                String str1rvs = new StringBuilder(str1).reverse().toString();
                // check "str.length() != 0" to avoid duplicates
                if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length()!=0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(map.get(str1rvs));
                    ret.add(list);
                    // System.out.printf("isPal(str2): %s\n", list.toString());
                }
            }
        }
    }
    return ret;
}

private boolean isPalindrome(String str) {
    int left = 0;
    int right = str.length() - 1;
    while (left <= right) {
        if (str.charAt(left++) !=  str.charAt(right--)) return false;
    }
    return true;
}

/*
//NOTE
1. The <= in for (int j=0; j<=words[i].length(); j++) is aimed to handle empty string in the input. Consider the test case of ["a", ""];

2. Since we now use <= in for (int j=0; j<=words[i].length(); j++) instead of <. There may be duplicates in the output (consider test case ["abcd", "dcba"]). Therefore I put a str2.length()!=0 to avoid duplicates.

Another way to avoid duplicates is to use Set<List<Integer>> ret = new HashSet<>(); and return new ArrayList<>(ret);
*/

// #2 Trie method
https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n*k2)-java-solution-with-Trie-structure
