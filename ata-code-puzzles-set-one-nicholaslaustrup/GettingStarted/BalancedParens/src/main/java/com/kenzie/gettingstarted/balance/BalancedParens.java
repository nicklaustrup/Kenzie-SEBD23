package com.kenzie.gettingstarted.balance;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Drills to apply the problem-solving framework on variations of the Balanced Parentheses problem.
 */
public class BalancedParens {

    /**
     * Determine whether a string composed entirely of opening and closing parentheses is balanced.
     * If a closing paren appears without an opening paren, the string is not balanced.
     * If an opening paren is not closed by the end of the string, it is not balanced.
     * <p>
     * BalancedParensTest includes some sample test cases.*
     *
     * @param parens A String of opening and closing parentheses
     * @return true if every opening and closing paren has a partner
     */
    public boolean simpleBalancedParenthesis(String parens) {
        int count = 0;
        for (char c : parens.toCharArray()) {
            if (c == '(') {
                count++;
            }
            if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    /**
     * Placeholder for the we-do classroom activity for Problem-Solving Framework.
     * <p>
     * BalancedParensTest includes some sample test cases.
     *
     * @param text A text String, described in the classroom.
     * @return True as described in the classroom, false otherwise.
     */
    public boolean balancedBracketsAndParenthesis(String text) {
        Stack<Character> parenStack = new Stack<>();
//loop over each character
        for (Character character : text.toCharArray()) {
            //   if it's an open grouping character, push it on the stack
            if (character.equals('(') || character.equals('{') || character.equals('[')) {
                parenStack.push(character);
            }
            //   if it's a closing grouping character, pop the stack
            //   if the popped character doesn't match, return false
            else {
                if (parenStack.size() == 0) {
                    return false;
                }
                if ((parenStack.peek().equals('{') && character.equals('}')) ||
                        (parenStack.peek().equals('(') && character.equals(')')) ||
                        (parenStack.peek().equals('[') && character.equals(']'))) {
                    parenStack.pop();
                } else {
                    return false;
                }
            }
        }
//return whether the stack is empty
        return (parenStack.size() == 0);
    }

    /**
     * Placeholder for the you-do classroom activity for Problem-Solving Framework.
     * <p>
     * BalancedParensTest includes some sample test cases.
     *
     * @param text A text String, described in the classroom.
     * @return True as described in the classroom, false otherwise.
     */
    public boolean balancedParenthesisSmileysAndString(String text) {
        //create stack and char array
        Stack<Character> stack = new Stack<>();
        char[] character = text.toCharArray();
        //loop over charArray
        for (int i = 0; i < character.length; i++) {
            //ignore any letters or numbers that are not parenthesis or smileys
            if (character[i] != '(' && character[i] != ')' && character[i] != '{' && character[i] != '}' &&
                    character[i] != '[' && character[i] != ']' && character[i] != ':'){
                continue;
            }
            //if smiley, continue
            if (character[i] == ':'&& i + 1 < character.length){
                if (character[i + 1] == ')' || character[i + 1] == '}' || character[i + 1] == ']' ||
                        character[i + 1] == '(' || character[i + 1] == '{' || character[i + 1] == '['){
                    i++;
                    continue;
                }
                else {
                    continue;
                }
            }
            //if opening parenthesis or colon, add to stack
            if (character[i] == ('(') || character[i] == ('[') || character[i] == ('{')){
                stack.push(character[i]);
            }
            else {
                if (stack.size() == 0){
                    return false;
                }
                //if closing bracket, check if top of stack is smiley
                //or is open paren, pop or false
                if (stack.peek().equals('{') && character[i] == ('}') ||
                    stack.peek().equals('(') && character[i] == (')') ||
                    stack.peek().equals('[') && character[i] == (']')) {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        //return size of stack
        return (stack.size() == 0);
    }
}
