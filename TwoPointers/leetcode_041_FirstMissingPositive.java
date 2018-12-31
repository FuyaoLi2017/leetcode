/*

*/

// 一种做法
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            while (cur > 0 && cur <= n && nums[cur - 1] != cur) {
                int next = nums[cur - 1];
                nums[cur - 1] = cur;
                cur = next;
            }
        }
        for (int i = 0; i < n; i++)
            if (nums[i] != i + 1) return i + 1;
        return n + 1;
    }
}

// 另一种用partition加上负数置数的做法
/*
The basic idea is ***for any k positive numbers (duplicates allowed), the first missing positive number must be within [1,k+1]***. The reason is like you put k balls into k+1 bins,
there must be a bin empty, the empty bin can be viewed as the missing number.

Unfortunately, there are 0 and negative numbers in the array, so firstly I think of using partition technique (used in quick sort) to put all positive numbers together in one side. This can be finished in O(n) time, O(1) space.
After partition step, you get all the positive numbers lying within A[0,k-1]. Now, According to the basic idea, I infer the first missing number must be within [1,k+1].
I decide to use A[i] (0<=i<=k-1) to indicate whether the number (i+1) exists.
But here I still have to main the original information A[i] holds. Fortunately, A[i] are all positive numbers,
so I can set them to negative to indicate the existence of (i+1) and I can still use abs(A[i]) to get the original information A[i] holds.
After step 2, I can again scan all elements between A[0,k-1] to find the first positive element A[i], that means (i+1) doesn't exist, which is what I want.

*/
public int firstMissingPositive(int[] A) {
    int n=A.length;
    if(n==0)
        return 1;
    int k=partition(A)+1;
    int temp=0;
    int first_missing_Index=k;
    for(int i=0;i<k;i++){
        temp=Math.abs(A[i]);
        if(temp<=k)
            A[temp-1]=(A[temp-1]<0)?A[temp-1]:-A[temp-1];
    }
    for(int i=0;i<k;i++){
        if(A[i]>0){
            first_missing_Index=i;
            break;
        }
    }
    return first_missing_Index+1;
}

public int partition(int[] A){
    int n=A.length;
    int q=-1;
    for(int i=0;i<n;i++){
        if(A[i]>0){
            q++;
            swap(A,q,i);
        }
    }
    return q;
}

public void swap(int[] A, int i, int j){
    if(i!=j){
        A[i]^=A[j];
        A[j]^=A[i];
        A[i]^=A[j];
    }
}

// my accepted solution, use O(N) space, similar to first solution, I should think more to get that solution
class Solution {
    public int firstMissingPositive(int[] nums) {
        // initialize a array with nums.length
        boolean[] array = new boolean[nums.length + 1];
        // every index of the array represent
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length) {
                array[nums[i]] = true;
            }
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] == false) {
                return i;
            }
        }
        return nums.length + 1;
    }
}
