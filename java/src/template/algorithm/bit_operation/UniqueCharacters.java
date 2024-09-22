package src.template.algorithm.bit_operation;

import java.util.HashSet;

/**
 * Determine whether a string contains unique characters -> which means no duplication
 * Time and Space complexity is easy to analyze
 */

public class UniqueCharacters {

    /**
     * Method 1, Use hashset the result set is un-know, hash
     */
    public boolean uniqueCharacters_Hashset(String a) {
        HashSet<Character> occurs = new HashSet<>();
        for (int i = 0; i < a.length(); i ++ ) {
            if (occurs.contains(a.charAt(i))) {
                return false;
            }
            occurs.add(a.charAt(i));
        }
        return true;
    }

    /**
     * Method 2: If all lower case between 'a' - 'z', the result set is known, so the length of the array/vector is
     *  the size of the result set
     */
    public boolean uniqueCharacter_Array(String a) {
        boolean[] occurs = new boolean[26]; // should be the length of result set
        for (int i = 0; i < a.length(); i ++) {
            int k = a.charAt(i) - 'a';
            if (occurs[k]) {
                return false;
            }
            occurs[k] = true;
        }
        return true;
    }

    /**
     * Method 3: It optimizes the space to use bit map
     */
    public boolean uniqueCharacter_BitMap(String a) {
        int occurs = 0;
        for (int i = 0; i < a.length(); i ++ ) {
            int k = a.charAt(i) - 'a';
            if (((occurs >> k) & 1) != 0) {
                return false;
            }
            occurs |= (1 << k);
        }
        return true;
    }

    /**
     * Method 4: bit map is extended to 2D, aka bit vector or bit matrix
     * Use case, when the result set is larger than 32 (int), we need to extend the map. This also require the
     *  result set is known. Let's say the unique is not ASCII, it uses utf-8
     */
    public boolean uniqueCharacter_BitMap2(String a) {
        long[] bit_vector = new long[4096];
        for (int i = 0; i < a.length();) {
            int codePoint = a.codePointAt(i);
            int row = codePoint / 64;
            int col = codePoint % 64;
            int mask = (1 << col);
            if ((bit_vector[row] & mask) != 0) {
                return false;
            }
            bit_vector[row] |= mask;
            i += Character.charCount(codePoint);
        }
        return true;
    }


}
