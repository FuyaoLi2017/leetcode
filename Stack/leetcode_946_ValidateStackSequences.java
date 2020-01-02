/*

*/


// my solution, should be more concise
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length == 0) return true;
        int pushIdx = 0;
        int popIdx = 0;
        int len = pushed.length;

        Stack<Integer> stack = new Stack<>();

        while(pushIdx < len || popIdx < len){
            int popVal = popped[popIdx];

            while(stack.isEmpty() || (pushIdx < len && stack.peek() != popVal)){
                stack.push(pushed[pushIdx++]);
            }

            if(stack.peek() != popVal) return false;

            while(!stack.isEmpty() && popIdx < len && stack.peek() == popped[popIdx]){
                stack.pop();
                popIdx++;
            }
        }

        return true;
    }
}

// a better solution, directly use for loop to check
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();
        int i = 0;
        for (int x : pushed) {
            s.push(x);
            while (!s.empty() && s.peek() == popped[i]) {
                s.pop();
                i++;
            }
        }
        return i == popped.length;
    }
}
