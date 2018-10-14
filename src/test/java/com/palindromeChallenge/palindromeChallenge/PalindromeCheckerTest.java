package com.palindromeChallenge.palindromeChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeCheckerTest {

    @Test
    public void checkPalindromeTest() {
        PalindromeChecker pc = new PalindromeChecker();

        //Check if Palindromes are actually being detected

        assertFalse(pc.checkPalindrome("hello"));
        assertTrue(pc.checkPalindrome("hanah"));

        //Check Capitals
        assertTrue(pc.checkPalindrome("hanah"));
        assertTrue(pc.checkPalindrome("Hanah"));
        assertTrue(pc.checkPalindrome("HanaH"));
        assertTrue(pc.checkPalindrome("haNah"));

        //Check if spaces and random symbols are being removed
        assertFalse(pc.checkPalindrome(""));
        assertFalse(pc.checkPalindrome(" "));
        assertFalse(pc.checkPalindrome("/"));
        assertFalse(pc.checkPalindrome("^£"));
        assertTrue(pc.checkPalindrome("!A"));
        assertTrue(pc.checkPalindrome("!A!"));
        assertTrue(pc.checkPalindrome("!han!ah"));
        assertTrue(pc.checkPalindrome("ha    nah"));
        assertFalse(pc.checkPalindrome("!hello"));
        assertFalse(pc.checkPalindrome("!hel!!!!!lo!"));
        assertFalse(pc.checkPalindrome("!hel       lo!"));
        assertTrue(pc.checkPalindrome("!han!!!!!ah!"));
    }

    @Test
    public void stringReversalTest() {
        PalindromeChecker pc = new PalindromeChecker();
        assertEquals("iH",pc.reverseString("Hi"));
        assertEquals("olleh",pc.reverseString("hello"));
        assertEquals("123",pc.reverseString("321"));
    }
}