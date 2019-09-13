/*
A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.



Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: "abcd"
Output: ["abcd"]


Note:

1 <= S.length <= 50
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.
*/
// my solution
class Solution {
    public String[] expand(String S) {
        if (S == null || S.length() == 0) return new String[0];
        List<String> result = new ArrayList<>();
        dfs(result, new StringBuilder(), S, 0);
        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    private void dfs(List<String> result, StringBuilder sb, String S, int index){
        if(index == S.length()) {
            result.add(sb.toString());
            return;
        }
        char current = S.charAt(index);
        if (current != '{'){
            sb.append(current);
            dfs(result, sb, S, index+1);
            sb.deleteCharAt(sb.length()-1);
        } else {
            int curIdx = index;
            while (S.charAt(curIdx) != '}') {
                curIdx++;
            }
            String[] options = S.substring(index+1, curIdx).split(",");
            for (String str : options) {
                sb.append(str);
                dfs(result, sb, S, curIdx+1);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}

// treeset solution
class Solution {
    public String[] expand(String S) {
		// TreeSet to sort
        TreeSet<String> set = new TreeSet<>();
        if (S.length() == 0) {
            return new String[]{""};
        } else if (S.length() == 1) {
            return new String[]{S};
        }
        if (S.charAt(0) == '{') {
            int i = 0; // keep track of content in the "{content}"
            while (S.charAt(i) != '}') {
                i++;
            }
            String sub = S.substring(1, i);
            String[] subs = sub.split(",");
            String[] strs = expand(S.substring(i + 1)); // dfs
            for (int j = 0; j < subs.length; j++) {
                for (String str : strs) {
                    set.add(subs[j] + str);
                }
            }
        } else {
            String[] strs = expand(S.substring(1));
            for (String str : strs) {
                set.add(S.charAt(0) + str);
            }
        }
        return set.toArray(new String[0]);
    }
}
