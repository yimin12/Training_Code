package src.test.template.algorithm.bit_operation.impl;

import org.junit.Before;
import org.junit.Test;
import src.template.algorithm.bit_operation.UniqueCharacters;

import static org.junit.Assert.*;
import java.util.HashSet;

public class UniqueCharactersTest {

    private UniqueCharacters uniqueCharacters;

    @Before
    public void setUp() {
        uniqueCharacters = new UniqueCharacters();
    }

    // Test for uniqueCharacters_Hashset (Method 1)
    @Test
    public void testUniqueCharacters_Hashset() {
        assertTrue(uniqueCharacters.uniqueCharacters_Hashset("abcdefg"));  // All unique characters
        assertFalse(uniqueCharacters.uniqueCharacters_Hashset("aabbcc"));  // Repeated characters
        assertTrue(uniqueCharacters.uniqueCharacters_Hashset(""));         // Empty string, should be unique
        assertTrue(uniqueCharacters.uniqueCharacters_Hashset("xyz123"));   // Mixed characters
        assertFalse(uniqueCharacters.uniqueCharacters_Hashset("abca"));    // Repeated character 'a'
    }

    // Test for uniqueCharacter_Array (Method 2)
    @Test
    public void testUniqueCharacter_Array() {
        assertTrue(uniqueCharacters.uniqueCharacter_Array("abcdefg"));    // All unique characters
        assertFalse(uniqueCharacters.uniqueCharacter_Array("aabbcc"));    // Repeated characters
        assertTrue(uniqueCharacters.uniqueCharacter_Array(""));           // Empty string, should be unique
        assertFalse(uniqueCharacters.uniqueCharacter_Array("aaz"));       // Repeated character 'a'
    }

    // Test for uniqueCharacter_BitMap (Method 3)
    @Test
    public void testUniqueCharacter_BitMap() {
        assertTrue(uniqueCharacters.uniqueCharacter_BitMap("abcdefg"));   // All unique characters
        assertFalse(uniqueCharacters.uniqueCharacter_BitMap("aabbcc"));   // Repeated characters
        assertTrue(uniqueCharacters.uniqueCharacter_BitMap(""));          // Empty string, should be unique
        assertFalse(uniqueCharacters.uniqueCharacter_BitMap("xyzxy"));    // Repeated character 'x' and 'y'
    }

    // Test for uniqueCharacter_BitMap2 (Method 4)
    @Test
    public void testUniqueCharacter_BitMap2() {
        assertTrue(uniqueCharacters.uniqueCharacter_BitMap2("abcdefg"));  // All unique characters
        assertFalse(uniqueCharacters.uniqueCharacter_BitMap2("aabbcc"));  // Repeated characters
        assertTrue(uniqueCharacters.uniqueCharacter_BitMap2(""));         // Empty string, should be unique
        assertTrue(uniqueCharacters.uniqueCharacter_BitMap2("𝛼𝛽𝛾𝛿𝜀𝜁"));  // Extended Unicode characters (UTF-8)
        assertFalse(uniqueCharacters.uniqueCharacter_BitMap2("𝛼𝛼𝛾𝛿"));    // Repeated character '𝛼' in Unicode
    }
}
