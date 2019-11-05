/*
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
*/

// my solution
class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */

    ListNode head;
    public Solution(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {

        double rand = Math.random();
        if(rand == 0) rand = 1;
        double index = 0;
        int prev = 0;

        ListNode fast = head;
        ListNode slow = head;
        while(fast != null){
            fast = fast.next;
            index += rand;
            // increase by 1
            if((int)index > prev){
                prev = (int)index;
                slow = slow.next;
            }
        }
        return slow.val;
    }
}

// sampling question
public class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    ListNode head;
    Random random;
    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int count = 0;
        int result = -1;
        ListNode dummyhead = head;
        while(dummyhead != null) {
            if(random.nextInt(++count) == 0) {
                result = dummyhead.val;
            }
            dummyhead = dummyhead.next;
        }
        return result;
    }
}
