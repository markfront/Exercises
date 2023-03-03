/*
https://app.codesignal.com/arcade/intro/level-3/9DgaPsE2a7M6M2Hu6

Write a function that reverses characters in (possibly nested) parentheses in the input string.

Input strings will always be well-formed with matching ()s.

Example

    For inputString = "(bar)", the output should be
    solution(inputString) = "rab";
    For inputString = "foo(bar)baz", the output should be
    solution(inputString) = "foorabbaz";
    For inputString = "foo(bar)baz(blim)", the output should be
    solution(inputString) = "foorabbazmilb";
    For inputString = "foo(bar(baz))blim", the output should be
    solution(inputString) = "foobazrabblim".
    Because "foo(bar(baz))blim" becomes "foo(barzab)blim" and then "foobazrabblim".

Input/Output

    [execution time limit] 3 seconds (java)

    [input] string inputString

    A string consisting of lowercase English letters and the characters ( and ). It is guaranteed that all parentheses in inputString form a regular bracket sequence.

    Guaranteed constraints:
    0 ≤ inputString.length ≤ 50.

    [output] string

    Return inputString, with all the characters that were in parentheses reversed.
 */

import java.util.*;

public class ReverseInParentheses {
    public String solution(String inputString) {
        int n = inputString.length();
        
        char[] a = inputString.toCharArray();
        
        Stack<Character> stack = new Stack<>();
        
        return helper(a, 0, n-1, stack);
    }
    
    public String helper(char[] a, int b, int e, Stack<Character> stack)
    {
        StringBuilder sb = new StringBuilder();
        int i=b, j=e, p=0;
    
        while(i<=j) {
            if (a[i] == '(') {
                stack.push(a[i]);
                p++; // track nesting level
            } else if (a[i] ==')') {
                Stack<Character> temp = new Stack<>();
                while(!stack.isEmpty()) {
                    char ch = stack.pop();
                    if (ch != '(') {
                        if (p % 2 == 1) sb.append(ch); // reverse
                        else { temp.push(ch); } // double reverse 
                    } else { 
                        while(!temp.isEmpty()) {
                            sb.append(temp.pop()); 
                        }
                        p--; 
                        break;
                    }
                }
            } else {
                if (stack.isEmpty()) {
                    sb.append(a[i]);
                } else {
                    stack.push(a[i]);
                }
            }
            
            i++;
        }
        
        // output the stack
        while(!stack.isEmpty()) {
            char ch = stack.pop();
            if (ch != '(') sb.append(ch);
            //else break;
        }
        
        return sb.toString();
    }
    
}


