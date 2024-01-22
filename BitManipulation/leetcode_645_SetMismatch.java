/**
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.
 */

 // my solution
 class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];

        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= nums.length; i++) {
            set.add(i);
        }

        for (int i : nums) {
            if(!set.remove(i)) {
                res[0] = i;
            };
        }
        res[1] = set.iterator().next();
        return res;
    }
}

// using constant space, invert the original array
// TC: O(N), SC: O(1)
public class Solution {
    public int[] findErrorNums(int[] nums) {
        int dup = -1, missing = 1;
        for (int n: nums) {
            if (nums[Math.abs(n) - 1] < 0)
                dup = Math.abs(n);
            else
                nums[Math.abs(n) - 1] *= -1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0)
                missing = i + 1;
        }
        return new int[]{dup, missing};
    }
}

// XOR solution
/**
 * Before we dive into the solution to this problem, let's consider a simple problem. 
 * Consider an array with n−1n-1n−1 elements containing numbers from 111 to nnn with one number missing out of them. 
 * Now, how to we find out this missing element. One of the solutions is to take the XOR of all the elements
 *  of this array with all the numbers from 111 to nnn. By doing so, we get the required missing number. 
 * This works because XORing a number with itself results in a 0 result.
 *  Thus, only the number which is missing can't get cancelled with this XORing.

Now, using this idea as the base, let's take it a step forward and use it for the current problem.
 By taking the XOR of all the elements of the given numsnumsnums array with all the numbers from 111 to nnn, 
 we will get a result, xorxorxor, as x⊕yx \oplus yx⊕y. Here, xxx and yyy refer to the repeated and the missing term 
 in the given numsnumsnums array. This happens on the same grounds as in the first problem discussed above.

Now, in the resultant xorxorxor, we'll get a 1 in the binary representation only at those bit positions which have a
 1 in one out of the numbers xxx and yyy, and a 0 at the same bit position in the other one. In the current solution,
  we consider the rightmost bit which is 1 in the xorxorxor, although any bit would work. Let's say, this position
   is called the rightmostbitrightmostbitrightmostbit.

If we divide the elements of the given numsnumsnums array into two parts such that the first set contains 
the elements which have a 1 at the rightmostbitrightmostbitrightmostbit position and the second set contains 
the elements having a 0 at the same position, we'll get one out of xxx or yyy in one set and the other one in the second set.
 Now, our problem has reduced somewhat to the simple problem discussed above.

To solve this reduced problem, we can find out the elements from 111 to nnn and consider them as a part of 
the previous sets only, with the allocation of the set depending on a 1 or 0 at the righmostbitrighmostbitrighmostbit position.

Now, if we do the XOR of all the elements of the first set, all the elements will result in an XOR of 0,
 due to cancellation of the similar terms in both numsnumsnums and the numbers (1:n)(1:n)(1:n), except one term, 
 which is either xxx or yyy.

For the other term, we can do the XOR of all the elements in the second set as well.
 */
public class Solution {
    public int[] findErrorNums(int[] nums) {
        int xor = 0, xor0 = 0, xor1 = 0;
        for (int n: nums)
            xor ^= n;
        for (int i = 1; i <= nums.length; i++)
            xor ^= i;
        int rightmostbit = xor & ~(xor - 1);
        for (int n: nums) {
            if ((n & rightmostbit) != 0)
                xor1 ^= n;
            else
                xor0 ^= n;
        }
        for (int i = 1; i <= nums.length; i++) {
            if ((i & rightmostbit) != 0)
                xor1 ^= i;
            else
                xor0 ^= i;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == xor0)
                return new int[]{xor0, xor1};
        }
        return new int[]{xor1, xor0};
    }
}