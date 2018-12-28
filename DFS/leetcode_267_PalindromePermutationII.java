/*

Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
*/
public List<String> generatePalindromes(String s) {
    int odd = 0;
    String mid = "";
    List<String> res = new ArrayList<>();
    List<Character> list = new ArrayList<>();
    Map<Character, Integer> map = new HashMap<>();

    // step 1. build character count map and count odds
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        odd += map.get(c) % 2 != 0 ? 1 : -1;
    }

    // cannot form any palindromic string
    if (odd > 1) return res;

    // step 2. add half count of each character to list
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
        char key = entry.getKey();
        int val = entry.getValue();

        if (val % 2 != 0) mid += key;

        for (int i = 0; i < val / 2; i++) list.add(key);
    }

    // step 3. generate all the permutations
    getPerm(list, mid, new boolean[list.size()], new StringBuilder(), res);

    return res;
}

// generate all unique permutation from list
void getPerm(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> res) {
    if (sb.length() == list.size()) {
        // form the palindromic string
        res.add(sb.toString() + mid + sb.reverse().toString());
        sb.reverse();
        return;
    }

    for (int i = 0; i < list.size(); i++) {
        // avoid duplication
        if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) continue;

        if (!used[i]) {
            used[i] = true; sb.append(list.get(i));
            // recursion
            getPerm(list, mid, used, sb, res);
            // backtracking
            used[i] = false; sb.deleteCharAt(sb.length() - 1);
        }
    }
}

//my solution
public class Solution {
  public List<String> generatePalindromes(String input) {
    List<String> res = new ArrayList<>();
    if (input == null || input.length() == 0) {
      return res;
    }
    char[] array = input.toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    int count = 0;
    for (char c : array) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    for (char c : map.keySet()) {
      if(map.get(c) % 2 == 1)
        count++;
    }
    if (count > 1)
      return res;
    // construct a first half char array
    StringBuilder sb = new StringBuilder();
    String mid = "";
    for (char c : map.keySet()) {
      int num = map.get(c);
      if (num % 2 == 1)
        mid += c;
      while (num > 1) {
        sb.append(c);
        num -= 2;
      }
    }
    char[] halfArray = sb.toString().toCharArray();
    // then permutate the first half and complete the second half using reflection
    // helper function just permutation the char array
    helper(res, halfArray, mid, 0);
    return res;
  }

  private void helper(List<String> res, char[] halfArray, String mid, int index) {
    if (index == halfArray.length) {
      StringBuilder temp = new StringBuilder(new String(halfArray));
      res.add(temp.toString() + mid + temp.reverse().toString());
    }
    Set<Character> set = new HashSet<>();
    for (int i = index; i < halfArray.length; i++) {
      if (set.add(halfArray[i])) {
        swap(halfArray, index, i);
        helper(res, halfArray, mid, index + 1);
        swap(halfArray, index, i);
      }
    }
  }

  private void swap(char[] array, int i, int j) {
    char temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}


Expected: [["dg","dh","di","eg","eh","ei","fg","fh","fi"]]
 Your Solution: [["dg","ed","fe","dh","ed","fe","di","ed","fe"]]
