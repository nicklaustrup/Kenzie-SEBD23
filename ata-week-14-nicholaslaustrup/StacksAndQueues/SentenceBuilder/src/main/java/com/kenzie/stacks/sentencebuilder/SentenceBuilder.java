package com.kenzie.stacks.sentencebuilder;

import java.util.LinkedList;
import java.util.Stack;

public class SentenceBuilder {

    Stack<String> sentenceBuilderStack;

    /**
     * Constructor.
     */
    public SentenceBuilder() {
        this.sentenceBuilderStack = new Stack<>();
    }

    /**
     * Adds a word to the sentence.
     * @param word to be added to the sentence.
     */
    public void addWord(String word) {
        sentenceBuilderStack.push(word);
    }

    /**
     * Undoes the last word added, and returns it.
     * @return The word most recently added to the sentence, if any; null, otherwise.
     */
    public String undo() {
        if (sentenceBuilderStack.empty()){
            return null;
        }
        return sentenceBuilderStack.pop();
    }

    /**
     * Display all the current words in our sentence.
     * @return string representation of the words currently in the sentence.
     */
    public String getSentenceWords() {
        return sentenceBuilderStack.toString();
    }
}
