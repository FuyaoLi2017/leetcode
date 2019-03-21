public class Solution {
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // In statistics, the median is used for dividing a set into two equal length subsets, that one subset is always greater than the other there are two array, we seperated them into two part, it is left_part and right_part. If we found the value in left_part <= right_part, then we can find the answer. So we need find i, j, which satisfied (1)A[i-1] < B[j] && B[j-1] < A[i(2)len(left_part) == len(right_part).
 //For(1) Median=(max(left_part)+min(right_part))/2.
 //For(2)  i + j == m-i+n-j => i + j = (m+n) / 2     (or: i + j == m-i+n-j+1 => j = (m+n+1)/2 - i)

   // left_part          |        right_part
   // A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
   // B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]


    int m = nums1.length;
    int n = nums2.length;
    if(m>n) return findMedianSortedArrays(nums2,nums1);
    //for all the following, we assumed m<=n;
    int imin = 0;
    int imax = m;
    int max_of_left;
    int min_of_right;
    int i=(imin+imax)/2, j=(m+n+1)/2 -i;
    //
    while(imin <= imax){
        i=(imin+imax)/2;
        j = (m+n+1)/2 -i;
        if(i>0 && nums1[i-1] > nums2[j]){
            //it means i is too large, so decrease i
            //m <= n(we have assumed), i < m ==> j = (m+n+1)/2 - i > (m+n+1)/2 - m >= (2*m+1)/2 - m >= 0
            imax = i-1;
        }else if(i < m  && nums2[j-1] > nums1[i]){
            //it means i is too smore.
            //m <= n(we have assumed), i > 0 ==> j = (m+n+1)/2 - i < (m+n+1)/2 <= (2*n+1)/2 <= n

            imin = i+1;
        }else break;
        //i is perfect
    }
           //find left maximum value and find right maximum value
           if(i == 0) max_of_left = nums2[j-1];
           else if(j == 0) max_of_left = nums1[i-1];
           else  max_of_left = Math.max(nums1[i-1],nums2[j-1]);
           if((m + n) % 2 == 1) return max_of_left;

           if(i == m) min_of_right = nums2[j];
           else if( j == n) min_of_right = nums1[i];
           else min_of_right = Math.min(nums1[i],nums2[j]);

           return (max_of_left+min_of_right)/2.0;

     }
   }



// 结合kth smallest element 比较直观的做法
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (kth(nums1, 0, nums2, 0, l) + kth(nums1, 0, nums2, 0, r)) / 2.0;
    }

    private int kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
        if (aLeft >= a.length) {
            return b[bLeft + k - 1];
        }
        if (bLeft >= b.length) {
            return a[aLeft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aLeft], b[bLeft]);
        }

        int aMid = aLeft + k / 2 - 1;
        int bMid = bLeft + k / 2 - 1;

        int aval = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
        int bval = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];

        if (aval > bval) {
            return kth(a, aLeft, b, bMid + 1, k - k / 2);
        } else {
            return kth(a, aMid + 1, b, bLeft, k - k / 2);
        }
    }
}
