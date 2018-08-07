// binary search and bit manipulation, my basic solution
class Solution {
    public int singleNonDuplicate(int[] nums) {
    int lo = 0, hi = nums.length - 1;
    while (lo < hi) {
        int mid = (lo + hi) >>> 1;
        if (nums[mid] == nums[mid ^ 1])
            lo = mid + 1;
        else
            hi = mid;
    }
    return nums[lo];
    }
}

// binary search, a tricky math Solution
public static int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;

        while (start < end) {
            // We want the first element of the middle pair,
            // which should be at an even index if the left part is sorted.
            // Example:
            // Index: 0 1 2 3 4 5 6
            // Array: 1 1 3 3 4 8 8
            //            ^
            int mid = (start + end) / 2;
            if (mid % 2 == 1) mid--;
            /* this operation will make the index of mid a even number.
            * In this way, there will be a even number of elements to the left of the mid element. Only in such conditions, the following if...else...sentences make sense. Because if the single element is in the left part, the mid element will be the second element in the pair since the array is sorted(corresponding to if (nums[mid] != nums[mid + 1])).
            * else, the element will be in the right part. I hope this can help you to understand.
            */

            // We didn't find a pair. The single element must be on the left.
            // (pipes mean start & end)
            // Example: |0 1 1 3 3 6 6|
            //               ^ ^
            // Next:    |0 1 1|3 3 6 6
            if (nums[mid] != nums[mid + 1]) end = mid;

            // We found a pair. The single element must be on the right.
            // Example: |1 1 3 3 5 6 6|
            //               ^ ^
            // Next:     1 1 3 3|5 6 6|
            else start = mid + 2;
        }

        // 'start' should always be at the beginning of a pair.
        // When 'start > end', start must be the single element.
        return nums[start];
    }
