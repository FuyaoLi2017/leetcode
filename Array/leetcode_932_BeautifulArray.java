/*
For some fixed N, an array A is beautiful if it is a permutation of the integers 1, 2, ..., N, such that:

For every i < j, there is no k with i < k < j such that A[k] * 2 = A[i] + A[j].

Given N, return any beautiful array A.  (It is guaranteed that one exists.)



Example 1:

Input: 4
Output: [2,1,4,3]
Example 2:

Input: 5
Output: [3,1,2,5,4]


Note:

1 <= N <= 1000
*/

//
// Thanks for sharing, it’s really brilliant!
//
// For those who may have question about why this method work, I want to share some of my understanding here:
//
// First: divide and conquer, why to divide to odd and even part (or merge odd and even part together)?
//
// that’s say, we have two part: odd = {1, 5, 3}, even = {2, 4, 6}
//
// any number from odd part and any number from even part will alway obey the rule A[k] * 2 != A[i] + A[j]
//
// Ex: 5 from odd part, 6 from even part, for any k between 5 and 6, A[k] * 2 != 5 + 6
//
// So merge two parts will form beautiful array!
//
//
// Next, we need to make sure the odd and even part are beautiful arrays!
//
// Second: how to find beautiful array that contains only odd (even) number?
//
// as the beautiful array properties that Lee mentioned, Addition and Multiplication
//
// We can get the odd/even beautiful array from previous beautiful array by addition and multiplication
//
// Following is the flow that generate odd and even separately
//
// odd (n * 2 - 1)  	even (n * 2)
// 1 (1*2-1)	 	2 (1*2)
// 1 3(2*2-1)  		2 4(2*2)
// 1 3 5(3*2-1)  	        2 4 6(3*2)

public int[] beautifulArray(int N) {
    ArrayList<Integer> res = new ArrayList<>();
    res.add(1);
    while (res.size() < N) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i : res) if (i * 2 - 1 <= N) tmp.add(i * 2 - 1);
        for (int i : res) if (i * 2 <= N) tmp.add(i * 2);
        res = tmp;
    }
    return res.stream().mapToInt(i -> i).toArray();
}
