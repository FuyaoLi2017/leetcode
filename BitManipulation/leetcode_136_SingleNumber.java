//Bit manipulation
//XOR two same elements will return 0.
//trverse to XOR all the elements,we will get the result
class Solution {
public int singleNumber(int[] nums) {
    int ans =0;

    int len = nums.length;
    for(int i=0;i!=len;i++)
        ans ^= nums[i];

    return ans;

}
}
