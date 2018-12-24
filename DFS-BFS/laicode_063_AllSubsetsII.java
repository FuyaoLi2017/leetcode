/*
Given a set of characters represented by a String, return a list containing all subsets of the characters.

Assumptions

There could be duplicate characters in the original set.
​Examples

Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"]
Set = "", all the subsets are [""]
Set = null, all the subsets are []
*/
// DFS - 1
public class Solution {
  public List<String> subSets(String set) {
    List<String> res = new ArrayList<>();
    if (set == null)
      return res;
    char[] array = set.toCharArray();
    Arrays.sort(array);
    helper(res, array, 0, new StringBuilder());
    return res;
  }

  private void helper(List<String> res, char[] array, int index, StringBuilder sb) {
    if (index == array.length) {
      res.add(sb.toString());
      return;
    }
    helper(res, array, index + 1, sb.append(array[index]));
    sb.deleteCharAt(sb.length() - 1);
    while (index < array.length - 1 && array[index] == array[index + 1]) {
      index++;
    }
    helper(res, array, index + 1, sb);
  }
}

// DFS - 2
public class Solution {
  public List<String> subSets(String set) {
    List<String> res = new ArrayList<>();
    if (set == null)
      return res;
    char[] array = set.toCharArray();
    Arrays.sort(array);
    helper(res, array, 0, new StringBuilder());
    return res;
  }

  private void helper(List<String> res, char[] array, int index, StringBuilder sb) {
    res.add(sb.toString());
    // for consecutive duplicate elements, we only pick the first one
    for (int i = index; i < array.length; i++) {
      if (i == index || array[i] != array[i - 1]) {
        helper(res, array, i + 1, sb.append(array[i]));
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }
}
