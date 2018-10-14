Port used: 8080

GET: curl -v localhost:8080/palindromes
POST: curl -X POST localhost:8080/palindromes -H 'Content-type:application/json' -d '{"palindrome": “[YOUR ENTRY]”}’