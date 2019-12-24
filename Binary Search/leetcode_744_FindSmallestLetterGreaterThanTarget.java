/*
Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
*/
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
            if(letters[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        return letters[lo % letters.length];
    }
}
