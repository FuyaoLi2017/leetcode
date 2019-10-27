/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
*/

class Solution {


    // solution 1: recusive
    public int calculate(String s) {
        int l1 = 0, o1 = 1;
        int l2 = 1, o2 = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = c - '0';

                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }

                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '(') {
                int j = i;

                for (int cnt = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                }

                int num = calculate(s.substring(j + 1, i));

                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '*' || c == '/') {
                o2 = (c == '*' ? 1 : -1);

            } else if (c == '+' || c == '-') {
                if (c == '-' && (i == 0 || s.charAt(i - 1) == '(')) {
                    o1 = -1;
                    continue;
                }
                l1 = l1 + o1 * l2;
                o1 = (c == '+' ? 1 : -1);

                l2 = 1; o2 = 1;
            }
        }

        return (l1 + o1 * l2);
    }


    // solution 2: iterative
    public int calculate(String s) {
       Queue<Character> tokens = new ArrayDeque<Character>();

       for(char c : s.toCharArray()){
           if(c != ' ') tokens.offer(c);
       }

       tokens.offer('+');
       return calculate(tokens);
    }

    private int calculate(Queue<Character> tokens){

       char preOp = '+';
       int num = 0, sum = 0, prev = 0;

       while(!tokens.isEmpty()){
            char c = tokens.poll();

            if('0' <= c && c <= '9') {
                num = num * 10 + c - '0';
            }else if(c=='(') {
                num = calculate(tokens);
            }else{
                switch (preOp){
                    case '+':
                        sum += prev;
                        prev = num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;
                }

                if(c == ')')
                    break;

                preOp = c;
                num = 0;
            }
       }

       return sum + prev;
    }
}
