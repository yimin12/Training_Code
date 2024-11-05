package src.template.algorithm.stack_queue.stack;

import java.util.Stack;

/**
 * Given a string `s` containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 * Example 4:
 * Input: s = "([])"
 * Output: true
 */

public class ValidParentheses {

    /**
     * Method 1: Use stack to iterate
     * This pattern matches LIFO, so stack operation is the idea by nature
     * Time: O(n) Space: O(n) worst case where n is the length of string
     * @param s
     * @return
     */
    public boolean is_valid_stack(String s) {
        if (s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Method 2: Nothing different, just use recursion to simulate the stack
     * Time: O(n) Space: O(n) worst case for call stack where n is the length of string
     * Insight:
     *  Idea is a little bit different since we could not actually take the parentheses out
     *  when we are doing the recursion (backtrack validation)
     * Questions for student:
     *  why not find_valid_end(s, 0) == s.length() - 1?
     * @param s
     * @return
     */
    public boolean is_valid_recursion(String s) {
        return find_valid_end(s, 0) == s.length();
    }

    private int find_valid_end(String s, int cur) {
        // Base case1: hit the end char of string
        if (cur == s.length()) return cur;
        char expect;
        // Base case2:
        //    Case1: if char is '(', '[', '{', set expect in current level of recursion
        //    Case2: if char is ')', ']', '}', return cur depth to previous level to validate the match
        switch (s.charAt(cur)) {
            case '(':
                expect = ')';
                break;
            case '{':
                expect = '}';
                break;
            case '[':
                expect = ']';
                break;
            default:
                return cur;
        }
        // Back Tracking 1: get the index of next ')', ']' or '}'
        int closeIndex = find_valid_end(s, cur + 1);
        // Validate
        //     Case 1: closeIndex == s.length()
        //     Case 2: Validate and return cur if not match
        if (closeIndex == s.length() || s.charAt(closeIndex) != expect) return cur;
        // Keep Backtracking 2:
        //     For the remaining validation -> e.g "(())()", "(())" has been validated, then do "()"
        return find_valid_end(s, closeIndex + 1);
    }
}
