// implement the stack with one queue
class MyStack {

    /** Initialize your data structure here. */
    private Queue<Integer> q1 = new LinkedList<>();

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);
        int len = q1.size();
        while(len > 1){
            q1.add(q1.remove());
            len--;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.remove();
    }

    /** Get the top element. */
    public int top() {
        return q1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

// implement the stack with two queue
class MyStack {

    /** Initialize your data structure here. */
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private int top;

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);
        top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    while (q1.size() > 1) {
    top = q1.remove();
    q2.add(top);
    }
    int res = q1.remove();
    Queue<Integer> temp = q1;
    q1 = q2;
    q2 = temp;
    return res;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
