/*
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
*/
// https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
// https://cloud.tencent.com/developer/article/1131945
int singleNumber(vector<int>& nums)
{
    int a = 0, b = 0;
    for (int i = 0; i < nums.size(); ++i)
    {
        b = (b ^ nums[i]) & ~a;
        a = (a ^ nums[i]) & ~b;
    }
    return b;
}
