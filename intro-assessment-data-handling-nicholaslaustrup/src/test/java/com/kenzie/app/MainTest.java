package com.kenzie.app;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String INPUTSTRING = "Hello! Welcome to Kenzie.  My name is Robert, and I'm here with my friend Waldo.  Have you met waldo?";
    private final String GENERICSTRING = "This is a string.";
    private final String EXTRA_SPACE_STRING = " Spaces         do not bother my code ";
    private final String EMPTYSTRING = "\n";
    private final String MIDDLE_PUNCT_STRING = "Don't strip the middle-of-the-word symbols";
    private final String END_PUNCT_STRING = "Can you handle this punctuation? Can you? Can you?!";
    private final String ONLY_PUNCT_STRING = "!!!";
    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeEach
    public void setTestInput() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void test_vowelA() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total [a]: 5"));
    }

    @Test
    public void test_vowelE() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total [e]: 12"));
    }
    @Test
    public void test_vowelI() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total [i]: 5"));
    }
    @Test
    public void test_vowelO() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total [o]: 7"));
    }
    @Test
    public void test_vowelU() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total [u]: 1"));
    }
    @Test
    public void test_periods() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total periods: 2"));
    }
    @Test
    public void test_commas() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total commas: 1"));
    }
    @Test
    public void test_exclamationPoints() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total exclamation points: 1"));
    }
    @Test
    public void test_questionMarks() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total question marks: 1"));
    }
    @Test
    public void test_characters() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total characters: 101"));
    }
    @Test
    public void test_totalWords() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("Total words: 19"));
    }
    @Test
    public void test_longestWord() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("The longest word: Welcome"));
    }
    @Test
    public void test_shortestWord() {
        runMainWithInput(INPUTSTRING);
        assertThat(outContent.toString(), containsString("The shortest word: to"));
    }

    @Test
    public void test_using_scanner() {
        runMainWithInput(GENERICSTRING);
        assertThat("Reason: Generic string test failed. May not be coded to take user input.",outContent.toString(),
                containsString("Total words: 4"));
    }


    @Test
    public void test_empty_totalWords() {
        runMainWithInput(EMPTYSTRING);
        assertThat("Reason: Empty string test failed. May not be coded to take user input.",outContent.toString(),
                containsString("Total words: 0"));
    }


    @Test
    public void test_punct_totalWords() {
        runMainWithInput(ONLY_PUNCT_STRING);
        assertThat("Reason: Only punctuation string test failed. Punctuation(!,.?) is not counted in word length.",outContent.toString(), containsString("Total words: 0"));
    }

    @Test
    public void test_end_punct_longestWord() {
        runMainWithInput(END_PUNCT_STRING);
        assertThat(outContent.toString(), containsString("The longest word: punctuation"));
        assertThat("Reason: End punctuation test failed. Punctuation(!,.?) should not be counted in word length.", outContent.toString(), not(containsString("The longest word: punctuation?")));
    }

    @Test
    public void test_extra_spaces_longestWord() {
        runMainWithInput(EXTRA_SPACE_STRING);
        assertThat("Reason: Extra space test failed. Extra spaces should be stripped and not count in word length",outContent.toString(), containsString("The longest word: Spaces"));
    }

    @Test
    public void test_mid_punct_longestWord() {
        runMainWithInput(MIDDLE_PUNCT_STRING);
        assertThat("Reason: Punctuation in middle of word failed. Hyphens(-) should not be stripped.",outContent.toString(), containsString("The longest word: middle-of-the-word"));
    }

    private static void runMainWithInput(String input){
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            Main.main(new String[]{});
        } catch (NoSuchElementException e) {
            // Ignore this error
        }
    }
}
