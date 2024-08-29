class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                // When we find a closing parenthesis, we need to reverse the characters within the parentheses
                StringBuilder temp = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    temp.append(stack.pop());
                }
                stack.pop(); // remove the opening parenthesis '('
                for (char c : temp.toString().toCharArray()) {
                    stack.push(c);
                }
            } else {
                stack.push(ch);
            }
        }
        
        // Build the final result
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }
}