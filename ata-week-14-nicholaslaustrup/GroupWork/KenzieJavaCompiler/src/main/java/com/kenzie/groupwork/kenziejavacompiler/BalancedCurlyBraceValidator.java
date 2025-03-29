package com.kenzie.groupwork.kenziejavacompiler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Compiler check that ensures the curly braces in a file are evenly matched. The file must contain an even number of
 * opening and closing curly braces. This check does not perform any validation that the curly braces are in legal
 * locations in the file. [NOTE] Your implementation does not need to handle escaped curly braces within strings
 * literals.
 */
public class BalancedCurlyBraceValidator {

    private static final char OPEN = '{';
    private static final char CLOSE = '}';
    private boolean debug = false;

    /**
     * Validates that the curly braces in the list of provided file characters are balanced.
     * @param fileCharacters all characters in a java file
     * @return true if the braces are balanced, false otherwise
     */
    public boolean check(List<Character> fileCharacters) {

        //Checks for debug
        if (debug) {
            return checkExtension(fileCharacters);
        }

        Stack<Character> stack = new Stack();

        //Loops over input list to check if open and close braces are equal
        for (char character : fileCharacters) {
            if (character == OPEN){
                stack.push(character);
            }else if (!stack.isEmpty() &&
                    (character == CLOSE)) {
                stack.pop();
            } else return false;
        }
        return stack.isEmpty();
    }

    /**
     * Does the above and prints out debugging information. This can be combined into one method, but separating these
     * out so we can see both the basic solution and the extension solution.
     * @param fileCharacters all characters in a java file
     * @return true if the braces are balanced, false otherwise
     */
    public boolean checkExtension(List<Character> fileCharacters) {

        //creating debug variables
        Stack<Character> stack = new Stack();
        Long openCount;
        Long totalCount = 0L;
        Long closeCount;
        boolean isBalanced = false;

        //Stream over the list to count open and close braces
        openCount = fileCharacters.stream()
                .filter(s -> s.equals('{'))
                .count();

        closeCount = fileCharacters.stream()
                .filter(s -> s.equals('}'))
                .count();

        //calculate brace counters
        if (openCount > closeCount){
            openCount = openCount - closeCount;
            closeCount = 0L;
            totalCount = openCount + closeCount;
        } else if (closeCount > openCount){
            closeCount = closeCount - openCount;
            openCount = 0L;
            totalCount = openCount + closeCount;
        } else if (openCount == closeCount && !fileCharacters.isEmpty() &&fileCharacters.get(0) != CLOSE){
            openCount = 0L;
            closeCount = 0L;
            totalCount = 0L;
        }else if (!fileCharacters.isEmpty() && fileCharacters.get(0) == CLOSE){
        openCount = 1L;
        closeCount = 1L;
        totalCount = openCount + closeCount;
        }

        //Create structures for string counter
        Stack<Integer> indexCount = new Stack<>();
        List<Character> longestString = new ArrayList<>();

        //Loop over string to get start and stop indices for longest string sublist.
        //CREDIT: Friday Q&A Session between  Nathan Holt and Teresa Brown
        for (int i = 0; i < fileCharacters.size(); i++) {
            char c = fileCharacters.get(i);
            if (c == OPEN){
                indexCount.push(i);
            } else if (c == CLOSE){
                if (!indexCount.isEmpty()){
                    int open = indexCount.pop();
                    if (i - open > longestString.size()) {
                        longestString = fileCharacters.subList(open, i+1);
                    }
                }
            }
        }

        //loop to determine if braces are even and to count
        for (Character fileCharacter : fileCharacters) {
            if (fileCharacter == OPEN) {
                stack.push(fileCharacter);
            } else if (!stack.isEmpty() &&
                    (fileCharacter == CLOSE)) {
                stack.pop();
            } else {
                isBalanced = false;
            }
        }


        System.out.println("Maximum unbalanced braces encountered: " + totalCount + "\n" +
                "Number of unbalanced open braces {: " + openCount + "\n" +
                "Number of unbalanced close braces }: " + closeCount + "\n" +
                "Longest string between balanced braces: " + longestString);

        if (stack.isEmpty() && openCount == closeCount){
        isBalanced = true;
        }
        return isBalanced;

    }

    /**
     * Use this to enable or disable additional debug messages.
     * @param debug the value to set the debug variable
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
