package com.palindromeChallenge.palindromeChallenge;


class PalindromeNotFoundException extends RuntimeException {

    PalindromeNotFoundException(Long id) {
        super("Could not find palindrome " + id);
    }
}
