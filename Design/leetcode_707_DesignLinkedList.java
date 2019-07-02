/*
Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
Example:

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
Note:

All values will be in the range of [1, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in LinkedList library.
*/
class MyLinkedList {

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    /** Initialize your data structure here. */
    ListNode dummy;

    public MyLinkedList() {
        dummy = new ListNode(0);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0) return -1;
        ListNode cursor = dummy;
        while (cursor != null && index-- >= 0) {
            cursor = cursor.next;
        }
        return (cursor != null) ? cursor.val : -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode current = new ListNode(val);
        ListNode temp = dummy.next;
        dummy.next = current;
        current.next = temp;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode cursor = dummy;
        while (cursor.next != null) {
            cursor = cursor.next;
        }
        cursor.next = new ListNode(val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0) {
            ListNode temp = dummy.next;
            ListNode current = new ListNode(val);
            dummy.next = current;
            current.next = temp;
        };
        ListNode cursor = dummy;
        // move cursor to position before index-th node
        while (cursor.next != null && index-- > 0) {
            cursor = cursor.next;
        }
        if (cursor.next == null && index > 0) return;
        // index equals to the length of the linked list
        if (cursor != null && cursor.next == null) {
            cursor.next = new ListNode(val);
        } else if (cursor != null && cursor.next != null) {
            ListNode temp = cursor.next;
            ListNode current = new ListNode(val);
            cursor.next = current;
            current.next = temp;
        }
        return;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0) return;
        ListNode cursor = dummy;
        while (cursor.next != null && index-- > 0) {
            cursor = cursor.next;
        }
        if (cursor.next != null) {
            cursor.next = cursor.next.next;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
