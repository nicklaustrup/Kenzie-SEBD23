package com.kenzie.breadthfirstsearch.wordsearch;

import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Coordinate;
import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Direction;
import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Node;

import java.util.*;

import static com.kenzie.breadthfirstsearch.wordsearch.SampleWordSearches.SORE_SEARCH;

/**
 * Class for completing word search puzzles.
 */
public class WordSearcher {
    private final WordSearch wordSearch;

    /**
     * Create a word search instance for the provided problem.
     *
     * @param wordSearch - the word search puzzle to solve
     */
    public WordSearcher(WordSearch wordSearch) {
        this.wordSearch = wordSearch;
    }

    /**
     * Main method for manual testing.
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        WordSearcher wordSearcher = new WordSearcher(SORE_SEARCH);

        System.out.println(wordSearcher.calculateWordCounts());
    }

    /**
     * Calculate how many ways (permutations) you can use the letters in the puzzle to spell
     * each word provided as part of the puzzle.
     *
     * @return a Map of the desired words, and how many ways (permutations) you can use the letters in the puzzle to
     * spell each word provided as part of the puzzle.
     */
    public Map<String, Long> calculateWordCounts() {
        List<String> wordList = wordSearch.getWordsToFind();
        Map<String, Long> solution = new HashMap<>();

        for (String word : wordList) {
            char firstLetter = word.charAt(0);
            int counter = 0;
            solution.put(word, (long) counter);
            for (int i = 0; i < this.wordSearch.getHeight(); i++) {
                for (int j = 0; j < this.wordSearch.getWidth(); j++) {
                    Coordinate coordinate = new Coordinate(i, j);


                    if (wordSearch.getCharacterAt(coordinate) == firstLetter) {
                        Queue<List<Node<Coordinate>>> currentLevel = new LinkedList<>();

                        List<Node<Coordinate>> currentPath = new ArrayList<>();
                        currentPath.add(new Node<>(coordinate));
                        currentLevel.add(currentPath);

                        while (!currentLevel.isEmpty()) {

                            // Retrieve the next node from the list of nodes
                            // to visit
                            currentPath = currentLevel.poll();
                            Node<Coordinate> currentNode = currentPath.get(currentPath.size() - 1);


                            String pathWord = "";
                            for (Node<Coordinate> node : currentPath) {
                                pathWord = pathWord + wordSearch.getCharacterAt(node.getValue());
                            }

                            // Compare your current node against the condition
                            // you want to check
                            if (pathWord.equals(word)) {
                                counter++;
                                continue;
                            }
                            // Add the non-visited nodes connected to your
                            // current node to the end of the queue of nodes
                            // to visit
                            if (pathWord.equals(word.substring(0, currentPath.size()))) {

                                List<Node<Coordinate>> coordinateNeighbors = new ArrayList<>();

                                for (Direction direction : Direction.ALL_DIRECTIONS) {
                                    Coordinate neighbor = direction.addToCoordinate(currentNode.getValue());
                                    if (neighbor.getColumn() >= 0 && neighbor.getColumn() < wordSearch.getHeight() && neighbor.getRow() >= 0 && neighbor.getRow() < wordSearch.getWidth()) {
                                        coordinateNeighbors.add(new Node<>(neighbor));
                                    }
                                }
                                    currentNode.addNeighbors(coordinateNeighbors);
                                    for (Node<Coordinate> neighbors : currentNode.getNeighbors()) {
                                        List<Node<Coordinate>> newPath = new ArrayList<>(currentPath);
                                        newPath.add(neighbors);
                                        currentLevel.add(newPath);
                                    }
                            }
                        }
                        solution.put(word, (long) counter);
                    }
                }
            }
        }
        return solution;
    }
}
