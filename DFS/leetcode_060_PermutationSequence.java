/*
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
*/
// high vote answer
// https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
// 思路已经想出了，就是实现过程中数组的定义和操作还是需要更加熟练
public class Solution {
public String getPermutation(int n, int k) {
    List<Integer> numbers = new ArrayList<>();
    int[] factorial = new int[n+1];
    StringBuilder sb = new StringBuilder();

    // create an array of factorial lookup
    int sum = 1;
    factorial[0] = 1;
    for(int i=1; i<=n; i++){
        sum *= i;
        factorial[i] = sum;
    }
    // factorial[] = {1, 1, 2, 6, 24, ... n!}

    // create a list of numbers to get indices
    for(int i=1; i<=n; i++){
        numbers.add(i);
    }
    // numbers = {1, 2, 3, 4}

    k--;

    for(int i = 1; i <= n; i++){
        int index = k/factorial[n-i];
        sb.append(String.valueOf(numbers.get(index)));
        numbers.remove(index);
        k-=index*factorial[n-i];
    }

    return String.valueOf(sb);
}
}

// my solution: TLE, brute force backtracking all possibilities is slow
class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            list.add(i);
        }
        List<String> res = new ArrayList<>();
        backtracking(res, list, new StringBuilder(), k);
        return res.get(k - 1);
    }

    private void backtracking(List<String> res, List<Integer> array, StringBuilder cur, int k) {
        if (array.size() == 0) {
            res.add(cur.toString());
        } else {
            for (int i = 0; i < array.size(); i++) {
                int temp = array.get(i);
                cur.append(temp);
                array.remove(i);
                backtracking(res, array, cur, k);
                array.add(i, temp);
                cur.deleteCharAt(cur.length() - 1);
            }
        }
    }
}
