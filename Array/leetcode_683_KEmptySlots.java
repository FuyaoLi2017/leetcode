/*
There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

If there isn't such day, output -1.

Example 1:
Input:
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
Example 2:
Input:
flowers: [1,2,3]
k: 1
Output: -1
Note:
The given array will be in the range [1, 20000].
*/
// https://www.cnblogs.com/yzssoft/p/7127894.html
// TreeSet的比较方法
/*
Time Complexity (Java): O(N \log N)O(NlogN), where N is the length of flowers. Every insertion and search is O(logN).
*/
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> active = new TreeSet();
        int day = 0;
        for (int flower: flowers) {
            day++;
            active.add(flower);
            // 只会影响插入节点左右的slot结构，所以即时进行判断就可以
            Integer lower = active.lower(flower);
            Integer higher = active.higher(flower);
            if (lower != null && flower - lower - 1 == k ||
                    higher != null && higher - flower - 1 == k)
                return day;
        }
        return -1;
    }
}

// another solution using arrays
public int kEmptySlots(int[] flowers, int k) {
        int[] days =  new int[flowers.length];
        // 按照slot作为index，days[]的数值是bloom date
        for(int i=0; i<flowers.length; i++)days[flowers[i] - 1] = i + 1;
        // 初始化left, right
        int left = 0, right = k + 1, res = Integer.MAX_VALUE;
        for(int i = 0; right < days.length; i++){   //注意这个地方的跳出条件是right < days.length,跟i无关
            if(days[i] < days[left] || days[i] <= days[right]){  //如果改slot夹在中间开
                if(i == right)res = Math.min(res, Math.max(days[left], days[right]));   //we get a valid subarray
                left = i;
                right = k + 1 + i;
            }
        }
        return (res == Integer.MAX_VALUE)?-1:res;
    }
