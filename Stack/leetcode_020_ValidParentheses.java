class Solution {
public boolean isValid(String s) {
     if(s.length() % 2 == 1)
        return false;
	Stack<Character> stack = new Stack<Character>();
	for (char c : s.toCharArray()) {
		if (c == '(')
			stack.push(')');
		else if (c == '{')
			stack.push('}');
		else if (c == '[')
			stack.push(']');
		else if (stack.isEmpty() || stack.pop() != c)
			return false;
	}
	return stack.isEmpty();
}
}

/*
review in Aug 28th
check the first half of the parentheses, push the second half of the parentheses into the stack
pop the element out if the element is not the first half of the parentheses.
If the popped out element in the stack doesn't
match the scanned element in the s. return false
if all the elements passed the loop, return true
*/
