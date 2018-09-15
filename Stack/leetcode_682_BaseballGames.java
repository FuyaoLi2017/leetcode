class Solution {
    public int calPoints(String[] ops) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < ops.length; i++){
            // System.out.print(ops[i] + ": ");
            if(ops[i].equals("+")){
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                int result = temp1 + temp2;
                sum += result;
                stack.push(temp2);
                stack.push(temp1);
                stack.push(result);
            }

            else if(ops[i].equals("D")){
                int temp = stack.peek();
                int result = temp * 2;
                sum += result;
                stack.push(result);
            }
            else if(ops[i].equals("C")){
                int temp = stack.pop();
                sum -= temp;
            }
            else{
                int temp = Integer.parseInt(ops[i]);
                stack.push(temp);
                sum += temp;
            }
            // System.out.println(sum);
        }
        return sum;
    }
}
