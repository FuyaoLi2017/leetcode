/*
Given an array of strings, find if all the strings can be chained to form a circle. Two string s1 and s2 can be chained, iff the last letter of s1 is identical to the first letter of s2.

 For example,

“abc” and “cd” can be chained,

“abc” and “dz” can not be chained.

 Example Input:

arr[] = {"aaa", "bbb", "baa", "aab"};

Output: True,



The given input strings can be chained to form a circle. The strings can be chained as "aaa", "aab", "bbb" and "baa"
*/
public class FinalProb4 {
    public boolean chainCircle(String[] words) {
        if (words == null || words.length == 0) {
            return false;
        }
        return dfs(words, 1);
    }

    private boolean dfs(String[] words, int index) {
        // base case
        if (index == words.length) {
            return canChain(words[0], words[index - 1]);
        }
        // induction rule
        for (int i = index; i < words.length; i++) {
            if (canChain(words[i], words[index - 1])) {
                swap(words, i, index);
                // avoid duplicate here
                if (dfs(words, index + 1)) return true;
                swap(words, i, index);
            }
        }
        return false;
    }

    private boolean canChain(String s1, String s2) {
        return s1.charAt(0) == s2.charAt(s2.length() - 1);
    }

    private void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    public static void main(String[] args) {
        FinalProb4 test = new FinalProb4();
        String[] input =  {"aaa", "abba"};
        System.out.println(test.chainCircle(input));
    }
}
