/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.

Example:

Input: n = 12, primes = [2,7,13,19]
Output: 32
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
             super ugly numbers given primes = [2,7,13,19] of size 4.
Note:

1 is a super ugly number for any given primes.
The given numbers in primes are in ascending order.
0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
*/

// based on ugly number 2
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ret = new int[n];
        ret[0] = 1;

        int[] indexes  = new int[primes.length];

        for(int i = 1; i < n; i++){
            ret[i] = Integer.MAX_VALUE;

// find next smallest value large than the current largest value
            for(int j = 0; j < primes.length; j++){
                ret[i] = Math.min(ret[i], primes[j] * ret[indexes[j]]);
            }
// avoid duplicate, move the index to the next position
// next time when we need to use it. It is the smallest value we could produce
//  easier for us to search for the result
            for(int j = 0; j < indexes.length; j++){
                if(ret[i] == primes[j] * ret[indexes[j]]){
                    indexes[j]++;
                }
            }
        }

        return ret[n - 1];
    }
}

// heap solution
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
	Comparator<Number> comparator = new NumberCompare();
	PriorityQueue<Number> queue =
            new PriorityQueue<Number>(primes.length, comparator);
	for(int i = 0; i < primes.length; i ++)
		queue.add(new Number(primes[i], 0, primes[i]));
	int[] uglyNums = new int[n];
	uglyNums[0] = 1;
	for(int i = 1; i < n; i++){
		Number min = queue.peek();
		uglyNums[i] = min.un;
		while(queue.peek().un == min.un){
			Number tmp = queue.poll();
			queue.add(new Number(uglyNums[tmp.pos + 1] * tmp.prime, tmp.pos+1, tmp.prime));
		}
	}

	return uglyNums[n-1];
}

public class Number{
	int un;
	int pos;
	int prime;
	Number(int un, int pos, int prime){
		this.un = un;
		this.pos = pos;
		this.prime = prime;
	}
}

public class NumberCompare implements Comparator<Number>{

	@Override
	public int compare(Number x, Number y) {
		// TODO Auto-generated method stub
		if (x.un > y.un)
			return 1;
		else if (x.un < y.un)
			return -1;
		else
			return 0;
	}
}
}
