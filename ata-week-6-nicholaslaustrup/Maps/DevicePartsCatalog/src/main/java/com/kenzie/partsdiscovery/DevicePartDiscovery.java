package com.kenzie.partsdiscovery;

import java.util.*;

/**
 * Helps expose key words from new editions of part catalogs.
 */
public class DevicePartDiscovery {

    /**
     * Calculate how often each word appears in a Catalog.
     * @param catalog The catalog to calculate word frequencies for.
     * @return A Map of words that appear in the catalog to the number of times they appear.
     */
    public Map<String, Integer> calculateWordCounts(PartCatalog catalog) {
        Map<String, Integer> wordCounts = new HashMap<>();
        List<String> words = catalog.getCatalogWords();
        //          Cycle through the list of words.
        for (String word : words) {
            if (wordCounts.containsKey(word)){
                Integer counter = wordCounts.get(word);
                counter++;
                wordCounts.put(word, counter);
            } else {
                wordCounts.put(word, 1);
            }
        }
        return wordCounts;
    }

    /**
     * Removes a word from the provided word count map.
     * @param word the word to be removed
     * @param wordCounts the map to remove the word from
     */
    public void removeWord(String word, Map<String, Integer> wordCounts) {
        wordCounts.remove(word);
    }

    /**
     * Find the word that appears most frequently based on the word counts from a catalog.
     * @param wordCounts an association between a word and the total number of times it appeared in a catalog
     * @return The word that appears most frequently in the catalog to the number of times they appear.
     */
    public String getMostFrequentWord(Map<String, Integer> wordCounts) {
        String frequentWord = "";
        int frequency = 0;
        for (Map.Entry<String, Integer> word:wordCounts.entrySet()) {
            if (word.getValue() > frequency){
                frequentWord = word.getKey();
                frequency = word.getValue();
            }
        }
        return frequentWord;
    }

    /**
     * Calculates the TF-IDF score for each word in a catalog. The TF-IDF score for a word
     * is equal to the count * idf score. You can assume there will be an idfScore for each word
     * in wordCounts.
     * @param wordCounts - associates a count for each word from a catalog
     * @param idfScores - associates an IDF score for each word in the catalog
     * @return a map associating each word with its TF-IDF score.
     */
    public Map<String, Double> getTfIdfScores(Map<String, Integer> wordCounts, Map<String, Double> idfScores) {
        String wordHolder;
        Double numberHolder;
        Map<String, Double> tfIdfScores = new HashMap<>();
        for (Map.Entry<String, Integer> word : wordCounts.entrySet()) {
            wordHolder = word.getKey();
            numberHolder = Double.valueOf(word.getValue());
            Double idfScore = idfScores.get(word.getKey());
            Double score = numberHolder * idfScore;
            tfIdfScores.put(wordHolder, score);
        }
        return tfIdfScores;
    }

    // TODO: Implement this method. Hint: Follow the comments
    /**
     * Gets the 10 highest (TF-IDF) scored words for a catalog.
     *
     * @param tfIdfScores - associates a TF-IDF score for each word in a catalog
     * @return a list of the 10 highest scored words for a catalog.
     */
    public List<String> getBestScoredWords(Map<String, Double> tfIdfScores) {
        /*
            Create a List copy of our Set of key:Value pairs,
            representing a pair from our word (String) : TF-IDF score (Double) relationship
            We want a list because we will be using this to sort and need to preserve the sorted order
         */
        List<Map.Entry<String,Double>> entries = new ArrayList<>(tfIdfScores.entrySet());

        /*
            Sort our relationships by their values (TF-IDF scores)
            Note that this will sort in *ascending* order (ex: 0,1,2,3,4)
        */
        entries.sort(Map.Entry.comparingByValue());

        /*
            Reverse our list of relationships so that we
            have them in *descending* order, such that the words
            with the highest TF-IDF scores come first.
         */
        Collections.reverse(entries);


        /*
            Create a list of Strings representing the top scoring words
            in our Map
         */
        List<String> topWordsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topWordsList.add(String.valueOf(entries.get(i).getKey()));
        }
        // For the top ten key:value pairs (in *descending* order of TF-IDF scores)


        return topWordsList;
    }

    // TODO: Implement this method. Hint: Follow the comments
    /**
     * Calculates the IDF score for each word in a set of catalogs. The IDF score for a word
     * is equal to the inverse of the total number of times appears in all catalogs.
     * Assume df is the sum of the counts of a single word across all catalogs, then idf = 1.0/df.
     *
     * @param catalogWordCounts - a list of maps that associate a count for each word for each catalog
     * @return a map associating each word with its IDF score.
     */
    public Map<String, Double> calculateIdfScores(List<Map<String, Integer>> catalogWordCounts) {
        // Calculate the Document Frequency (DF) scores for each unique word in our catalogs
        Map<String, Integer> dfScores = getDfScores(catalogWordCounts);
        // Create a map which associates a word (String) with it's idf score (Double);
        Map<String, Double> idfScores = new HashMap<>();
        // get the overall number of catalogs we have
        int catCount = catalogWordCounts.size();
        // For each word in our catalog, get the word:dfScore relationship for it
        for (Map.Entry<String, Integer> dfScore: dfScores.entrySet()) {
        // Get the word from our word:dfScore relationship
            String dfString = dfScore.getKey();
        // Get the DF score from our word:dfScore relationship
            Integer dfValue = dfScore.getValue();
            /*
                Calculate the IDF score for the word
                We need to cast one of our values to double in order to
                get a double return value from the division.
                Otherwise, we will lose floating-point precision.
             */
            Double idfScore = Math.log10(catCount / (double) dfValue);
        // add the idf score for the word to our map
        idfScores.put(dfString, idfScore);
    }
        return idfScores;
    }

    private static Map<String, Integer> getDfScores(List<Map<String, Integer>> catalogWordCounts) {
        /*
            Create a map associating a word (String) with the number of
            catalogs which contain the word (Integer)
        */
        Map<String, Integer> dfScores = new HashMap<>();

        // For each catalog
        for (Map<String, Integer> wordCounts : catalogWordCounts) {
            // For each word in our catalog
            for (String word : wordCounts.keySet()) {
                /*
                    If this word has already been found in one catalog:
                      get the previous count of catalogs the word was in
                    If our previous catalogs did not add the word to our map:
                        set the count of previous articles the word was
                        used in to 0
                */
                Integer value = dfScores.getOrDefault(word, 0);

                /*
                    Increment the count of how many catalogs our word was found in
                    and update that value for our word in the map
                 */
                dfScores.put(word, value + 1);
            }
        }
        return dfScores;
    }

}
