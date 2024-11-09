package src.template.algorithm.data_structure.hash;

/**
 * Description:
 * In data structures, a hash function is used to convert a string (or any other type) into an integer
 * that is smaller than the hash table size and greater than or equal to zero. The goal of designing
 * a hash function is to distribute keys as uniformly as possible across the hash table to minimize
 * collisions. A widely used hash function algorithm utilizes a magic number (often 33) and treats
 * any string as a base-33 large integer, as demonstrated below:
 *
 * Example:
 * Given the string "abcd":
 * hashcode("abcd") = (ascii(a) * 33^3 + ascii(b) * 33^2 + ascii(c) * 33^1 + ascii(d)) % HASH_SIZE
 *                  = (97 * 33^3 + 98 * 33^2 + 99 * 33 + 100) % HASH_SIZE
 *                  = 3595978 % HASH_SIZE
 *
 * Here, `HASH_SIZE` represents the capacity of the hash table, which can be thought of as an array
 * with indices ranging from 0 to HASH_SIZE - 1.
 *
 * Task:
 * Given a string `key` and the size of the hash table `HASH_SIZE`, return the computed hash value
 * for the given key using this algorithm.
 */

public class MyHashFunction {

    public int hashCode(char[] key, int HASH_SIZE) {
        int N = key.length;
        long sum = 0;
        for(int i = 0; i < N; i++) {
            sum = (sum * 33 + (int)(key[i]))%HASH_SIZE;
        }
        return (int)sum;
    }

}
