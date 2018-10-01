/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
// recursive solution
public class Solution {
  // HashMap which holds old nodes as keys and new nodes as its values.
  HashMap<RandomListNode, RandomListNode> visitedHash =
      new HashMap<RandomListNode, RandomListNode>();

  public RandomListNode copyRandomList(RandomListNode head) {

    if (head == null) {
      return null;
    }

    // If we have already processed the current node, then we simply return the cloned version of it.
    if (this.visitedHash.containsKey(head)) {
      return this.visitedHash.get(head);
    }

    // Create a new node with the label same as old node. (i.e. copy the node)
    RandomListNode node = new RandomListNode(head.label);

    // Save this value in the hash map. This is needed since there might be
    // loops during traversal due to randomness of random pointers and this would help us avoid them.
    this.visitedHash.put(head, node);

    // Recursively copy the remaining linked list starting once from the next pointer and then from the random pointer.
    // Thus we have two independent recursive calls.
    // Finally we update the next and random pointers for the new node created.
    node.next = this.copyRandomList(head.next);
    node.random = this.copyRandomList(head.random);

    return node;
  }
}
/*
Time Complexity: O(N)O(N) where N is the number of nodes in the linked list.
Space Complexity: O(N). If we look closely, we have the recursion stack and we also have the space complexity to keep track of 
nodes already cloned i.e. using the visited dictionary. But asymptotically, the complexity is O(N).
*/

// 确实还不能完全理解这个东西
/*
An intuitive solution is to keep a hash table for each node in the list,
via which we just need to iterate the list in 2 rounds respectively to create nodes and assign the values for their random pointers.
As a result, the space complexity of this solution is O(N), although with a linear time complexity.

As an optimised solution, we could reduce the space complexity into constant.
The idea is to associate the original node with its copy node in a single linked list.
In this way, we don't need extra space to keep track of the new nodes.

The algorithm is composed of the follow three steps which are also 3 iteration rounds.

Iterate the original list and duplicate each node. The duplicate
of each node follows its original immediately.
Iterate the new list and assign the random pointer for each
duplicated node.
Restore the original list and extract the duplicated nodes.
*/
public RandomListNode copyRandomList(RandomListNode head) {
	RandomListNode iter = head, next;

	// First round: make copy of each node,
	// and link them together side-by-side in a single list.
	while (iter != null) {
		next = iter.next;

		RandomListNode copy = new RandomListNode(iter.label);
		iter.next = copy;
		copy.next = next;

		iter = next;
	}

	// Second round: assign random pointers for the copy nodes.
	iter = head;
	while (iter != null) {
		if (iter.random != null) {
			iter.next.random = iter.random.next;
		}
		iter = iter.next.next;
	}

	// Third round: restore the original list, and extract the copy list.
	iter = head;
	RandomListNode pseudoHead = new RandomListNode(0);
	RandomListNode copy, copyIter = pseudoHead;

	while (iter != null) {
		next = iter.next.next;

		// extract the copy
		copy = iter.next;
		copyIter.next = copy;
		copyIter = copy;

		// restore the original list
		iter.next = next;

		iter = next;
	}

	return pseudoHead.next;
}
