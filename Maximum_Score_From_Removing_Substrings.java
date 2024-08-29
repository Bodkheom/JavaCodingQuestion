class Solution {
    String str;

    public int removeSubstring(String pair, int score) {
        int totalScore = 0;
        Stack<Character> stack = new Stack<>();
        char firstChar = pair.charAt(0);
        char secChar = pair.charAt(1);

        for (char ch : str.toCharArray()) {
            if (ch == secChar && !stack.isEmpty() && stack.peek() == firstChar) {
                totalScore += score;
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        str = sb.toString();
        return totalScore;
    }

    public int maximumGain(String s, int x, int y) {
        str = s; // Initialize the string `str` with the input `s`
        String firstPair = (x > y) ? "ab" : "ba";
        String secPair = (firstPair.equals("ab")) ? "ba" : "ab";
        int score = 0;

        score = removeSubstring(firstPair, Math.max(x, y));
        score += removeSubstring(secPair, Math.min(x, y));
        return score;
    }
}