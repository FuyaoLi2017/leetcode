/*
Given a set of characters represented by a String, return a list containing all subsets of the characters.

Assumptions

There are no duplicate characters in the original set.
​Examples

Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
Set = "", all the subsets are [""]
Set = null, all the subsets are []
*/

public class Solution {
  public List<String> subSets(String set) {
    List<String> list = new ArrayList<>();
    if (set == null)
      return list;
    char[] array = set.toCharArray();
    findSubset(list, array, new StringBuilder(), 0);
    return list;
  }

  private void findSubset(List<String> list, char[] array, StringBuilder sb, int index) {
    if (index == array.length) {
      list.add(new String(sb));
      return;
    } else {
      sb.append(array[index]);
      findSubset(list, array, sb, index + 1);
      sb.deleteCharAt(sb.length() - 1);
      findSubset(list, array, sb, index + 1);
    }
  }
}

// another more tricky way to DFS
public class Solution {
  public List<String> subSets(String set) {
    List<String> res = new ArrayList<>();
    if (set == null)
      return res;
    char[] array = set.toCharArray();
    findSubset(res, array, new StringBuilder(), 0);
    return res;
  }

  private void findSubset(List<String> list, char[] array, StringBuilder sb, int index) {
    list.add(sb.toString());
    for (int i = index; i < array.length; i++) {
      sb.append(array[i]);
      findSubset(list, array, sb, i + 1);   // traverse all positions of the subsets
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
