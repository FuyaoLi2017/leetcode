/*
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.
*/
// https://leetcode.com/problems/next-greater-element-ii/discuss/336597/My-understanding-of-the-Java-stack-solution
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        // use stack to keep track of largest element will be seen in the furture(the end part of the array)
        Deque<Integer> stack = new ArrayDeque<>();
        // we put index into the stack
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            // pop until see an element larger than the current element scanned
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            // if we can't find an element, then it is -1(this is the largest element)
            // if there is still element, we can directly put the stack top to result
            res[i % nums.length] = stack.isEmpty() ? -1 : nums[stack.peek()];
            // put the current element onto the stack
            stack.push(i % nums.length);
        }
        return res;
    }
}
