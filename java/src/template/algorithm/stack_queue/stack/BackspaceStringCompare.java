package src.template.algorithm.stack_queue.stack;

/**
 * Example 1:
 *
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * Example 2:
 *
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * Example 3:
 *
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 *
 *
 * Follow up: Can you solve it in O(n) time and O(1) space?
 */

public class BackspaceStringCompare {

    // Skip brute force

    /**
     * Use stack to simulate the process
     * Time: O(n) , Space: O(n) in worst case
     * @param s
     * @param t
     * @return
     */
    public boolean back_space_compare_stack(String s, String t) {
        return convert(s).equals(convert(t));
    }

    private String convert(String s) {
        StringBuffer sb = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ch != '#') {
                sb.append(ch);
            } else {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Two pointers and traverse in same directions
     * Time: O(n), Space: O(1)
     * @param s
     * @param t
     * @return
     */
    public boolean back_space_compare_two_pointers(String s, String t) {
        int s_index = s.length() - 1, t_index = t.length() - 1;
        int skipS = 0, skipT = 0;
        while (s_index >= 0 || t_index >= 0) {
            while (s_index >= 0) {
                if (s.charAt(s_index) == '#') {
                    skipS ++;
                    s_index --;
                } else if (skipS > 0) {
                    skipS --;
                    s_index --;
                } else {
                    break;
                }
            }
            while (t_index >= 0) {
                if (t.charAt(t_index) == '#') {
                    skipT ++;
                    t_index --;
                } else if (skipT > 0) {
                    skipT --;
                    t_index --;
                } else {
                    break;
                }
            }
            if (s_index >= 0 && t_index >= 0) {
                if (s.charAt(s_index) != t.charAt(t_index)) {
                    return false;
                }
            } else {
                if (s_index >= 0 || t_index >= 0) {
                    return false;
                }
            }
            s_index--;
            t_index--;
        }
        return true;
    }
}
