/*
Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.



Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.


Note:

1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length
Accepted
*/

// TreeMap
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if(hand == null || hand.length == 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int card : hand){
            map.put(card, map.getOrDefault(card,0)+1);
        }

        // try to extend exactly k values
        while(map.size() > 0){
            int first = map.firstKey();
            for(int i = first+1; i < first+W; i++){
                if(!map.containsKey(i)) return false;
            }
            for(int i = first; i < first+W; i++){
                if(map.get(i) == 1){
                    map.remove(i);
                } else {
                    map.put(i, map.get(i)-1);
                }
            }
        }
        return true;
    }
}
