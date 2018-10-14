package com.palindromeChallenge.palindromeChallenge;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PalindromeController {
    private final PalindromeRepository repository;
    static final int timePeriod = 10;

    PalindromeController(PalindromeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/palindromes")
    Resources<Resource<Palindrome>> all() {

        //Get All palindromes
        List<Resource<Palindrome>> palindromes = repository.findAll().stream()
                .map(palindrome -> new Resource<>(palindrome,
                        linkTo(methodOn(PalindromeController.class).one(palindrome.getId())).withSelfRel(),
                        linkTo(methodOn(PalindromeController.class).all()).withRel("palindromes")))
                .collect(Collectors.toList());


        List<Resource<Palindrome>> selectedPalindromes = removeOutOfTimePeriodPalindromes(palindromes,timePeriod);

        //If list is larger than 10 elements long, select last 10. Else select all
        return new Resources<>(selectedPalindromes.subList(Math.max(selectedPalindromes.size() - 10, 0), selectedPalindromes.size()),
                    linkTo(methodOn(PalindromeController.class).all()).withSelfRel());

    }

    public static List<Resource<Palindrome>> removeOutOfTimePeriodPalindromes(List<Resource<Palindrome>> palindromes,int timePeriod) {
        List<Resource<Palindrome>> selectedPalindromes = new ArrayList<Resource<Palindrome>>();

        //Get Current Time
        Date date = new Date();
        System.out.println(date.getTime());

        for (Resource<Palindrome> palindromeResource: palindromes) {
            Palindrome palindrome = palindromeResource.getContent();
            Long timeStamp = palindrome.getDateAdded();

            if(date.getTime() - timeStamp <= (timePeriod*60*1000)) {
                System.out.println(date.getTime() - timeStamp);
                System.out.println("yes");
                selectedPalindromes.add(palindromeResource);
            }
        }
        return selectedPalindromes;
    }

    @PostMapping("/palindromes")
    Palindrome newPalindrome(@RequestBody Palindrome newPalindrome) {
        newPalindrome.setDateAdded();
        PalindromeChecker pc = new PalindromeChecker();
        if(pc.checkPalindrome(newPalindrome.getPalindrome()) == true) {
            return repository.save(newPalindrome);
        } else  {
            return null;
        }
    }

    @GetMapping("/palindromes/{id}")
    Resource<Palindrome> one(@PathVariable Long id) {

        Palindrome palindrome = repository.findById(id)
                .orElseThrow(() -> new PalindromeNotFoundException(id));

        return new Resource<>(palindrome,
                linkTo(methodOn(PalindromeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PalindromeController.class).all()).withRel("palindromes"));
    }

}
