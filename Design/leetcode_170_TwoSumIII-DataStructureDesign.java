/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false
*/

// add 比较慢， find比较快
public class TwoSum {
        Set<Integer> sum;
        Set<Integer> num;

        TwoSum(){
            sum = new HashSet<Integer>();
            num = new HashSet<Integer>();
        }
        // Add the number to an internal data structure.
    	public void add(int number) {
    	    if(num.contains(number)){
    	        sum.add(number * 2);
    	    }else{
    	        Iterator<Integer> iter = num.iterator();
    	        while(iter.hasNext()){
    	            sum.add(iter.next() + number);
    	        }
    	        num.add(number);
    	    }
    	}

        // Find if there exists any pair of numbers which sum is equal to the value.
    	public boolean find(int value) {
    	    return sum.contains(value);
    	}
    }

// 用hashmap, add快
    public class TwoSum {
        Map<Integer,Integer> hm;

        TwoSum(){
            hm = new HashMap<Integer,Integer>();
        }
        // Add the number to an internal data structure.
    	public void add(int number) {
    	    if(hm.containsKey(number)){
    	        hm.put(number,2);
    	    }else{
    	        hm.put(number,1);
    	    }
    	}

        // Find if there exists any pair of numbers which sum is equal to the value.
    	public boolean find(int value) {
    	    Iterator<Integer> iter = hm.keySet().iterator();
    	    while(iter.hasNext()){
    	        int num1 = iter.next();
    	        int num2 = value - num1;
    	        if(hm.containsKey(num2)){
    	            if(num1 != num2 || hm.get(num2) == 2){
    	                return true;
    	            }
    	        }
    	    }
    	    return false;
    	}
    }


// 用min 和max做优化，提高一定的时间复杂度
class TwoSum {

    /** Initialize your data structure here. */
    Map<Integer, Integer> nums;
    int min;
    int max;

    //Set<Integer> sums;
    public TwoSum() {
        nums = new HashMap<Integer, Integer>();
        min=Integer.MAX_VALUE;
        max=Integer.MIN_VALUE;
        //sums = new HashSet<Integer>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        nums.put(number, nums.getOrDefault(number, 0)+1);
        min=Math.min(min,number);
        max=Math.max(max,number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if(nums.size()<1){
            return false;
        }
        if(value<2*min||value>2*max){
            return false;
        }
        for(int n:nums.keySet()){
            if(2*n==value){
                if(nums.get(n)>1){
                    //sums.add(value);
                    return true;
                }
            }else{
                if(nums.containsKey(value-n)){
                    //sums.add(value);
                    return true;
                }
            }
        }


        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
