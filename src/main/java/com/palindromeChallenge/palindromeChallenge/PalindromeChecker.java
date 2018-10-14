package com.palindromeChallenge.palindromeChallenge;

public class PalindromeChecker {

    //Checks if a string is a palindrome or not. True = Palindrome, False = Not Palindrome
    public static boolean checkPalindrome(String entry) {

        if(!entry.equals("")) {
            //Remove non-alphanumeric characters
            entry = entry.replaceAll("[^a-zA-Z0-9]", "");
            entry = entry.replaceAll("\\s+","");

            if(!entry.equals("") ) {

                //Remove Capitals
                entry = entry.toLowerCase();
                String reversedEntry = reverseString(entry);

                System.out.println("Entry: " + entry);
                System.out.println("Reversed: " +reversedEntry);

                if (entry.equals(reversedEntry)) {
                    System.out.println("------------------");
                    System.out.println("Palindrome? True");
                    System.out.println("------------------");
                    return true;
                } else {
                    System.out.println("------------------");
                    System.out.println("Palindrome? False");
                    System.out.println("------------------");
                    return false;
                }
            }
        }
        System.out.println("Entry: " + entry);
        System.out.println("------------------");
        System.out.println("NO VALID ENTERED");
        System.out.println("------------------");
        return false;
    }

    //reverse entry
    public static String reverseString(String entry) {
        String temp = "";

        for (int i = entry.length()-1; i >= 0 ; i--) {
            temp = temp + entry.charAt(i);
        }
        return temp;
    }
}
