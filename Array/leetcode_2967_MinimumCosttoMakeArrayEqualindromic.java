/**
 * You are given a 0-indexed integer array nums having length n.

You are allowed to perform a special move any number of times (including zero) on nums. In one special move you perform the following steps in order:

Choose an index i in the range [0, n - 1], and a positive integer x.
Add |nums[i] - x| to the total cost.
Change the value of nums[i] to x.
A palindromic number is a positive integer that remains the same when its digits are reversed. For example, 121, 2552 and 65756 are palindromic numbers whereas 24, 46, 235 are not palindromic numbers.

An array is considered equalindromic if all the elements in the array are equal to an integer y, where y is a palindromic number less than 109.

Return an integer denoting the minimum possible total cost to make nums equalindromic by performing any number of special moves.


 */

 // sample solution, just find median and go up/down to find nearest palindrome

 class Solution {
    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        int med=0,len=nums.length;
        med=nums[len/2];
        int num1=palindrome1(med),num2=palindrome2(med);
        long sum1=findsum(nums,num1),sum2=findsum(nums,num2);
        return sum1 > sum2 ? sum2 : sum1;
    }
    private int palindrome1(int num)
    {
        while(!check(num))
        {
            num--;
        }
        return  num;
    }
    private int palindrome2(int num)
    {
        while(!check(num))
        {
            num++;
        }
        return num;
    }
    private boolean check(int n)
    {
        long dup=n,rev=0;
        while(dup!=0)
        {
            rev=rev*10+(dup%10);
            dup/=10;
        }
        return rev==n;
    }
    private long findsum(int nums[], int n)
    {
        long sum=0;
        for(int i=0;i<nums.length;i++)
        {
            sum=sum+Math.abs(n-nums[i]);
        }
        return sum;
    }
}


// my wrong answer, passed 622/645 test cases, I just thought too much about this... this is not that hard
class Solution {
    public long minimumCost(int[] nums) {
        long cost = 0;
        Arrays.sort(nums);
        
        // get median number
        int median = 0;
        if (nums.length % 2 == 0) {
            median = (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2;
        } else {
            median = nums[nums.length/2];
        }
        
        if (checkP(median)) {
            for (int i = 0; i < nums.length; i++) {
                cost += Math.abs(nums[i] - median);
            }
        } else {
            String str = String.valueOf(median);
            int digits = (str.length()-1)/2;
            int factor = (int)Math.pow(10, digits);

            int number2 = median + factor;
            int number3 = median - factor;
            int closestP1 = getClosestP(median);
            
            int win = closestP1;
            int closestP2 = getClosestP(number2);
            int closestP3 = getClosestP(number3);
            
            int sum = 0;
            
            for (int i = 0; i< nums.length; i++) {
                sum += nums[i];
            }
            int avg = sum / nums.length;
            
            if (Math.abs(win - median) >= Math.abs(closestP2 - median)) {
                win = closestP2;
            }
            if (Math.abs(win - median) >= Math.abs(closestP3 - median)) {
                win = closestP3;
            }
            for (int i = 0; i < nums.length; i++) {
                cost += Math.abs(nums[i] - win);
            }
        }
        return cost;
        
    }
    
    private boolean checkP(int num) {
        String s = String.valueOf(num);
        if (s.length() == 1) return true;
        
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length()-i-1)) {
                return false;
            }
        }
        return true;
    }
    
    private int getClosestP(int num) {
        int res = 0;
        String curS = String.valueOf(num);
        char[] arr = curS.toCharArray();
        
        for (int i = 0; i < arr.length / 2; i++) {
            arr[arr.length-i-1] = arr[i];
        }
        
        String newS = new String(arr);
        return Integer.valueOf(newS);
    }
}