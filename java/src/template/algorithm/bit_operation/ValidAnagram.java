package src.template.algorithm.bit_operation;

/**
 * 242: Link: https://leetcode.com/problems/valid-anagram/description/
 * Given two strings `s` and `t`, return `true` if `t` is an
 * anagram of `s`, and `false` otherwise.
 *
 * An anagram is a word or phrase formed by rearranging the letters
 * of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 * Constraints:
 * - 1 <= s.length, t.length <= 5 * 10^4
 * - `s` and `t` consist of lowercase English letters only.
 */

public class ValidAnagram {

    /**
     * Hash Mapping, common used for compare anagram
     * Time: O(n) Space: O(1)
     * @param a
     * @param b
     * @return
     */
    public boolean is_anagram_mapping(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.length() != b.length()) return false;
        int[] tmpArr = new int[26];
        for (char c : a.toCharArray()) tmpArr[c - 'a']++;
        for (char c : b.toCharArray()) {
            tmpArr[c - 'a']--;
            if (tmpArr[c - 'a'] < 0) return false;
        }
        return true;
    }

    /**
     * Use Xor, 异或满足交换律，所以可以检测 Anagram
     * Time: O(n) Space: O(1)
     * @param a
     * @param b
     * @return
     */
    public boolean is_anagram_x_or(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.length() != b.length()) return false;
        int res = 0;
        for (char c : a.toCharArray()) {
            res ^= c - 'a';
        }
        for (char c : b.toCharArray()) {
            res ^= c - 'a';
        }
        return res == 0;
    }

}
