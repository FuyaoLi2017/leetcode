/*
Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6]

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,3,2,4,5,6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].
*/
// my solution
import java.util.NoSuchElementException;
public class ZigzagIterator {

    // the index for the lists
    int index1 = 0;
    int index2 = 0;
    List<Integer> v1;
    List<Integer> v2;
    // which line to output next, 1 or 2
    int sign = 1;
    boolean noSuchElement;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        if (noSuchElement) {
            throw new NoSuchElementException();
        } else if (v1.size() > index1 && sign == 1) {
            sign = 2;
            return v1.get(index1++);
        } else if (v2.size() > index2 && sign == 2) {
            sign = 1;
            return v2.get(index2++);
        } else if (v1.size() > index1) {
            return v1.get(index1++);
        } else {
            return v2.get(index2++);
        }
    }

    public boolean hasNext() {
        return (v1.size() > index1 || v2.size() > index2);
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

 // using queue
 class ZigzagIterator {
    private Queue<Iterator<Integer>> qu;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        qu = new LinkedList<Iterator<Integer>>();
        if (!v1.isEmpty()) {
            qu.offer(v1.iterator());
        }
        if (!v2.isEmpty()) {
            qu.offer(v2.iterator());
        }
    }

    public int next() {
        // iterator如果这个地方不指定泛型的话，返回的是iterator
        Iterator<Integer> itr = qu.poll();
        int val = itr.next();
        if (itr.hasNext()) {
            qu.offer(itr);
        }
        return val;
    }

    public boolean hasNext() {
        return !qu.isEmpty();
    }
}
