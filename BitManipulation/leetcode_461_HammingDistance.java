// Bit Manipulation
//using XOR to solve the problem
class Solution {
public int hammingDistance(int x, int y) {
    int xor = x ^ y, count = 0;
    for (int i=0;i<32;i++) count += (xor >> i) & 1;
    return count;
}
}

//API trick
public class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
