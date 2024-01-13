/**
 * Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.

 */

 // my initial solution pass 57/62 cases, TLE, after 
 // the idea is essentially emulating a stack - 
 // after putting the data into a Set after 
 // consolidating the parrenthese index, TLE issue is fixed
 // When checking your own implementation, watch out for any O(n) library functions inside loops, 
 // as these would make your solution O(n^2)

class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();

        List<Integer> leftIndex = new ArrayList<>();
        List<Integer> rightIndex = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                leftIndex.add(i);
            }
            if (arr[i] ==')' && rightIndex.size() < leftIndex.size()) {
                rightIndex.add(i);
            }
        }

        int size = rightIndex.size();
        while (leftIndex.size() > size) {
            leftIndex.remove(leftIndex.size() - 1);
        }
        
        // directly using List will cause TLE, in the loop, check with set is O(1)
        // check with list is O(n)
        Set<Integer> leftSet = new HashSet<>(leftIndex);
        Set<Integer> rightSet = new HashSet<>(rightIndex);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (Character.isLetter(c)) {
                sb.append(c);
            }
            if (c == '(' && leftSet.contains(i)) {
                sb.append('(');
            }
            if (c == ')' && rightSet.contains(i)) {
                sb.append(')');
            }
        }

        return sb.toString();
    }
}

// I have thought about stack, it is just how to implement it
// stack need to keep track of the index to find the right index
// Approach 1: Using a Stack and String Builder
class Solution {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}

// approach 2: look from 2 directions to make sure left and right side are aligned

class Solution {

    private StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == open) {
                balance++;
            } if (c == close) {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }  
        return sb;
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }
}


// approach 3: enhanced version of apporach 2 Shortened Two Pass String Builder
class Solution {

    public String minRemoveToMakeValid(String s) {

        // Pass 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            } if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

        // Pass 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                if (openToKeep < 0) continue;
            }
            result.append(c);
        }

        return result.toString();
    }
}