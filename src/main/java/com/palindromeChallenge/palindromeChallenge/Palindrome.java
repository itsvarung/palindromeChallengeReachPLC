package com.palindromeChallenge.palindromeChallenge;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Palindrome {
    private @Id @GeneratedValue Long id;
    private String palindrome;
    private Long dateAdded;

    Palindrome(String palindrome) {
        this.palindrome = palindrome;
    }

    public void setDateAdded() {
        Date date = new Date();
        this.dateAdded = date.getTime();
    }
}
