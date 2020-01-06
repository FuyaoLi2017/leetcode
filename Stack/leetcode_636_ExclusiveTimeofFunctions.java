/*
On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.

We store logs in timestamp order that describe when a function is entered or exited.

Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.

A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.

The CPU is single threaded which means that only one function is being executed at a given time unit.

Return the exclusive time of each function, sorted by their function id.
*/
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        // separate time to several intervals, add interval to their function
        int[] result = new int[n];
        Stack<Integer> st = new Stack<>();
        int pre = 0;
        // pre means the start of the interval
        for(String log: logs) {
            String[] arr = log.split(":");
            if(arr[1].equals("start")) {
                if(!st.isEmpty())  result[st.peek()] += Integer.parseInt(arr[2]) - pre;
                // arr[2] is the start of next interval, doesn't belong to current interval.
                st.push(Integer.parseInt(arr[0]));
                pre = Integer.parseInt(arr[2]);
            } else {
                result[st.pop(;)] += Integer.parseInt(arr[2]) - pre + 1;
                // arr[2] is end of current interval, belong to current interval. That's why we have +1 here
                pre = Integer.parseInt(arr[2]) + 1;
                // pre means the start of next interval, so we need to +1
            }
        }
        return result;
    }
}
